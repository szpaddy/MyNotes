package com.paddy.mynotes.editor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import android.text.style.UnderlineSpan;
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

	public static class BaseImageSpan extends ImageSpan {

		public BaseImageSpan(Context context, int resourceId) {
			super(context, resourceId);
		}

		@Override
		public void draw(Canvas canvas, CharSequence text, int start, int end,
				float x, int top, int y, int bottom, Paint paint) {
			// TODO Auto-generated method stub
			super.draw(canvas, text, start, end, x, top, y, bottom, paint);
		}

	}
	
	public static class MyUnderlineSpan extends UnderlineSpan{
		
	}

}
