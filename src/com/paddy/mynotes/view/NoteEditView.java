package com.paddy.mynotes.view;

import android.content.Context;
import android.text.Layout;
import android.text.Selection;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

public class NoteEditView extends EditText {

	public NoteEditView(Context context) {
		super(context);
	}

	public NoteEditView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoteEditView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			int x = (int) event.getX() + getScrollX() - getTotalPaddingLeft();
			int y = (int) event.getY() + getScrollY() - getTotalPaddingTop();

			Layout layout = getLayout();
			int line = layout.getLineForVertical(y);
			int off = layout.getOffsetForHorizontal(line, x);
			Selection.setSelection(getText(), off);
			break;
		}

		return super.onTouchEvent(event);
	}
}
