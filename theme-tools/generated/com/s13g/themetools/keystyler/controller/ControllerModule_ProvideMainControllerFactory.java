package com.s13g.themetools.keystyler.controller;

import com.s13g.themetools.keystyler.view.MainViewInterface;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ControllerModule_ProvideMainControllerFactory implements Factory<MainController> {
  private final ControllerModule module;
  private final Provider<MainViewInterface> viewProvider;
  private final Provider<SettingsController> settingsControllerProvider;

  public ControllerModule_ProvideMainControllerFactory(ControllerModule module, Provider<MainViewInterface> viewProvider, Provider<SettingsController> settingsControllerProvider) {  
    assert module != null;
    this.module = module;
    assert viewProvider != null;
    this.viewProvider = viewProvider;
    assert settingsControllerProvider != null;
    this.settingsControllerProvider = settingsControllerProvider;
  }

  @Override
  public MainController get() {  
    MainController provided = module.provideMainController(viewProvider.get(), settingsControllerProvider.get());
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<MainController> create(ControllerModule module, Provider<MainViewInterface> viewProvider, Provider<SettingsController> settingsControllerProvider) {  
    return new ControllerModule_ProvideMainControllerFactory(module, viewProvider, settingsControllerProvider);
  }
}

