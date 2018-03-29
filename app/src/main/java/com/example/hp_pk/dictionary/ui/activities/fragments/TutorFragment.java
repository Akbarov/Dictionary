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
import com.example.hp_pk.dictionary.ui.activities.MyTutor;
import com.example.hp_pk.dictionary.ui.activities.NavigationActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @auther ZOHIDJON
 * @since 3/29/18.
 */

public class TutorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutor_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(value = {R.id.extra, R.id.test, R.id.lessons, R.id.payment})
    public void onClick(View view) {
        String category;
        switch (view.getId()) {
            case R.id.extra:
                category = getString(R.string.extra);
                break;

            case R.id.test:
                category = getString(R.string.test);
                break;

            case R.id.lessons:
                category = getString(R.string.my_lesson);
                break;

            case R.id.payment:
                category = getString(R.string.payment);
                break;
            default:
                category = getString(R.string.payment);
                break;
        }

        MyTutor.start(getContext(),category);
    }
}
