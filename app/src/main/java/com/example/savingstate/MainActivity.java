package com.example.savingstate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText notesEditText;
    Button settingsButton;
    private static final int SETTINGS_INFO = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesEditText = (EditText) findViewById(R.id.notesEditText);
        if(savedInstanceState != null) {
            String notes = savedInstanceState.getString("Notes");
            notesEditText.setText(notes);
        }

        String spNotes = getPreferences(Context.MODE_PRIVATE).getString("Notes", "Empty");
        if(!spNotes.equals("Empty")) {
            notesEditText.setText(spNotes);
        }

        settingsButton = (Button) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("applicationContext---->", getApplicationContext().toString());
                Log.d("this---->", this.toString());

                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivityForResult(intent, SETTINGS_INFO);

            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("Notes", notesEditText.getText().toString());
        super.onSaveInstanceState(outState);
    }

    public void saveSetting() {
        SharedPreferences.Editor spEditor = getPreferences(Context.MODE_PRIVATE).edit();
        spEditor.putString("Notes", notesEditText.getText().toString());
        spEditor.commit();
    }

    @Override
    protected void onStop() {
        saveSetting();
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SETTINGS_INFO) {
            updateNoteText();
        }

    }

    public void updateNoteText() {
        Log.d("this---->", this.toString());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.getBoolean("pref_text_bold", false)){
            notesEditText.setTypeface(null, Typeface.BOLD);
        } else {
            notesEditText.setTypeface(null, Typeface.NORMAL);
        }

        String textSizeStr = sharedPreferences.getString("pref_text_size", "16");
        float textSizeFloat = Float.parseFloat(textSizeStr);
        notesEditText.setTextSize(textSizeFloat);
    }






}
