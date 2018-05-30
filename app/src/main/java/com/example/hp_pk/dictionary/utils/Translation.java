package com.example.hp_pk.dictionary.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.classes.WordClass;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Translation extends Dialog {

    private Context context;
    private WordClass wordClass;
    @BindView(R.id.word)
    TextView word;
    @BindView(R.id.favorite_icon)
    ImageView favoriteIcon;
    @BindView(R.id.meaning)
    TextView meaning;

    public Translation(Context context, WordClass wordClass) {
        super(context);
        this.context = context;
        this.wordClass = wordClass;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.translation_layout);
        ButterKnife.bind(this);
        word.setText(wordClass.getWord());
        meaning.setText(wordClass.getMeaning());
        favoriteIcon.setImageResource(wordClass.isFavorite() ? R.drawable.ic_star_24dp : R.drawable.ic_star_half_24dp);
    }
}
