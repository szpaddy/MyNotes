package com.paddy.mynotes.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDBHelper extends SQLiteOpenHelper {
	private static final int VERSION = 1;
	private static final String DB_NAME = "notes.db";
	private static final String TABLE_NOTELIST = "notelist";

	private static final String CREATE_TABLE = "create table "
			+ TABLE_NOTELIST
			+ "(_id Integer primary key autoincrement, parent_id Integer,type Integer, bg_color_id Integer,"
			+ "content text,created_date text,modified_date text)";

	private static NotesDBHelper instance;

	public NotesDBHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	public static NotesDBHelper getInstance(Context context) {
		if (instance == null) {
			instance = new NotesDBHelper(context);
		}
		return instance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
