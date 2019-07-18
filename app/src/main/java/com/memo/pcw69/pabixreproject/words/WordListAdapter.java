package com.memo.pcw69.pabixreproject.words;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.memo.pcw69.pabixreproject.R;
import com.memo.pcw69.pabixreproject.data.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView content;

        private WordViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.textView1);
            content = (TextView) itemView.findViewById(R.id.textView2);
        }
    }

    private final LayoutInflater mInflater;
    private List<Word> mWords;

    WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (mWords  != null) {
            Word  current = mWords .get(position);
            holder.title.setText(current.getWord());
            holder.content.setText(current.getWord());
        } else {
            // Covers the case of data not being ready yet.
            holder.title.setText(" ");
            holder.content.setText(" ");
        }
    }

    void setWords(List<Word> words) {
        mWords=words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mWords !=null)
            return mWords.size();
        else return 0;
    }
}
