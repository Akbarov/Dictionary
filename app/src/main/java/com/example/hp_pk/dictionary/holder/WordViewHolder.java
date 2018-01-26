package com.example.hp_pk.dictionary.holder;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.WordClass;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by root on 1/26/18.
 */

public class WordViewHolder extends BaseViewHolder<WordClass> {

    private TextView word;
    private ImageView favoriteIcon;

    public WordViewHolder(ViewGroup parent) {
        super(parent, R.layout.row_dictionary);
        word = $(R.id.word);
        favoriteIcon = $(R.id.favorite_icon);
    }

    @Override
    public void setData(WordClass data) {
        if (data == null) return;
        word.setText(data.getWord());
        favoriteIcon.setImageResource(data.isFavorite() ? R.drawable.ic_star_24dp : R.drawable.ic_star_half_24dp);
        favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("sss", "Clicked");
            }
        });
    }
}
