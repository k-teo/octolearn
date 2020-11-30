package com.octolearn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class WordsAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    ArrayList<Flashcard> flashcards;

    public WordsAdapter(Context context, ArrayList<Flashcard> flashcards){
        this.flashcards = flashcards;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return flashcards.size();
    }

    @Override
    public Object getItem(int position) {
        return flashcards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.words_listview_detail, null);
        TextView wordTextView = (TextView) view.findViewById(R.id.wordTextView);
        TextView translationTextView = (TextView) view.findViewById(R.id.translationTextView);

        String word = flashcards.get(position).getWord();
        String translation = flashcards.get(position).getTranslation();

        wordTextView.setText(word);
        translationTextView.setText(translation);

        return view;
    }
}
