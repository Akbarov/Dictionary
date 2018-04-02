package com.example.hp_pk.dictionary.ui.activities.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.hp_pk.dictionary.Dictionary;
import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.database.Book;
import com.example.hp_pk.dictionary.database.DbManager;
import com.example.hp_pk.dictionary.listeners.ItemClickListener;
import com.example.hp_pk.dictionary.ui.activities.BookActivity;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Zohidjon
 * @since 2018, March 21
 */

public class SubjectFragment extends Fragment {

    @Inject
    DbManager manager;
    @BindView(R.id.expandable_list)
    ExpandableListView expandableListView;
    private String category;
    private ItemClickListener itemClickListener;
    private RecyclerArrayAdapter<Book> adapter;

    public static SubjectFragment newInstance(String category) {
        SubjectFragment fragment = new SubjectFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dictionary.getAppComponent().inject(this);
        if (getArguments() != null) {
            this.category = getArguments().getString("category");
        }
        itemClickListener = new ItemClickListener(position -> {
            BookActivity.start(getContext(), adapter.getItem(position));
            Log.d("book", adapter.getItem(position).toString());

        });

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
    }
}
