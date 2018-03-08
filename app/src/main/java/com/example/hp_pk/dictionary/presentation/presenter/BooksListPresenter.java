package com.example.hp_pk.dictionary.presentation.presenter;

import android.os.Bundle;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.hp_pk.dictionary.Dictionary;
import com.example.hp_pk.dictionary.database.Book;
import com.example.hp_pk.dictionary.database.DbManager;
import com.example.hp_pk.dictionary.listeners.ItemClickListener;
import com.example.hp_pk.dictionary.manager.PrefManager;
import com.example.hp_pk.dictionary.presentation.view.BooksListView;
import com.example.hp_pk.dictionary.ui.activities.BookActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import javax.inject.Inject;

/**
 * @auther ZOHIDJON
 * @since 2/28/18.
 */
@InjectViewState
public class BooksListPresenter extends MvpPresenter<BooksListView> {

    private BooksListView stateView;
    private RecyclerArrayAdapter<Book> adapter;

    @Inject
    DbManager manager;

    @Inject
    PrefManager pref;

    private ItemClickListener itemClickListener;

    public BooksListPresenter() {
        stateView = getViewState();
        Dictionary.getAppComponent().inject(this);
        setUpListener();
    }

    public void setAdapter(RecyclerArrayAdapter<Book> adapter) {
        this.adapter = adapter;
        adapter.setOnItemClickListener(itemClickListener);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("books");
        Query query = reference.orderByKey().startAt("2");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book book = snapshot.getValue(Book.class);
                    book.setBookKey(snapshot.getKey());
                    adapter.add(book);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setUpListener() {
        itemClickListener = new ItemClickListener(position -> {
            Log.d("sss", position + "");
            Book book = adapter.getItem(position);
            Log.d("sss", book.getName());
            stateView.startBookActivity(book);
        });
    }

    public void searchWithFilter(String filter) {
        adapter.clear();
        adapter.addAll(manager.getBookByNameOrAuthor(filter));
    }

}
