package com.paddy.mynotes.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.paddy.mynotes.ui.NotesListActivity.AppearenceMode;

public class ListTitleBarView extends FrameLayout {

	public ListTitleBarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ListTitleBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ListTitleBarView(Context context) {
		super(context);
	}

	public void setAppearenceMode(AppearenceMode mode) {

	}

}
