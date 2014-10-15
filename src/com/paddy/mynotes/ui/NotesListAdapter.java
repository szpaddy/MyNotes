package com.paddy.mynotes.ui;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.paddy.mynotes.data.Note;
import com.paddy.mynotes.data.NotesDataManager;
import com.paddy.mynotes.view.NotesListItemView;

public class NotesListAdapter extends CursorAdapter {

	public NotesListAdapter(Context context) {
		super(context, null);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return new NotesListItemView(context);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		if (view instanceof NotesListItemView) {
			Note note = NotesDataManager.getInstance(context)
					.getNoteFromCursor(cursor);
			((NotesListItemView) view).bind(note);
		}
	}

}
