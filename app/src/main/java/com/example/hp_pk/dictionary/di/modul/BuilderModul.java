package com.example.hp_pk.dictionary.di.modul;

import android.app.Activity;

import com.example.hp_pk.dictionary.ui.activities.MainActivity;
import com.example.hp_pk.dictionary.di.subcompanent.*;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by root on 1/25/18.
 */
@Module(subcomponents = {
        MainActivitySubComponent.class})
public abstract class BuilderModul {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivityInjectorFactory(MainActivitySubComponent.Builder builder);

}
