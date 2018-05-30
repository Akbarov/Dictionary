package com.example.hp_pk.dictionary;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.example.hp_pk.dictionary.di.companent.AppComponent;
import com.example.hp_pk.dictionary.di.companent.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasFragmentInjector;

/**
 * @author HP-PK
 * @since 2018, January 23
 */

public class Dictionary extends Application implements HasActivityInjector, HasFragmentInjector {
    private static AppComponent appComponent;
    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);

// Setting timeout globally for the download network requests:
        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setReadTimeout(30_000)
                .setConnectTimeout(30_000)
                .build();
        PRDownloader.initialize(getApplicationContext(), config);

    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentInjector;
    }
}
