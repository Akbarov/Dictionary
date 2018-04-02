package com.example.hp_pk.dictionary.ui.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.ui.activities.fragments.ChooseBookCategory;
import com.example.hp_pk.dictionary.ui.activities.fragments.NewsFragment;
import com.example.hp_pk.dictionary.ui.activities.fragments.TutorFragment;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String title = "News";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(title);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123
            );
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new NewsFragment(), "news").commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.Fragment fragment;
        switch (id) {
            case R.id.my_tutor:
                fragment = getSupportFragmentManager().findFragmentByTag("tutor");
                if (fragment == null) {
                    fragment = new TutorFragment();
                }
                title = "My center";
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, "tutor").commit();
                break;
            case R.id.dictionary:
                DictionaryActivity.start(NavigationActivity.this);
                break;
            case R.id.books:
                fragment = getSupportFragmentManager().findFragmentByTag("books");
                if (fragment == null) {
                    fragment = new ChooseBookCategory();
                }
                title = "Library";
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, "books").commit();
                break;
            case R.id.news:
                fragment = getSupportFragmentManager().findFragmentByTag("news");
                if (fragment == null) {
                    fragment = new NewsFragment();
                }
                title = "News";
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, "news").commit();
                break;
        }
        setTitle(title);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
