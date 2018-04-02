package com.example.hp_pk.dictionary.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.hp_pk.dictionary.Dictionary;
import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.database.Book;
import com.example.hp_pk.dictionary.database.DbManager;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther root
 * @since 1/30/18.
 */

public class BookActivity extends MvpAppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener, OnPageErrorListener {

    @BindView(R.id.pdfView)
    PDFView pdfView;

    public static void start(Context context, Book book) {
        Intent intent = new Intent(context, BookActivity.class);
        intent.putExtra("book", book);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_layout);
        Dictionary.getAppComponent().inject(this);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123
            );
        }

        ButterKnife.bind(this);
        Log.d("book", "bookk");
        if (getIntent() != null && getIntent().getExtras() != null) {
            Book book = getIntent().getExtras().getParcelable("book");
            Log.d("book", " read");
            if (book != null)
                Log.d("book", book.toString());
            FileLoader.with(this)
                    .load(book.getBookUrl())
                    .fromDirectory("books", FileLoader.DIR_INTERNAL)
                    .asFile(new FileRequestListener<File>() {
                        @Override
                        public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                            File loadedFile = response.getBody();
                            readFromFile(loadedFile);
                        }

                        @Override
                        public void onError(FileLoadRequest request, Throwable t) {
                        }
                    });
        } else {
            Log.d("book", "can't read");
        }
//        AssetManager assetManager = getAssets();
//        InputStream in = null;
//        OutputStream out = null;
//        File file = new File(getFilesDir(), "ABC.pdf");
//        try {
//            in = assetManager.open("something");
//            out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);
//
//            copyFile(in, out);
//            in.close();
//            in = null;
//            out.flush();
//            out.close();
//            out = null;
//        } catch (Exception e) {
//            Log.e("tag", e.getMessage());
//        }

//        readFromFile(getFilesDir() + "/ABC.pdf");

//        pdfView.fromAsset("tarix.pdf")
//                .defaultPage(0)
//                .onPageChange(this)
//                .enableAnnotationRendering(true)
//                .onLoad(this)
//                .scrollHandle(new DefaultScrollHandle(this))
//                .spacing(10) // in dp
//                .onPageError(this)
//                .load();
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    private void readFromFile(File file) {
        pdfView.fromFile(file)
                .defaultPage(0)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .onPageError(this)
                .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
//        Toast.makeText(BookActivity.this, "Page changed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadComplete(int nbPages) {
//        Toast.makeText(BookActivity.this, "Completed", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPageError(int page, Throwable t) {
        Toast.makeText(BookActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
