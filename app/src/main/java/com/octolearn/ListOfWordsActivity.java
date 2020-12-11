package com.octolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListOfWordsActivity extends AppCompatActivity {

    private ListView wordsListView;
    private ArrayList<Flashcard> flashcards;
    private WordsAdapter wordsAdapter;
    private WordsSQLiteDB dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_words);

        dataBase = new WordsSQLiteDB(this);
        wordsListView = (ListView) findViewById(R.id.wordsListView);
        flashcards = new ArrayList<>();

        loadFlashcardsFromDatabase();

        Button button = (Button) findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListOfWordsActivity.this, CreateFlashcardActivity.class));
            }
        });

        wordsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListOfWordsActivity.this, flashcards.get(position).getWord(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListOfWordsActivity.this, EditFlashcardActivity.class);
                Bundle b = new Bundle();
                b.putString("word", flashcards.get(position).getWord());
                b.putString("translation", flashcards.get(position).getTranslation());
                b.putString("sample", flashcards.get(position).getSample());
                b.putString("transcription", flashcards.get(position).getTranscription());
                intent.putExtras(b);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadFlashcardsFromDatabase() {
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

            wordsAdapter = new WordsAdapter(this, flashcards);
            wordsAdapter.notifyDataSetChanged();
            wordsListView.setAdapter(wordsAdapter);
        }
    }

}