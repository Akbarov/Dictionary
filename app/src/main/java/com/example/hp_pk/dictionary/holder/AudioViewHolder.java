package com.example.hp_pk.dictionary.holder;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.database.Audio;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;

/**
 * @auther ZOHIDJON
 * @since 2/28/18.
 */

public class AudioViewHolder extends BaseViewHolder<Audio> {

    private Context context;
    private RoundedImageView audioImage;
    private TextView name;

    public AudioViewHolder(ViewGroup parent, Context context) {
        super(parent, R.layout.row_audio);
        this.context = context;
        audioImage = $(R.id.book_image);
        name = $(R.id.name);

    }

    @Override
    public void setData(Audio data) {
        super.setData(data);
//        GlideApp.with(context)
//                .load(data.getImageUrl())
//                .centerCrop()
//                .placeholder(R.drawable.mp3)
//                .into(audioImage);
        name.setText(data.getName());
    }
}
