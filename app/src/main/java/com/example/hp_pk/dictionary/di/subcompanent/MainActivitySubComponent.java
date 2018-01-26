package com.example.hp_pk.dictionary.di.subcompanent;

import com.example.hp_pk.dictionary.ui.activities.MainActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * @author akbar
 * @since 2017, July 17
 */

@Subcomponent
public interface MainActivitySubComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {
    }
}
