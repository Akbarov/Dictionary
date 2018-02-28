package com.example.hp_pk.dictionary.di.modul;

import android.content.Context;

import com.example.hp_pk.dictionary.Dictionary;
import com.example.hp_pk.dictionary.database.DbManager;
import com.example.hp_pk.dictionary.manager.PrefManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @auther ZOHIDJON
 * @since 1/25/18.
 */


@Singleton
@Module
public class AppModule {

    @Provides
    Context provideContext(Dictionary application) {

        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    PrefManager getPrefManager(Context context) {

        return new PrefManager(context);
    }

    @Singleton
    @Provides
    DbManager getDbManager(Context context) {
        return new DbManager(context);
    }


}
