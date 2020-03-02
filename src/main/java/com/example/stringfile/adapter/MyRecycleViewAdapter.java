package com.example.stringfile.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stringfile.Headline;
import com.example.stringfile.R;
import com.example.stringfile.model.Note;
import com.example.stringfile.storage.NoteStorage;
import com.example.stringfile.view.ViewHolder;

import java.util.ArrayList;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<Note> list;

    public static final String headline = "getHeadline", message = "getMessage", pos = "getPosition";

    public MyRecycleViewAdapter() {
        this.list = NoteStorage.getList();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout ll = (LinearLayout) LayoutInflater.
                from(parent.getContext()).inflate(R.layout.customrow, parent, false);
        return new ViewHolder(ll);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.setData(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(v.getContext(), Headline.class);
                edit.putExtra(headline, NoteStorage.getNote(position).getHeadline());
                edit.putExtra(message, NoteStorage.getNote(position).getBody());
                edit.putExtra(pos, position);
                v.getContext().startActivity(edit);
            }
        });

    }

    @Override
    public int getItemCount() {
        return NoteStorage.getLength();
    }
}