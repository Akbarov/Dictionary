package com.example.hp_pk.dictionary.di.companent;

import com.example.hp_pk.dictionary.Dictionary;
import com.example.hp_pk.dictionary.di.modul.AppModule;
import com.example.hp_pk.dictionary.presentation.presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author HP-PK
 * @since 2018, January 23
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class})
public interface AppComponent {

    void inject(Dictionary dictionary);

    void inject(MainPresenter mainPresenter);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Dictionary application);

        AppComponent build();
    }
}
