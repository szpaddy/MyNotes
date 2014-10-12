package com.paddy.mynotes.ui;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

public abstract class NotesBaseAdapter extends CursorAdapter {

	public NotesBaseAdapter(Context context) {
		super(context, null, true);
	}

}
