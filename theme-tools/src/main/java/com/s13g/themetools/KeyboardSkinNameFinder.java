/*
 * Copyright 2015 Sascha Haeberling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.s13g.themetools;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Read ZIP files containing keyboard themes. Then read the skins.xml within the
 * ZIP file and read the name of the theme.
 * <p>
 * Usage: KeyboardSkinNameFinder 'path containing ZIP files'
 */
public class KeyboardSkinNameFinder {
    private static SAXBuilder sSaxBuilder = new SAXBuilder();

    public static void main(String[] args) throws IOException, JDOMException {
        if (args.length != 1) {
            System.err.println("No path provided");
            return;
        }

        File directory = new File(args[0]);
        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("Given argument is not a valid directory: " + args[0]);
            return;
        }

        File[] zipFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".zip"));
        if (zipFiles.length == 0) {
            System.err.println("No ZIP files found in " + directory.getAbsolutePath());
            return;
        }

        System.out.println("ZIP files found: " + zipFiles.length);

        for (File file : zipFiles) {
            ZipInputStream zin = new ZipInputStream(new FileInputStream(file));
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                if (entry.getName().equals("skin.xml")) {
                    printNames(file.getName(), zin);
                    break;
                }
            }
        }
    }

    private static void printNames(String filename, InputStream skinsXml) throws JDOMException,
            IOException {
        Document document = sSaxBuilder.build(skinsXml);
        if (document == null) {
            System.err.println("Could not get document - " + filename);
            return;
        }

        Element root = document.getRootElement();
        if (root == null) {
            System.err.println("Could not get root element - " + filename);
            return;
        }
        System.out.println(filename + " --> " + root.getAttributeValue("name"));
    }
}
