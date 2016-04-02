package com.s13g.themetools.keystyler.controller;

import com.s13g.themetools.keystyler.annotations.ForApplication;
import com.s13g.themetools.keystyler.view.MainViewInterface;
import dagger.Module;
import dagger.Provides;

@Module
public class ControllerModule {
  @Provides
  @ForApplication
  public MainController provideMainController(
          MainViewInterface view,
          SettingsController settingsController) {
    return new MainControllerImpl(view, settingsController);
  }

  @Provides
  @ForApplication
  public SettingsController provideSettingsController() {
    return new SettingsController();
  }
}
