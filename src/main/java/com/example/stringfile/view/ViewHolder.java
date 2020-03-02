package com.example.stringfile.view;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stringfile.R;
import com.example.stringfile.storage.NoteStorage;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        LinearLayout linearLayout = (LinearLayout)itemView;
        textView = linearLayout.findViewById(R.id.textView);
    }

    public void setData(int row) {
        textView.setText(NoteStorage.getNote(row).getHeadline());
    }
}
