package com.paddy.mynotes.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpneHelper extends SQLiteOpenHelper {

	private static final int VERSION = 1;
	private static final String DB_NAME = "notes.db";

	private static final String CREATE_TABLE = "create table tb_notelist("
			+ "_id Integer primary key autoincrement, parent_id Integer, bg_color_id Integer,"
			+ "content text,created_date text,modified_date text)";

	public DBOpneHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
