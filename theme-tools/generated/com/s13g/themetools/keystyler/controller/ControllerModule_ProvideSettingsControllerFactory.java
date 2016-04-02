package com.s13g.themetools.keystyler.controller;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ControllerModule_ProvideSettingsControllerFactory implements Factory<SettingsController> {
  private final ControllerModule module;

  public ControllerModule_ProvideSettingsControllerFactory(ControllerModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public SettingsController get() {  
    SettingsController provided = module.provideSettingsController();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<SettingsController> create(ControllerModule module) {  
    return new ControllerModule_ProvideSettingsControllerFactory(module);
  }
}

