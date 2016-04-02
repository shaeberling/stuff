package com.s13g.themetools.keystyler.view;

import dagger.internal.Factory;
import javafx.scene.Scene;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ViewModule_ProvideMainSceneFactory implements Factory<Scene> {
  private final ViewModule module;
  private final Provider<FXMLLoaderProxy> loaderProvider;

  public ViewModule_ProvideMainSceneFactory(ViewModule module, Provider<FXMLLoaderProxy> loaderProvider) {  
    assert module != null;
    this.module = module;
    assert loaderProvider != null;
    this.loaderProvider = loaderProvider;
  }

  @Override
  public Scene get() {  
    Scene provided = module.provideMainScene(loaderProvider.get());
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<Scene> create(ViewModule module, Provider<FXMLLoaderProxy> loaderProvider) {  
    return new ViewModule_ProvideMainSceneFactory(module, loaderProvider);
  }
}

