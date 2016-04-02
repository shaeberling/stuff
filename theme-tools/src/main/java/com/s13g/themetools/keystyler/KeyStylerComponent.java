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

package com.s13g.themetools.keystyler;

import com.s13g.themetools.keystyler.annotations.ForApplication;
import com.s13g.themetools.keystyler.controller.ControllerModule;
import com.s13g.themetools.keystyler.view.ViewModule;
import dagger.Component;

import javax.inject.Singleton;

/**
 * The main dagger component for the application.
 */
@ForApplication
@Component(modules = {ControllerModule.class, ViewModule.class})
public interface KeyStylerComponent {
  void inject(KeyStylerApp application);
}
