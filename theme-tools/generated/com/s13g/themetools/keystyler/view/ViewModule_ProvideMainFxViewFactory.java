package com.s13g.themetools.keystyler.view;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ViewModule_ProvideMainFxViewFactory implements Factory<FXMLLoaderProxy> {
  private final ViewModule module;

  public ViewModule_ProvideMainFxViewFactory(ViewModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public FXMLLoaderProxy get() {  
    FXMLLoaderProxy provided = module.provideMainFxView();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<FXMLLoaderProxy> create(ViewModule module) {  
    return new ViewModule_ProvideMainFxViewFactory(module);
  }
}

