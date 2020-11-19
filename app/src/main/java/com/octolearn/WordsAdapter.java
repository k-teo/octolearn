package com.octolearn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WordsAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] foreign_words;
    String[] native_words;

    public WordsAdapter(Context context, String[] foreign_words, String[] native_words){
        this.foreign_words = foreign_words;
        this.native_words = native_words;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return foreign_words.length;
    }

    @Override
    public Object getItem(int position) {
        return foreign_words[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.words_listview_detail, null);
        TextView wordTextView = (TextView) v.findViewById(R.id.wordTextView);
        TextView translationTextView = (TextView) v.findViewById(R.id.translationTextView);

        String word = foreign_words[position];
        String translation = native_words[position];

        wordTextView.setText(word);
        translationTextView.setText(translation);

        return v;
    }
}
