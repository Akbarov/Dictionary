package com.example.hp_pk.dictionary.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.hp_pk.dictionary.Dictionary;
import com.example.hp_pk.dictionary.database.Book;
import com.example.hp_pk.dictionary.database.Books;
import com.example.hp_pk.dictionary.database.DbManager;
import com.example.hp_pk.dictionary.presentation.view.BooksListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.Iterator;
import java.util.List;

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

    public BooksListPresenter() {
        stateView = getViewState();
        Dictionary.getAppComponent().inject(this);
    }

    public void setAdapter(RecyclerArrayAdapter<Book> adapter) {
        this.adapter = adapter;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("books");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    DataSnapshot snapshot = iterator.next();
                    Book book = snapshot.getValue(Book.class);
                    adapter.add(book);
                    manager.setBook(book);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        adapter.notifyDataSetChanged();
    }

}
