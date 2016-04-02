package com.s13g.themetools.keystyler.view;

import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ViewModule_ProvideMainViewInterfaceFactory implements Factory<MainViewInterface> {
  private final ViewModule module;
  private final Provider<FXMLLoaderProxy> loaderProvider;

  public ViewModule_ProvideMainViewInterfaceFactory(ViewModule module, Provider<FXMLLoaderProxy> loaderProvider) {  
    assert module != null;
    this.module = module;
    assert loaderProvider != null;
    this.loaderProvider = loaderProvider;
  }

  @Override
  public MainViewInterface get() {  
    MainViewInterface provided = module.provideMainViewInterface(loaderProvider.get());
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<MainViewInterface> create(ViewModule module, Provider<FXMLLoaderProxy> loaderProvider) {  
    return new ViewModule_ProvideMainViewInterfaceFactory(module, loaderProvider);
  }
}

