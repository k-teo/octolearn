package com.octolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class WayOfLearningActivity extends AppCompatActivity implements View.OnClickListener, DeleteDialog.DialogListener{

    private ArrayList<Flashcard> flashcards;
    private WordsSQLiteDB wordsTable;
    private CatalogsSQLiteDB catalogsTable;
    private String catalogName;

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
        ImageView delete = findViewById(R.id.deleteCatalog);
        delete.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            catalogName = bundle.getString("name");
        }

        wordsTable = new WordsSQLiteDB(this);
        catalogsTable = new CatalogsSQLiteDB(this);
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
                Toast.makeText(WayOfLearningActivity.this, catalogName, Toast.LENGTH_SHORT).show();
                break;
            case R.id.wordsListView:
                startActivity(new Intent(WayOfLearningActivity.this, ListOfWordsActivity.class));
                break;
            case R.id.deleteCatalog:
                openDialog();
                break;

        }
    }

    public void getFlashcards(){
        Cursor cursor = wordsTable.getAllFlashcards();

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

    public void openDialog(){
        DeleteDialog deleteDialog = new DeleteDialog();
        deleteDialog.show(getSupportFragmentManager(), "deleteDialog");
    }

    @Override
    public void onYesClicked() {
            wordsTable.deleteAllFlashcards(catalogName);
            catalogsTable.deleteCatalog(catalogName);
            startActivity(new Intent(WayOfLearningActivity.this, MainActivity.class));
            finish();
    }

}