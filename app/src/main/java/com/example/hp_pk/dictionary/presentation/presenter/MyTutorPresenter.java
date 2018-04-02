package com.example.hp_pk.dictionary.presentation.presenter;

import android.support.v4.app.FragmentPagerAdapter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.hp_pk.dictionary.Dictionary;
import com.example.hp_pk.dictionary.adapters.LessonViewPagerAdapter;
import com.example.hp_pk.dictionary.adapters.MyTutorViewPagerAdapter;
import com.example.hp_pk.dictionary.database.Audio;
import com.example.hp_pk.dictionary.database.Categories;
import com.example.hp_pk.dictionary.database.DbManager;
import com.example.hp_pk.dictionary.database.LessonItem;
import com.example.hp_pk.dictionary.database.Movie;
import com.example.hp_pk.dictionary.database.Photo;
import com.example.hp_pk.dictionary.database.TestItem;
import com.example.hp_pk.dictionary.manager.PrefManager;
import com.example.hp_pk.dictionary.presentation.view.BooksListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;

/**
 * @auther ZOHIDJON
 * @since 2/12/18.
 */
@InjectViewState
public class MyTutorPresenter extends MvpPresenter<BooksListView> {

    @Inject
    DbManager manager;
    @Inject
    PrefManager pref;
    private BooksListView stateView;
    private long updatedLimit = 86400000; // 1 day
    private String category;

    public MyTutorPresenter() {
        stateView = getViewState();
        Dictionary.getAppComponent().inject(this);
    }

    private boolean needToUpdate(String category) {
        return System.currentTimeMillis() - pref.getLastMyTutorUpdated(category) > updatedLimit;
    }

    public void updateBooksFromServer(String category) {
        this.category = category;
        if (!needToUpdate(category)) return;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("My Center/" + category);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if ("Extracurricular".equals(category)) {
                    parseExtracurricular(dataSnapshot);
                } else if ("Lessons".equals(category)) {
                    parseLessons(dataSnapshot);
                } else if ("Tests".equals(category)) {
                    parseTest(dataSnapshot);
                }
                if (pref.getLastBookUpdated(category) == 0) {
                    stateView.updateCategories();
                }
                pref.setLastMyTutorUpdated(category, System.currentTimeMillis());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                stateView.getBookListCanceled(databaseError.getMessage());
            }
        });
    }

    private void parseExtracurricular(DataSnapshot dataSnapshot) {
        int count = 0;
        for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()) {
            count = 0;
            if ("Audios".equals(categorySnapshot.getKey())) {
                parseAudios(categorySnapshot);
            } else if ("Movies".equals(categorySnapshot.getKey())) {
                parseMovies(categorySnapshot);
            } else if ("Photo Albums".equals(categorySnapshot.getKey())) {
                parsePhotos(categorySnapshot);
            }
            Categories categories = new Categories(categorySnapshot.getKey(), count, category);
            manager.setCategory(categories);
        }

        if (pref.getLastBookUpdated(category) == 0) {
            stateView.updateCategories();
        }
        pref.setLastBookUpdated(category, System.currentTimeMillis());
    }

    private void parseLessons(DataSnapshot lessonsSnapshot) {
        for (DataSnapshot subject : lessonsSnapshot.getChildren()) {
            for (DataSnapshot level : subject.getChildren()) {
                for (DataSnapshot lesson : level.getChildren()) {
                    for (DataSnapshot item : lesson.getChildren()) {
                        LessonItem lessonItem = item.getValue(LessonItem.class);
                        if (lessonItem != null) {
                            lessonItem.setLesson(lesson.getKey());
                            lessonItem.setLevel(level.getKey());
                            lessonItem.setSubject(subject.getKey());
                            manager.setLessonItem(lessonItem);
                        }
                    }
                }
            }
        }
    }

    private void parseTest(DataSnapshot lessonsSnapshot) {
        for (DataSnapshot subject : lessonsSnapshot.getChildren()) {
            for (DataSnapshot level : subject.getChildren()) {
                for (DataSnapshot item : level.getChildren()) {
                    TestItem testItem = item.getValue(TestItem.class);
                    if (testItem != null) {
                        testItem.setLevel(level.getKey());
                        testItem.setSubject(subject.getKey());
                        manager.setTestItem(testItem);
                    }
                }
            }
        }
    }

    private void parsePhotos(DataSnapshot photoSnapshot) {
        for (DataSnapshot item : photoSnapshot.getChildren()) {
            Photo photo = item.getValue(Photo.class);
            photo.setId(item.getKey());
            manager.setPhoto(photo);
        }
    }

    private void parseMovies(DataSnapshot moviesSnapshot) {
        for (DataSnapshot item : moviesSnapshot.getChildren()) {
            Movie movie = item.getValue(Movie.class);
            movie.setId(item.getKey());
            manager.setMovie(movie);
        }
    }

    private void parseAudios(DataSnapshot audioSnapshot) {
        for (DataSnapshot item : audioSnapshot.getChildren()) {
            Audio audio = item.getValue(Audio.class);
            assert audio != null;
            audio.setId(item.getKey());
            manager.setAudio(audio);
        }
    }

    public FragmentPagerAdapter getPagerAdapter(android.support.v4.app.FragmentManager fragmentManager) {
        if (category.equals("Lessons")) {
            return new LessonViewPagerAdapter(fragmentManager, manager.getCategoriesByMainCategory(category));
        } else
            return new MyTutorViewPagerAdapter(fragmentManager, manager.getCategoriesByMainCategory(category));
    }

    public boolean hasCategory() {
        if (pref.getLastMyTutorUpdated(category) == 0)
            return false;
        else
            return manager.getCategoriesByMainCategory(category) != null;
    }
}

