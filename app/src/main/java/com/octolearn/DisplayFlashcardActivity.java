package com.octolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayFlashcardActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Flashcard> flashcards;
    private Flashcard currentFlashcard;
    private TextView word;
    private boolean isTranslation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_flashcard);

        Bundle b = getIntent().getExtras();
        if(b != null)
        {
            flashcards = (ArrayList<Flashcard>) b.getSerializable("flashcards");
        }

        word = (TextView) findViewById(R.id.word);

        nextFlashcard();
        isTranslation = false;

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(this);
        Button correct = (Button) findViewById(R.id.incorrect);
        correct.setOnClickListener(this);
        Button incorrect = (Button) findViewById(R.id.correct);
        incorrect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                if(isTranslation) {
                    word.setText(currentFlashcard.getWord());
                }
                else {
                    word.setText(currentFlashcard.getTranslation());
                }
                isTranslation = !isTranslation;
                break;
            case R.id.correct:
                if(flashcards.isEmpty()){
                    startActivity(new Intent(DisplayFlashcardActivity.this, NoFlashcardsLeftActivity.class));
                }
                else{
                   nextFlashcard();
                }
                break;
            case R.id.incorrect:
                flashcards.add(currentFlashcard);
                nextFlashcard();
                break;
        }
    }

    public void nextFlashcard(){
        currentFlashcard = flashcards.get(0);
        flashcards.remove(0);
        word.setText(currentFlashcard.getWord());
    }
}