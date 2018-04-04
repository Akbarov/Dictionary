package com.example.hp_pk.dictionary.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.hp_pk.dictionary.adapters.LessonViewPagerAdapter;
import com.example.hp_pk.dictionary.adapters.MyTutorViewPagerAdapter;
import com.example.hp_pk.dictionary.database.Book;
import com.example.hp_pk.dictionary.database.Subject;

import java.util.List;

/**
 * @auther ZOHIDJON
 * @since 2/12/18.
 */


@StateStrategyType(SkipStrategy.class)
public interface MyTutorView extends MvpView {

    void setUpPager();

    void startBookActivity(Book book);

    void getBookListCanceled(String message);

    void setAdapter(LessonViewPagerAdapter adapter);

    void setAdapter(MyTutorViewPagerAdapter adapter);
}
