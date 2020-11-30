package com.octolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CreateFlashcardActivity extends AppCompatActivity {

    private EditText wordEditText;
    private EditText translationEditText;
    private EditText sampleEditText;
    private EditText transcriptionEditText;

    private String word;
    private String translation;
    private String sample;
    private String transcription;

    private String catalogName = "animals";
    private WordsSQLiteDB dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flashcard);

        dataBase = new WordsSQLiteDB(this);

        wordEditText = (EditText) findViewById(R.id.foreignWordPlainText);
        translationEditText = (EditText) findViewById(R.id.nativeWordPlainText);
        sampleEditText = (EditText) findViewById(R.id.foreignWordPlainText);
        transcriptionEditText = (EditText) findViewById(R.id.nativeWordPlainText);

        Button addButton = (Button) findViewById(R.id.add_flashcard);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word = (wordEditText.getText().toString().equals(""))? null : wordEditText.getText().toString();
                translation = (translationEditText.getText().toString().equals(""))? null : translationEditText.getText().toString();
                sample = (sampleEditText.getText().toString().equals(""))? null : sampleEditText.getText().toString();
                transcription = (transcriptionEditText.getText().toString().equals(""))? null : transcriptionEditText.getText().toString();

                if (dataBase.addFlashcard(word, translation, sample, transcription, catalogName))
                {
                    Toast.makeText(CreateFlashcardActivity.this, "Flashcard Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CreateFlashcardActivity.this, ListOfWordsActivity.class));
                }
                else
                    if (word == null && translation == null)
                        Toast.makeText(CreateFlashcardActivity.this, "Word and its translation cannot be empty", Toast.LENGTH_SHORT).show();
                    else if (word == null)
                        Toast.makeText(CreateFlashcardActivity.this, "Word cannot be empty", Toast.LENGTH_SHORT).show();
                    else if (translation == null)
                        Toast.makeText(CreateFlashcardActivity.this, "Translation cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }
}