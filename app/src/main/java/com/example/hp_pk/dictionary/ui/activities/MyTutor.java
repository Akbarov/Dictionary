package com.example.hp_pk.dictionary.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.manager.Subjects;
import com.example.hp_pk.dictionary.presentation.presenter.MyTutorPresenter;
import com.example.hp_pk.dictionary.presentation.view.MyTutorView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @auther root
 * @since 1/29/18.
 */

public class MyTutor extends MvpAppCompatActivity implements MyTutorView {


    @InjectPresenter
    MyTutorPresenter presenter;

    public static void start(Context context) {
        context.startActivity(new Intent(context, MyTutor.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_tutor);
        ButterKnife.bind(this);
        updateSubjects();

    }

    private void updateSubjects() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("all");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                List<Subjects> subjects = new ArrayList<>();
                while (iterator.hasNext()) {
                    DataSnapshot snapshot = iterator.next();
                    subjects.add(new Subjects(snapshot.getKey(), snapshot.getValue(String.class)));
                }
                presenter.setAllSubjects(subjects);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void setAllSubjects(List<Subjects> subjects) {
        for (int i = 0; i < subjects.size(); i++) {
            Log.d("sss", subjects.get(i).getSubject());
        }
    }
}
