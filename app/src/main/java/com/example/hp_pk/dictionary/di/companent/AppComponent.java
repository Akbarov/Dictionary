package com.example.hp_pk.dictionary.di.companent;

import com.example.hp_pk.dictionary.Dictionary;
import com.example.hp_pk.dictionary.di.modul.AppModule;
import com.example.hp_pk.dictionary.di.modul.BuilderModul;
import com.example.hp_pk.dictionary.di.modul.DatabaseModule;
import com.example.hp_pk.dictionary.presentation.presenter.DictionaryPresenter;
import com.example.hp_pk.dictionary.presentation.presenter.MainPresenter;
import com.example.hp_pk.dictionary.room_database.AppDatabase;
import com.example.hp_pk.dictionary.room_database.dao.SubjectDao;
import com.example.hp_pk.dictionary.ui.activities.DictionaryActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author HP-PK
 * @since 2018, January 23
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, BuilderModul.class})
public interface AppComponent {

    void inject(DictionaryActivity dictionary);

    void inject(MainPresenter mainPresenter);

    void inject(DictionaryPresenter presenter);

    void inject(Dictionary dictionary);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Dictionary application);

        AppComponent build();
    }
}
