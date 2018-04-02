package com.example.hp_pk.dictionary.holder;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.database.Photo;
import com.example.hp_pk.dictionary.model.GlideApp;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * @auther ZOHIDJON
 * @since 2/28/18.
 */

public class PhotoViewHolder extends BaseViewHolder<Photo> {

    private Context context;
    private ImageView photoImage;
    private TextView name;

    public PhotoViewHolder(ViewGroup parent, Context context) {
        super(parent, R.layout.row_photo);
        this.context = context;
        photoImage = $(R.id.image);
        name = $(R.id.name);

    }

    @Override
    public void setData(Photo data) {
        super.setData(data);
        GlideApp.with(context)
                .load(data.getDownloadUrl())
                .centerCrop()
                .placeholder(R.drawable.result)
                .into(photoImage);
        name.setText(data.getName());
    }
}
