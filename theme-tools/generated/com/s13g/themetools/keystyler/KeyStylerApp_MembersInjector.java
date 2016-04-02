package com.s13g.themetools.keystyler;

import com.s13g.themetools.keystyler.controller.MainController;
import com.s13g.themetools.keystyler.controller.SettingsController;
import dagger.MembersInjector;
import javafx.application.Application;
import javafx.scene.Scene;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class KeyStylerApp_MembersInjector implements MembersInjector<KeyStylerApp> {
  private final MembersInjector<Application> supertypeInjector;
  private final Provider<MainController> mMainControllerProvider;
  private final Provider<SettingsController> mSettingsControllerProvider;
  private final Provider<Scene> mMainSceneProvider;

  public KeyStylerApp_MembersInjector(MembersInjector<Application> supertypeInjector, Provider<MainController> mMainControllerProvider, Provider<SettingsController> mSettingsControllerProvider, Provider<Scene> mMainSceneProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert mMainControllerProvider != null;
    this.mMainControllerProvider = mMainControllerProvider;
    assert mSettingsControllerProvider != null;
    this.mSettingsControllerProvider = mSettingsControllerProvider;
    assert mMainSceneProvider != null;
    this.mMainSceneProvider = mMainSceneProvider;
  }

  @Override
  public void injectMembers(KeyStylerApp instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.mMainController = mMainControllerProvider.get();
    instance.mSettingsController = mSettingsControllerProvider.get();
    instance.mMainScene = mMainSceneProvider.get();
  }

  public static MembersInjector<KeyStylerApp> create(MembersInjector<Application> supertypeInjector, Provider<MainController> mMainControllerProvider, Provider<SettingsController> mSettingsControllerProvider, Provider<Scene> mMainSceneProvider) {  
      return new KeyStylerApp_MembersInjector(supertypeInjector, mMainControllerProvider, mSettingsControllerProvider, mMainSceneProvider);
  }
}

