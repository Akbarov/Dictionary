package com.example.hp_pk.dictionary.ui.activities.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hp_pk.dictionary.Dictionary;
import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.database.DbManager;
import com.example.hp_pk.dictionary.database.News;
import com.example.hp_pk.dictionary.holder.NewsHolder;
import com.example.hp_pk.dictionary.listeners.OnNewsItemClickListener;
import com.example.hp_pk.dictionary.manager.PrefManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Zohidjon
 * @since 2018, March 21
 */

public class NewsFragment extends Fragment {

    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @Inject
    DbManager manager;
    @Inject
    PrefManager pref;
    private RecyclerArrayAdapter<News> adapter;
    private long updatedLimit = 86400000; // 1 day
    private OnNewsItemClickListener listener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Dictionary.getAppComponent().inject(this);
        createAdapter();
        if (System.currentTimeMillis() - pref.getLanguageType() > updatedLimit) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("news");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot newsObject : dataSnapshot.getChildren()) {
                        News news = newsObject.getValue(News.class);
                        if (news != null) {
                            news.setId(newsObject.getKey());
                            news.setLikeYou(false);
                        }
                        manager.setNews(news);
                        updateAdapter();
                    }
                    pref.setLastUpdateNews(System.currentTimeMillis());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else {
            updateAdapter();
        }
    }

    private void updateAdapter() {
        adapter.clear();
        adapter.addAll(manager.getNewsList());
        adapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapterWithProgress(adapter);
    }

    private void createAdapter() {

        listener = (news, imageView) -> {

            Uri bmpUri = getLocalBitmapUri(imageView);
            if (bmpUri != null) {
                // Construct a ShareIntent with link to image
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                shareIntent.setType("image/*");
                // Launch sharing dialog for image
                startActivity(Intent.createChooser(shareIntent, "Share Image"));
            } else {
                // ...sharing failed, handle error
            }
        };

        adapter = new RecyclerArrayAdapter<News>(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new NewsHolder(parent, getContext(), listener);
            }
        };
    }

    public Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable) {
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {

            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {

                File file = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
                file.getParentFile().mkdirs();
                FileOutputStream out = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.close();
                bmpUri = Uri.fromFile(file);
            }
            else {

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bmpUri;
    }

}
