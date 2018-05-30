package com.example.hp_pk.dictionary.listeners;

import android.widget.ImageView;

import com.example.hp_pk.dictionary.database.News;

/**
 * @auther root
 * @since 2/1/18.
 */

public interface OnNewsItemClickListener {

    void onItemClick(News news, ImageView imageView);


}
