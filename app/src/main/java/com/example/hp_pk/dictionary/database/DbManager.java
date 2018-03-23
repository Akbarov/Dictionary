package com.example.hp_pk.dictionary.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

import java.util.List;

/**
 * @auther ZOHIDJON
 * @since 2/20/18.
 */

public class DbManager {
    Context context;
    private DaoSession session;

    public DbManager(Context context) {
        this.context = context;
        makeDatabase();

    }

    private void makeDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "local.db");
        Database db = helper.getEncryptedWritableDb("MyPassword");
        session = new DaoMaster(db).newSession();
    }

    public DaoSession getSession() {
        if (session == null) {
            makeDatabase();
        }
        return session;
    }

    public void setSubject(Subject subject) {
        session.getSubjectDao().insertOrReplace(subject);
    }

    public List<Subject> getAllSubjects() {
        return session.getSubjectDao().loadAll();
    }

    public void setBook(Book book) {
        session.getBookDao().insertOrReplace(book);
    }

    public void setBook(List<Book> books) {
        for (Book book : books) {
            setBook(book);
        }
    }

    public List<Book> getBooks() {
        return session.getBookDao().loadAll();
    }

    public List<Book> getBooksByCategory(String category) {
        return session.getBookDao().queryRaw("WHERE T.CATEGORY =?", category.toLowerCase());
    }

    public List<Book> getBookByNameOrAuthor(String text) {
        if (text.isEmpty()) return getBooks();
        else
            return session.getBookDao().queryRaw("WHERE T.NAME LIKE ? OR T.AUTHOR LIKE ?", text + "%", text + "%");
    }

    public void setCategory(Categories category) {
        session.getCategoriesDao().insertOrReplace(category);
    }

    public List<Categories> getCategories() {
        return session.getCategoriesDao() != null ? session.getCategoriesDao().loadAll() : null;
    }

    public List<Categories> getCategoriesByMainCategory(String mainCategory) {
        return session.getCategoriesDao().queryRaw("WHERE T.MAIN_CATEGORY =?", mainCategory);
    }
}
