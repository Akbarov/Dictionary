package com.example.hp_pk.dictionary.holder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private Button button;
    boolean downloaded = false;
    private ImageView play;

    public VideoViewHolder(ViewGroup parent, Context context) {
        super(parent, R.layout.row_video);
        this.context = context;
        audioImage = $(R.id.image);
        name = $(R.id.name);
        downloadProgressView = $(R.id.download);
        button = $(R.id.down);
        play = $(R.id.play);


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
        downloadProgressView.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Played", Toast.LENGTH_SHORT).show();
        });
        downloaded = Utils.hasFile(Utils.getRootDirPath(context) + "/" + data.getName());

        play.setVisibility(downloaded ? View.VISIBLE : View.GONE);
        play.setOnClickListener(view -> {
            String newVideoPath = Utils.getRootDirPath(context) + "/" + data.getName();
            playVideo(newVideoPath);
        });
        downloadProgressView.setVisibility(downloaded ? View.GONE : View.VISIBLE);
        button.setOnClickListener(v -> {
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
                                downloadProgressView.setProgress(progress.currentBytes / (progress.totalBytes * 1F));
                                Log.d("percente", Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }).start(new OnDownloadListener() {
                                @Override
                                public void onDownloadComplete() {
                                    Log.d("download", "completed");
                                    data.setDownloadState(1);
                                }

                                @Override
                                public void onError(Error error) {
                                    data.setDownloadState(-1);
                                    Log.d("download", "error");
                                }
                            });
                    data.setDownloadState(0);
                    break;
                case 0:
                    PRDownloader.cancel(downloadId);
                    data.setDownloadState(-1);
                    break;
                case 1:
                    Log.d("downloaded", "already downloaded");

                    break;
            }
        });
    }

    private void playVideo(String newVideoPath) {
        if (Utils.hasFile(newVideoPath)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(newVideoPath));
            intent.setDataAndType(Uri.parse(newVideoPath), "video/*");
            context.startActivity(intent);
        }
    }
}
