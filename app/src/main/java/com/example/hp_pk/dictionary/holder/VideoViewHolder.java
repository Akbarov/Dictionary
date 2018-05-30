package com.example.hp_pk.dictionary.holder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aloj.progress.DownloadProgressView;
import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.database.Movie;
import com.example.hp_pk.dictionary.model.GlideApp;
import com.example.hp_pk.dictionary.utils.Utils;
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
    private String dir;
    private int downloadId;

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
        dir = Utils.getRootDirPath(context);
        GlideApp.with(context)
                .load(data.getImageUrl())
                .centerCrop()
                .placeholder(R.drawable.video)
                .into(audioImage);
        name.setText(data.getName());
        downloadProgressView.setOnClickListener(v -> {
            switch (data.getDownloadState()) {
                case -1:
                     downloadId = PRDownloader.download(data.getDownloadUrl(), dir, data.getName())
                            .build()
                            .setOnStartOrResumeListener(() -> {

                            })
                            .setOnPauseListener(() -> {

                            })
                            .setOnCancelListener(() -> {

                            })
                            .setOnProgressListener(progress -> {
                                downloadProgressView.setProgress(progress.currentBytes / progress.totalBytes);
                            }).start(new OnDownloadListener() {
                                @Override
                                public void onDownloadComplete() {
                                    Log.d("download", "completed");
                                }

                                @Override
                                public void onError(Error error) {
                                    Log.d("download", "error");
                                }
                            });
                    data.setDownloadState(0);
                    break;
                case 0:
                    PRDownloader.cancel(downloadId);
                case 1:
                    Log.d("downloaded", "already downloaded");
            }
        });
    }
}
