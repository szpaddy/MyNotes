package com.paddy.mynotes.editor;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class RichEditView extends EditText {

	public RichEditView(Context context) {
		this(context, null);
	}

	public RichEditView(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.editTextStyle);
	}

	public RichEditView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

}
