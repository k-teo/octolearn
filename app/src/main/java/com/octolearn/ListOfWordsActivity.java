package com.octolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;


public class ListOfWordsActivity extends AppCompatActivity {

    public static ListView wordsListView;
    public static ArrayList<String> foreign_words;
    public static ArrayList<String> native_words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_words);

        //Resources res = getResources();
        wordsListView = (ListView) findViewById(R.id.wordsListView);
        //foreign_words = res.getStringArray(R.array.foreign_words);
        //native_words = res.getStringArray(R.array.native_words);

        foreign_words = new ArrayList<>();
        foreign_words.add("a dog");
        foreign_words.add("a crocodile");
        foreign_words.add("a parrot");
        foreign_words.add("a cat");

        native_words = new ArrayList<>();
        native_words.add("pies");
        native_words.add("krokodyl");
        native_words.add("papuga");
        native_words.add("kot");

        WordsAdapter wordsAdapter = new WordsAdapter(this, foreign_words, native_words);
        wordsListView.setAdapter(wordsAdapter);
        wordsListView.setAdapter(wordsAdapter);

        Button button = (Button) findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListOfWordsActivity.this, CreateFlashcardActivity.class));
            }
        });
    }

}