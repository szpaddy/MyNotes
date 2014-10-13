package com.paddy.mynotes.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NotesDataManager {
	private static final String TABLE_NOTELIST = "notelist";
	private static final String[] COLUMNS_NOTELIST = { "_id", "parent_id",
			"type", "bg_color_id", "content", "created_date", "modified_date" };

	private static NotesDataManager mNotesDataManager;
	private NotesDBHelper mHelper;

	public NotesDataManager(Context context) {
		mHelper = NotesDBHelper.getInstance(context);
		mHelper.getWritableDatabase();
	}

	public static NotesDataManager getInstance(Context context) {
		if (mNotesDataManager == null) {
			mNotesDataManager = new NotesDataManager(context);
		}
		return mNotesDataManager;
	}

	public long insertNote(Note note) {
		ContentValues cv = new ContentValues();
		cv.put("parent_id", String.valueOf(note.getParent_id()));
		cv.put("type", String.valueOf(note.getType()));
		cv.put("bg_color_id", String.valueOf(note.getBg_color_id()));
		cv.put("content", note.getContent());
		cv.put("created_date", note.getCreated_date());
		cv.put("modified_date", note.getModified_date());
		return mHelper.getWritableDatabase().insert(TABLE_NOTELIST, null, cv);
	}

	public long deleteNote(Note note) {
		String whereClause = "_id=?";
		String[] whereArgs = { String.valueOf(note.get_id()) };
		return mHelper.getWritableDatabase().delete(TABLE_NOTELIST,
				whereClause, whereArgs);// 执行删除
	}

	public long updateNote(Note note) {
		ContentValues cv = new ContentValues();
		cv.put("parent_id", String.valueOf(note.getParent_id()));
		cv.put("type", String.valueOf(note.getType()));
		cv.put("bg_color_id", String.valueOf(note.getBg_color_id()));
		cv.put("content", note.getContent());
		cv.put("created_date", note.getCreated_date());
		cv.put("modified_date", note.getModified_date());
		String whereClause = "_id=?";
		String[] whereArgs = { String.valueOf(note.get_id()) };
		return mHelper.getWritableDatabase().update(TABLE_NOTELIST, cv,
				whereClause, whereArgs);
	}

	public Note queryNoteById(long noteId) {
		String selection = "_id=?";
		String[] selectionArgs = { String.valueOf(noteId) };
		Cursor cursor = mHelper.getWritableDatabase().query(TABLE_NOTELIST,
				COLUMNS_NOTELIST, selection, selectionArgs, null, null, null);
		Note note = null;
		if (cursor.moveToFirst()) {
			note = getNoteFromCursor(cursor);
		}
		return note;
	}

	public Note getNoteFromCursor(Cursor cursor) {
		int _id = cursor.getInt(cursor.getColumnIndex("_id"));
		int parent_id = cursor.getInt(cursor.getColumnIndex("parent_id"));
		int type = cursor.getInt(cursor.getColumnIndex("type"));
		int bg_color_id = cursor.getInt(cursor.getColumnIndex("bg_color_id"));
		String content = cursor.getString(cursor.getColumnIndex("content"));
		String created_date = cursor.getString(cursor
				.getColumnIndex("created_date"));
		String modified_date = cursor.getString(cursor
				.getColumnIndex("modified_date"));
		return new Note(_id, parent_id, type, bg_color_id, content,
				created_date, modified_date);
	}

	public Cursor queryNoteListByFolderId(int folderId) {
		String selection = "parent_id=?";
		String[] selectionArgs = { String.valueOf(folderId) };
		String groupBy = "type";
		String orderBy = "modified_date";
		return mHelper.getWritableDatabase().query(TABLE_NOTELIST,
				COLUMNS_NOTELIST, selection, selectionArgs, groupBy, null,
				orderBy);
	}
}
