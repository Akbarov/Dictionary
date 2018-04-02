package com.example.hp_pk.dictionary.holder;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aloj.progress.DownloadProgressView;
import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.database.Movie;
import com.example.hp_pk.dictionary.model.GlideApp;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * @auther ZOHIDJON
 * @since 2/28/18.
 */

public class VideoViewHolder extends BaseViewHolder<Movie> {

    private Context context;
    private ImageView audioImage;
    private TextView name;
    private DownloadProgressView downloadProgressView;

    public VideoViewHolder(ViewGroup parent, Context context) {
        super(parent, R.layout.row_video);
        this.context = context;
        audioImage = $(R.id.image);
        name = $(R.id.name);
        downloadProgressView = $(R.id.download);

    }

    @Override
    public void setData(Movie data) {
        super.setData(data);
        GlideApp.with(context)
                .load(data.getImageUrl())
                .centerCrop()
                .placeholder(R.drawable.video)
                .into(audioImage);
        name.setText(data.getName());

    }
}
