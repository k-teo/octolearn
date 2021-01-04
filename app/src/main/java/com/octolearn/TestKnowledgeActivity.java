package com.octolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TestKnowledgeActivity extends AppCompatActivity {

    private ArrayList<Flashcard> flashcards;
    private Flashcard currentFlashcard;
    private TextView word;
    private TextView answer;
    private TextView wrongAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_knowledge);

        Bundle b = getIntent().getExtras();
        if(b != null)
        {
            flashcards = (ArrayList<Flashcard>) b.getSerializable("flashcards");
        }

        word = findViewById(R.id.word_test);
        answer = findViewById(R.id.answer);
        wrongAnswer = findViewById(R.id.wrong_answer);

        nextFlashcard();

        final Handler handler = new Handler(Looper.getMainLooper());

        Button button = findViewById(R.id.check);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flashcards.isEmpty()){
                    startActivity(new Intent(TestKnowledgeActivity.this, NoFlashcardsLeftActivity.class));
                }
                else if(!currentFlashcard.getTranslation().equals(answer.getText().toString())){
                    wrongAnswer.setText(currentFlashcard.getTranslation());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            wrongAnswer.setText("");
                            nextFlashcard();
                        }}, 1000);
                }
                else{
                    nextFlashcard();
                }
            }
        });
    }
    public void nextFlashcard(){
        currentFlashcard = flashcards.get(0);
        flashcards.remove(0);
        word.setText(currentFlashcard.getWord());
        answer.setText("");
    }
}