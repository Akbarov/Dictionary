package com.example.hp_pk.dictionary.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.hp_pk.dictionary.database.Book;

/**
 * @auther ZOHIDJON
 * @since 2/28/18.
 */
@StateStrategyType(SkipStrategy.class)
public interface BooksListView extends MvpView {

    void startBookActivity(Book book);

}
