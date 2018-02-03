package com.example.hp_pk.dictionary.listeners;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * @auther root
 * @since 1/29/18.
 */

public class ItemClickListener implements RecyclerArrayAdapter.OnItemClickListener {

    ItemClickedListener listener;

    public ItemClickListener(ItemClickedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(int position) {
        if (listener != null) {
            listener.clicked(position);
        }
    }

    public interface ItemClickedListener {
        void clicked(int position);
    }
}
