package com.paddy.mynotes.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class NoteDAOImpl implements NoteDAO {
	private DBOpneHelper dbOpenHelper;

	public NoteDAOImpl(Context context) {
		dbOpenHelper = new DBOpneHelper(context);
	}

	public void add(Note note) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("");
	}
}
