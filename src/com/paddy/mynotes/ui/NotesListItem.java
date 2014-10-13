package com.paddy.mynotes.ui;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.paddy.mynotes.R;
import com.paddy.mynotes.data.Note;

public class NotesListItem extends LinearLayout {

	private TextView mTime;
	private TextView mTitle;

	public NotesListItem(Context context) {
		super(context);

		inflate(context, R.layout.note_item, this);
		mTime = (TextView) findViewById(R.id.tv_time);
		mTitle = (TextView) findViewById(R.id.tv_title);
	}

	public void bind(Context context, Note note) {
		mTime.setText(note.getModified_date());
		mTitle.setText(note.getContent());
	}

}
