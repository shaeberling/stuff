package com.s13g.themetools.keystyler;

import com.s13g.themetools.keystyler.controller.ControllerModule;
import com.s13g.themetools.keystyler.controller.ControllerModule_ProvideMainControllerFactory;
import com.s13g.themetools.keystyler.controller.ControllerModule_ProvideSettingsControllerFactory;
import com.s13g.themetools.keystyler.controller.MainController;
import com.s13g.themetools.keystyler.controller.SettingsController;
import com.s13g.themetools.keystyler.view.FXMLLoaderProxy;
import com.s13g.themetools.keystyler.view.MainViewInterface;
import com.s13g.themetools.keystyler.view.ViewModule;
import com.s13g.themetools.keystyler.view.ViewModule_ProvideMainFxViewFactory;
import com.s13g.themetools.keystyler.view.ViewModule_ProvideMainSceneFactory;
import com.s13g.themetools.keystyler.view.ViewModule_ProvideMainViewInterfaceFactory;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import dagger.internal.ScopedProvider;
import javafx.scene.Scene;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerKeyStylerComponent implements KeyStylerComponent {
  private Provider<FXMLLoaderProxy> provideMainFxViewProvider;
  private Provider<MainViewInterface> provideMainViewInterfaceProvider;
  private Provider<SettingsController> provideSettingsControllerProvider;
  private Provider<MainController> provideMainControllerProvider;
  private Provider<Scene> provideMainSceneProvider;
  private MembersInjector<KeyStylerApp> keyStylerAppMembersInjector;

  private DaggerKeyStylerComponent(Builder builder) {  
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {  
    return new Builder();
  }

  private void initialize(final Builder builder) {  
    this.provideMainFxViewProvider = ScopedProvider.create(ViewModule_ProvideMainFxViewFactory.create(builder.viewModule));
    this.provideMainViewInterfaceProvider = ScopedProvider.create(ViewModule_ProvideMainViewInterfaceFactory.create(builder.viewModule, provideMainFxViewProvider));
    this.provideSettingsControllerProvider = ScopedProvider.create(ControllerModule_ProvideSettingsControllerFactory.create(builder.controllerModule));
    this.provideMainControllerProvider = ScopedProvider.create(ControllerModule_ProvideMainControllerFactory.create(builder.controllerModule, provideMainViewInterfaceProvider, provideSettingsControllerProvider));
    this.provideMainSceneProvider = ScopedProvider.create(ViewModule_ProvideMainSceneFactory.create(builder.viewModule, provideMainFxViewProvider));
    this.keyStylerAppMembersInjector = KeyStylerApp_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), provideMainControllerProvider, provideSettingsControllerProvider, provideMainSceneProvider);
  }

  @Override
  public void inject(KeyStylerApp application) {  
    keyStylerAppMembersInjector.injectMembers(application);
  }

  public static final class Builder {
    private ControllerModule controllerModule;
    private ViewModule viewModule;
  
    private Builder() {  
    }
  
    public KeyStylerComponent build() {  
      if (controllerModule == null) {
        this.controllerModule = new ControllerModule();
      }
      if (viewModule == null) {
        throw new IllegalStateException("viewModule must be set");
      }
      return new DaggerKeyStylerComponent(this);
    }
  
    public Builder controllerModule(ControllerModule controllerModule) {  
      if (controllerModule == null) {
        throw new NullPointerException("controllerModule");
      }
      this.controllerModule = controllerModule;
      return this;
    }
  
    public Builder viewModule(ViewModule viewModule) {  
      if (viewModule == null) {
        throw new NullPointerException("viewModule");
      }
      this.viewModule = viewModule;
      return this;
    }
  }
}

