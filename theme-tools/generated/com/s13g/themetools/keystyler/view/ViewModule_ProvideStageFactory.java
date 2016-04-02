package com.s13g.themetools.keystyler.view;

import dagger.internal.Factory;
import javafx.stage.Stage;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ViewModule_ProvideStageFactory implements Factory<Stage> {
  private final ViewModule module;

  public ViewModule_ProvideStageFactory(ViewModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public Stage get() {  
    Stage provided = module.provideStage();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<Stage> create(ViewModule module) {  
    return new ViewModule_ProvideStageFactory(module);
  }
}

