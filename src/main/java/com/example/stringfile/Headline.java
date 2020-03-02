package com.example.stringfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.stringfile.adapter.MyRecycleViewAdapter;
import com.example.stringfile.model.Note;
import com.example.stringfile.storage.NoteStorage;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Headline extends AppCompatActivity  implements View.OnClickListener {

    private Button back;

    private EditText headline, message;

    private Intent extras;
    private Bundle bundle;
    private Boolean editing = false;

    private final static String notes = "notes";
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headline);

        back = findViewById(R.id.btn_back);
        back.setOnClickListener(this);

        headline = findViewById(R.id.headline);
        message = findViewById(R.id.message);

        extras = getIntent();
        bundle = extras.getExtras();
        if(bundle != null) {
            headline.setText(extras.getExtras().getString(MyRecycleViewAdapter.headline));
            message.setText(extras.getExtras().getString(MyRecycleViewAdapter.message));
            editing = true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                if(!editing) {
                    NoteStorage.getList().add(new Note(headline.getText().toString(), message.getText().toString()));
                    NoteStorage.saveNotesToFile();
                    addNewNote(new Note(headline.getText().toString(), message.getText().toString()));
                    finish();
                } else {
                    NoteStorage.getList().set(extras.getExtras().getInt(MyRecycleViewAdapter.pos), new Note(headline.getText().toString(), message.getText().toString()));
                    NoteStorage.saveNotesToFile();
                    finish();
                }
        }
    }

    private void addNewNote(Note note) {
        DocumentReference docRef = db.collection(notes).document();
        Map<String, String> map = new HashMap<>();
        map.put("head", note.headline);
        map.put("body", note.body);
        docRef.set(map);
    }
}
