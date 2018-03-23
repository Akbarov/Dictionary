package com.example.hp_pk.dictionary.ui.activities.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.ui.activities.BooksListActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Zohidjon
 * @since 2018, March 21
 */

public class ChooseBookCategory extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_book_category, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(value = {R.id.literature, R.id.ielts, R.id.lessons, R.id.journal})
    public void onClick(View view) {
        String category = "";
        switch (view.getId()) {
            case R.id.lessons:
                category = getString(R.string.lessons);
                break;
            case R.id.ielts:
                category = getString(R.string.ielts);
                break;
            case R.id.literature:
                category = getString(R.string.literature);
                break;
            case R.id.journal:
                category = getString(R.string.journal);
                break;
        }
        BooksListActivity.start(getContext(), category);
    }
}
