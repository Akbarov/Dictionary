package com.example.hp_pk.dictionary.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.hp_pk.dictionary.R;
import com.example.hp_pk.dictionary.database.Subject;
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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther root
 * @since 1/29/18.
 */

public class MyTutor extends MvpAppCompatActivity implements MyTutorView, View.OnClickListener {


    @InjectPresenter
    MyTutorPresenter presenter;

    @BindView(R.id.container)
    LinearLayout container;

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
                List<Subject> subjects = new ArrayList<>();
                while (iterator.hasNext()) {
                    DataSnapshot snapshot = iterator.next();
                    subjects.add(new Subject(Integer.parseInt(snapshot.getKey()), snapshot.getValue(String.class)));
                }
                presenter.setAllSubjects(subjects);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void setAllSubjects(List<Subject> subjects) {
        for (Subject subject : subjects) {
            Log.d("sss", subject.getSubject());
            View view = LayoutInflater.from(this).inflate(R.layout.button_layout, null);
            Button button = view.findViewById(R.id.button);
            button.setText(subject.getSubject());
            button.setTag(subject.getSubject());
            button.setOnClickListener(this);
            container.addView(view);
        }
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getBaseContext(), String.valueOf(view.getTag()), Toast.LENGTH_SHORT).show();
    }
}
