package com.octolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class WayOfLearningActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<Flashcard> flashcards;
    private WordsSQLiteDB dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way_of_learning);

        Button displayFlashcards = findViewById(R.id.flashcards);
        displayFlashcards.setOnClickListener(this);
        Button testKnowledge = findViewById(R.id.testKnowledge);
        testKnowledge.setOnClickListener(this);
        Button miniGames = findViewById(R.id.miniGames);
        miniGames.setOnClickListener(this);
        Button listOfWords = findViewById(R.id.wordsListView);
        listOfWords.setOnClickListener(this);

        dataBase = new WordsSQLiteDB(this);
        flashcards = new ArrayList<>();


    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Bundle b = new Bundle();
        switch (v.getId()) {
            case R.id.flashcards:
                getFlashcards();
                if(flashcards.isEmpty()){
                    startActivity(new Intent(WayOfLearningActivity.this, NoFlashcardsLeftActivity.class));
                    break;
                }
                intent = new Intent(WayOfLearningActivity.this, DisplayFlashcardActivity.class);
                b.putSerializable("flashcards", flashcards);
                intent.putExtras(b);
                startActivity(intent);
                break;
            case R.id.testKnowledge:
                getFlashcards();
                if(flashcards.isEmpty()){
                    startActivity(new Intent(WayOfLearningActivity.this, NoFlashcardsLeftActivity.class));
                    break;
                }
                intent = new Intent(WayOfLearningActivity.this, TestKnowledgeActivity.class);
                b.putSerializable("flashcards", flashcards);
                intent.putExtras(b);
                startActivity(intent);
                break;
            case R.id.miniGames:
                Toast.makeText(WayOfLearningActivity.this, "Not available", Toast.LENGTH_SHORT).show();
                break;
            case R.id.wordsListView:
                startActivity(new Intent(WayOfLearningActivity.this, ListOfWordsActivity.class));
                break;
        }
    }

    public void getFlashcards(){
        Cursor cursor = dataBase.getAllFlashcards();

        if (cursor.moveToFirst()) {
            do {
                flashcards.add(new Flashcard(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
            } while (cursor.moveToNext());
        }
    }

}