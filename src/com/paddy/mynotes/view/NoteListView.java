package com.paddy.mynotes.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class NoteListView extends ListView {

	public NoteListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
	}

	public NoteListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}


	public NoteListView(Context context) {
		super(context);
		init(null);
	}

	private void init(AttributeSet attrs) {

	}

}
