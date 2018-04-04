package com.example.hp_pk.dictionary.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.hp_pk.dictionary.Dictionary;
import com.example.hp_pk.dictionary.adapters.MyViewPagerAdapter;
import com.example.hp_pk.dictionary.database.Book;
import com.example.hp_pk.dictionary.database.Categories;
import com.example.hp_pk.dictionary.database.DbManager;
import com.example.hp_pk.dictionary.listeners.ItemClickListener;
import com.example.hp_pk.dictionary.manager.PrefManager;
import com.example.hp_pk.dictionary.presentation.view.BooksListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import javax.inject.Inject;

/**
 * @auther ZOHIDJON
 * @since 2/28/18.
 */
@InjectViewState
public class BooksListPresenter extends MvpPresenter<BooksListView> {

    @Inject
    DbManager manager;
    @Inject
    PrefManager pref;
    private BooksListView stateView;
    private RecyclerArrayAdapter<Book> adapter;
    private ItemClickListener itemClickListener;
    private long updatedLimit = 86400000; // 1 day
    private String bookCategory;

    public BooksListPresenter() {
        stateView = getViewState();
        Dictionary.getAppComponent().inject(this);
        setUpListener();
    }

    public void setAdapter(RecyclerArrayAdapter<Book> adapter, String bookCategory) {
        this.bookCategory = bookCategory;
        this.adapter = adapter;
        adapter.setOnItemClickListener(itemClickListener);
        if (System.currentTimeMillis() - pref.getLastBookUpdated(bookCategory) > updatedLimit) {
            updateBooksFromServer();
        } else {
            searchWithFilter("");
        }
//        updateBooksFromServer();

    }

    private void updateBooksFromServer() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("books/" + bookCategory);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    int count = 0;
                    for (DataSnapshot bookSnapshot : snapshot.getChildren()) {
                        Book book = bookSnapshot.getValue(Book.class);
                        if (book != null) {
                            book.setBookKey(bookSnapshot.getKey());
                            book.setMainCategory(bookCategory);
                            book.setCategory(snapshot.getKey());
                        }
                        manager.setBook(book);
                        count++;
                    }
                    Categories categories = new Categories(snapshot.getKey(), count, bookCategory);
                    manager.setCategory(categories);
                }
                if (pref.getLastBookUpdated(bookCategory) == 0) {
                    pref.setLastBookUpdated(bookCategory, System.currentTimeMillis());
                    stateView.updateCategories();
                } else {
                    pref.setLastBookUpdated(bookCategory, System.currentTimeMillis());
                }
                searchWithFilter("");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                stateView.getBookListCanceled(databaseError.getMessage());
            }
        });
    }

    public MyViewPagerAdapter getPagerAdapter(android.support.v4.app.FragmentManager fragmentManager) {
        return new MyViewPagerAdapter(fragmentManager, manager.getCategoriesByMainCategory(bookCategory));
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
        adapter.addAll(manager.getBookByNameOrAuthor(bookCategory, filter));
        adapter.notifyDataSetChanged();
    }

    public boolean hasCategory() {
        if (pref.getLastBookUpdated(bookCategory) == 0)
            return false;
        else
            return manager.getCategoriesByMainCategory(bookCategory) != null;
    }
}
