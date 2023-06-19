package com.tmdb.store.reducer.app.di;

import com.tmdb.feature.movie.details.reducer.MovieDetailsFeatureSlice;
import com.tmdb.feature.reducer.HomeFeatureSlice;
import com.tmdb.store.base.Action;
import com.tmdb.store.base.Effect;
import com.tmdb.store.env.contract.AppEnv;
import com.tmdb.store.state.app.AppState;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AppReducerModule_AppReducerFactory implements Factory<Function2<AppState, Action, Pair<AppState, Effect<AppEnv>>>> {
  private final Provider<HomeFeatureSlice> homeFeatureSliceProvider;

  private final Provider<MovieDetailsFeatureSlice> movieDetailsFeatureSliceProvider;

  public AppReducerModule_AppReducerFactory(Provider<HomeFeatureSlice> homeFeatureSliceProvider,
      Provider<MovieDetailsFeatureSlice> movieDetailsFeatureSliceProvider) {
    this.homeFeatureSliceProvider = homeFeatureSliceProvider;
    this.movieDetailsFeatureSliceProvider = movieDetailsFeatureSliceProvider;
  }

  @Override
  public Function2<AppState, Action, Pair<AppState, Effect<AppEnv>>> get() {
    return appReducer(homeFeatureSliceProvider.get(), movieDetailsFeatureSliceProvider.get());
  }

  public static AppReducerModule_AppReducerFactory create(
      Provider<HomeFeatureSlice> homeFeatureSliceProvider,
      Provider<MovieDetailsFeatureSlice> movieDetailsFeatureSliceProvider) {
    return new AppReducerModule_AppReducerFactory(homeFeatureSliceProvider, movieDetailsFeatureSliceProvider);
  }

  public static Function2<AppState, Action, Pair<AppState, Effect<AppEnv>>> appReducer(
      HomeFeatureSlice homeFeatureSlice, MovieDetailsFeatureSlice movieDetailsFeatureSlice) {
    return Preconditions.checkNotNullFromProvides(AppReducerModule.INSTANCE.appReducer(homeFeatureSlice, movieDetailsFeatureSlice));
  }
}
