package com.example.hp_pk.dictionary.holder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.classes.WordClass;
import com.example.hp_pk.dictionary.listeners.OnItemClickListener;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * @auther root
 * @since 1/26/18.
 */

public class WordViewHolder extends BaseViewHolder<WordClass> {

    private TextView word;
    private ImageView favoriteIcon;
    private OnItemClickListener clickListener;

    public WordViewHolder(ViewGroup parent, OnItemClickListener listener) {
        super(parent, R.layout.row_dictionary);
        clickListener = listener;
        word = $(R.id.word);
        favoriteIcon = $(R.id.favorite_icon);
    }

    @Override
    public void setData(WordClass data) {
        if (data == null) return;
        word.setText(data.getWord());
        favoriteIcon.setImageResource(data.isFavorite() ? R.drawable.ic_star_24dp : R.drawable.ic_star_half_24dp);
        favoriteIcon.setOnClickListener(view -> {
            if (clickListener != null) {
                data.setFavorite(!data.isFavorite());
                boolean isFavorite = data.isFavorite();
                favoriteIcon.setImageResource(isFavorite ? R.drawable.ic_star_24dp : R.drawable.ic_star_half_24dp);
                clickListener.onItemClick(getAdapterPosition(), data);
            }
        });
    }
}
