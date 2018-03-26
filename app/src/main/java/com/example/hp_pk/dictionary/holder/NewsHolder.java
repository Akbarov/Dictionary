package com.example.hp_pk.dictionary.holder;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.database.News;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * @author Zohidjon
 * @since 2018, March 26
 */

public class NewsHolder extends BaseViewHolder<News> {

    private TextView header;
    private ImageView baseImage, likeYou;
    private TextView likeNumber, description;

    private Context context;

    public NewsHolder(ViewGroup parent, Context context) {
        super(parent, R.layout.news_item_layout);
        this.context = context;
        header = $(R.id.header);
        baseImage = $(R.id.image);
        likeNumber = $(R.id.like_number);
        description = $(R.id.description);
        likeYou = $(R.id.like_you);
    }

    @Override
    public void setData(News data) {
        super.setData(data);

    }
}
