package com.example.hp_pk.dictionary.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.hp_pk.dictionary.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther root
 * @since 1/30/18.
 */

public class BooksActivity extends MvpAppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener, OnPageErrorListener {

    @BindView(R.id.pdfView)
    PDFView pdfView;

    public static void start(Context context) {
        context.startActivity(new Intent(context, BooksActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_layout);
        ButterKnife.bind(this);
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

        pdfView.fromAsset("tarix.pdf")
                .defaultPage(0)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .onPageError(this)
                .load();
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    private void readFromFile(String pdfName) {
        File file = new File(pdfName);
        pdfView.fromFile(file)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .defaultPage(0)
                .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        Toast.makeText(BooksActivity.this, "Page changed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadComplete(int nbPages) {
        Toast.makeText(BooksActivity.this, "Completed", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPageError(int page, Throwable t) {
        Toast.makeText(BooksActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
