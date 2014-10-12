package com.paddy.mynotes.ui;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NotesListAdapter extends NotesBaseAdapter {

	private LayoutInflater mInflater;

	public NotesListAdapter(Context context) {
		super(context);

		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub

	}

}
