package com.octolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditFlashcardActivity extends AppCompatActivity {

    private EditText wordEditText;
    private EditText translationEditText;
    private EditText sampleEditText;
    private EditText transcriptionEditText;

    private String word;
    private String translation;
    private String sample;
    private  String transcription;

    private String newWord;
    private String newTranslation;
    private String newSample;
    private String newTranscription;

    private String catalogName = "animals";
    private WordsSQLiteDB dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flashcard);

        dataBase = new WordsSQLiteDB(this);

        wordEditText = (EditText) findViewById(R.id.foreignWordPlainText);
        translationEditText = (EditText) findViewById(R.id.nativeWordPlainText);
        sampleEditText = (EditText) findViewById(R.id.foreignWordPlainText);
        transcriptionEditText = (EditText) findViewById(R.id.nativeWordPlainText);

        Bundle b = getIntent().getExtras();
        if(b != null)
        {
            word = b.getString("word");
            translation = b.getString("translation");
            sample = b.getString("sample");
            transcription = b.getString("transcription");
        }

        Button deleteButton = (Button) findViewById(R.id.remove_button);
        Button editButton = (Button) findViewById(R.id.save_button);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBase.deleteFlashcard(word, translation))
                {
                    Toast.makeText(EditFlashcardActivity.this, "Flashcard deleted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditFlashcardActivity.this, ListOfWordsActivity.class));
                }
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newWord = (wordEditText.getText().toString().equals("")) ? word : wordEditText.getText().toString();
                newTranslation = (translationEditText.getText().toString().equals("")) ? translation : translationEditText.getText().toString();
                newSample = (sampleEditText.getText().toString().equals("")) ? sample : sampleEditText.getText().toString();
                newTranscription = (transcriptionEditText.getText().toString().equals("")) ? transcription : transcriptionEditText.getText().toString();

                //Toast.makeText(EditFlashcardActivity.this, newWord + " " + newTranslation, Toast.LENGTH_LONG).show();
                //startActivity(new Intent(EditFlashcardActivity.this, ListOfWordsActivity.class));

                if (dataBase.updateFlashcard(word, translation, newWord, newTranslation, newSample, newTranscription, "animals"))
                {
                    Toast.makeText(EditFlashcardActivity.this, word + translation, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditFlashcardActivity.this, ListOfWordsActivity.class));
                }
            }
        });

    }
}