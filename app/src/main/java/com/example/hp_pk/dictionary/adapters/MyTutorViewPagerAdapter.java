package com.example.hp_pk.dictionary.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hp_pk.dictionary.database.Categories;
import com.example.hp_pk.dictionary.ui.activities.fragments.AudioRecyclerFragment;
import com.example.hp_pk.dictionary.ui.activities.fragments.RecyclerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zohidjon
 * @since 2018, March 21
 */

public class MyTutorViewPagerAdapter extends FragmentPagerAdapter {
    private int count = 0;
    private List<Categories> categoriesList;

    public MyTutorViewPagerAdapter(FragmentManager fm, List<Categories> categoryList) {
        super(fm);
        if (categoryList != null) {
            this.count = categoryList.size();
            this.categoriesList = categoryList;
        } else {
            this.categoriesList = new ArrayList<>();
            this.count = 0;
        }
    }

    @Override
    public Fragment getItem(int position) {
        String categoryName = (String) getPageTitle(position);
        return AudioRecyclerFragment.newInstance(categoryName);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (categoriesList != null)
            return categoriesList.get(position).getName();
        else return "";
    }
}
