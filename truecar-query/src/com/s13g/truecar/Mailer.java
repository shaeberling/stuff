/*
 * Copyright 2016 Sascha Häberling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.s13g.truecar;

import com.s13g.truecar.data.SearchResponse;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * Tool for sending e-mails about vehicle matches.
 */
class Mailer {
  private static final String SMTP_HOST = "smtp-host";
  private static final String SMTP_FROM = "smtp-from";
  private static final String SMTP_TO = "smtp-to";
  private static final String SMTP_USER = "smtp-user";
  private static final String SMTP_PASSWORD = "smtp-password";

  private final String mHost;
  private final String mUser;
  private final String mPassword;

  private final String mFrom;
  private final String mTo;

  static Optional<Mailer> from(Properties properties) {
    String host = properties.getProperty(SMTP_HOST);
    if (host == null) {
      System.err.println(SMTP_HOST + " not specified in properties.");
      return Optional.empty();
    }
    String from = properties.getProperty(SMTP_FROM);
    if (from == null) {
      System.err.println(SMTP_FROM + " not specified in properties.");
      return Optional.empty();
    }
    String to = properties.getProperty(SMTP_TO);
    if (to == null) {
      System.err.println(SMTP_TO + " not specified in properties.");
      return Optional.empty();
    }
    String user = properties.getProperty(SMTP_USER);
    if (user == null) {
      System.err.println(SMTP_USER + " not specified in properties.");
      return Optional.empty();
    }
    String password = properties.getProperty(SMTP_PASSWORD);
    if (password == null) {
      System.err.println(SMTP_PASSWORD + " not specified in properties.");
      return Optional.empty();
    }
    return Optional.of(new Mailer(host, user, password, from, to));
  }

  private Mailer(String host, String user, String password, String from, String to) {
    mHost = host;
    mUser = user;
    mPassword = password;
    mFrom = from;
    mTo = to;
  }

  boolean sendMatches(String subject, List<SearchResponse.Vehicle> vehicles, VehicleFilter topMatchFilter) {
    if (vehicles.isEmpty()) {
      System.out.println("No new vehicle matches to send.");
      return false;
    }

    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", mHost);
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

    Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(mUser, mPassword);
      }
    });

    try {
      System.out.println("Attempting to send email ...");
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(mFrom));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(mTo));
      message.setSubject(subject);
      message.setText(genMessageFromItems(vehicles, topMatchFilter));
      Transport.send(message);
      System.out.println("Email sent ...");
      return true;
    } catch (MessagingException ex) {
      System.err.println("Cannot send email: " + ex.getMessage());
      return false;
    }
  }

  private String genMessageFromItems(List<SearchResponse.Vehicle> vehicles, VehicleFilter topMatchFilter) {
    List<SearchResponse.Vehicle> topMatches = new ArrayList<>();
    List<SearchResponse.Vehicle> rest = new ArrayList<>();

    for (SearchResponse.Vehicle vehicle : vehicles) {
      if (topMatchFilter.matches(vehicle)) {
        topMatches.add(vehicle);
      } else {
        rest.add(vehicle);
      }
    }

    String text = "TOP matches: " + topMatches.size() + " vehicles\n\n";
    for (SearchResponse.Vehicle vehicle : topMatches) {
      text += vehicle.toString() + "\n\n";
    }

    text += "\n\n==========================================\n";
    text += "Remaining:  " + vehicles.size() + " vehicles\n\n";
    for (SearchResponse.Vehicle vehicle : rest) {
      text += vehicle.toString() + "\n\n";
    }
    return text;
  }
}
