package com.example.hp_pk.dictionary.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hp_pk.dictionary.database.Categories;
import com.example.hp_pk.dictionary.ui.activities.fragments.SubjectFragment;
import com.example.hp_pk.dictionary.ui.activities.fragments.TestFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zohidjon
 * @since 2018, March 21
 */

public class TestViewPagerAdapter extends FragmentPagerAdapter {
    private int count;
    private List<Categories> categoriesList;

    public TestViewPagerAdapter(FragmentManager fm, List<Categories> categoryList) {
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
        return TestFragment.newInstance(categoryName);
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
