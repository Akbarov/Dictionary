package com.example.hp_pk.dictionary.ui.activities.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;

import com.example.hp_pk.dictionary.Dictionary;
import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.adapters.MyExpandableAdapter;
import com.example.hp_pk.dictionary.classes.LevelGroup;
import com.example.hp_pk.dictionary.database.DbManager;
import com.example.hp_pk.dictionary.database.LessonItem;
import com.example.hp_pk.dictionary.database.TestItem;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestFragment extends Fragment {

    private String category = "English";
    @Inject
    DbManager manager;

    @BindView(R.id.loading)
    ProgressBar progressBar;

    @BindView(R.id.expandable_list)
    ExpandableListView expandableListView;

    public static TestFragment newInstance(String category) {
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        TestFragment fragment = new TestFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dictionary.getAppComponent().inject(this);
        if (getArguments() != null) {
            category = getArguments().getString("category");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subject_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<TestItem> testItems = manager.getTestItemBySubject(category);
        String level = "";
        List<LevelGroup> levelGroupList = new ArrayList<>();
        LevelGroup group = new LevelGroup();
        for (TestItem item : testItems) {
            if (level.equals(item.getLevel())) {
                group.addLesson(item.getName());
            } else {
                level = item.getLevel();
                group = new LevelGroup();
                group.setLevel(item.getLevel());
                levelGroupList.add(group);
            }
        }
        MyExpandableAdapter adapter = new MyExpandableAdapter(getContext(), levelGroupList);
        expandableListView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }
}
