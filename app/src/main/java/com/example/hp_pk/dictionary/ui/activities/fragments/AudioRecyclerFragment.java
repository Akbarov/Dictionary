package com.example.hp_pk.dictionary.ui.activities.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hp_pk.dictionary.Dictionary;
import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.database.Audio;
import com.example.hp_pk.dictionary.database.DbManager;
import com.example.hp_pk.dictionary.database.Movie;
import com.example.hp_pk.dictionary.database.Photo;
import com.example.hp_pk.dictionary.holder.AudioViewHolder;
import com.example.hp_pk.dictionary.holder.PhotoViewHolder;
import com.example.hp_pk.dictionary.holder.VideoViewHolder;
import com.example.hp_pk.dictionary.listeners.ItemClickListener;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import javax.inject.Inject;

/**
 * @author Zohidjon
 * @since 2018, March 21
 */

public class AudioRecyclerFragment extends Fragment {

    @Inject
    DbManager manager;
    EasyRecyclerView recyclerView;
    private String category = "";
    private ItemClickListener itemClickListener;
    private RecyclerArrayAdapter adapter;

    public static AudioRecyclerFragment newInstance(String category) {
        AudioRecyclerFragment fragment = new AudioRecyclerFragment();
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
            Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
            if (adapter.getItem(position) instanceof Audio) {
                Audio audio = (Audio) adapter.getItem(position);
                Toast.makeText(getContext(), audio.getDownloadUrl(), Toast.LENGTH_SHORT).show();

            } else if (adapter.getItem(position) instanceof Movie) {
                Movie movie = (Movie) adapter.getItem(position);
            }

        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_layout, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (category.equals("Audios")) {
            adapter = new RecyclerArrayAdapter<Audio>(getContext()) {
                @Override
                public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                    return new AudioViewHolder(parent, getContext());
                }
            };
            recyclerView.setAdapterWithProgress(adapter);
            adapter.setOnItemClickListener(itemClickListener);
            adapter.addAll(manager.getAudioList());
            adapter.notifyDataSetChanged();
        } else if (category.equals("Movies")) {
            adapter = new RecyclerArrayAdapter<Movie>(getContext()) {
                @Override
                public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                    return new VideoViewHolder(parent, getContext());
                }
            };
            recyclerView.setAdapterWithProgress(adapter);
            adapter.setOnItemClickListener(itemClickListener);
            adapter.addAll(manager.getMovieList());
            adapter.notifyDataSetChanged();
        } else if (category.equals("Photo Albums")) {
            adapter = new RecyclerArrayAdapter<Photo>(getContext()) {
                @Override
                public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                    return new PhotoViewHolder(parent, getContext());
                }
            };
            recyclerView.setAdapterWithProgress(adapter);
            adapter.addAll(manager.getPhotoList());
            adapter.notifyDataSetChanged();
        }
    }
}
