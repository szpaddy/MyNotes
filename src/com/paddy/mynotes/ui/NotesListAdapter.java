package com.paddy.mynotes.ui;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.paddy.mynotes.R;
import com.paddy.mynotes.data.Note;
import com.paddy.mynotes.data.NotesDataManager;

public class NotesListAdapter extends CursorAdapter {

	public NotesListAdapter(Context context) {
		super(context, null);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return LayoutInflater.from(context).inflate(R.layout.note_item, null);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView tv_time = (TextView) view.findViewById(R.id.tv_time);
		TextView tv_title = (TextView) view.findViewById(R.id.tv_title);

		Note note = NotesDataManager.getInstance(context).getNoteFromCursor(
				cursor);
		tv_time.setText(note.getModified_date());
		tv_title.setText(note.getContent());
	}

}
