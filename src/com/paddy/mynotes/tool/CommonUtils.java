package com.paddy.mynotes.tool;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class CommonUtils {

	public static void showSoftInput(View view) {
		InputMethodManager inputMethodManager = (InputMethodManager) view
				.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		if (inputMethodManager != null)
			inputMethodManager.showSoftInput(view,
					InputMethodManager.RESULT_SHOWN);
	}

	public static void hideSoftInput(View view) {
		InputMethodManager inputMethodManager = (InputMethodManager) view
				.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		if (inputMethodManager != null)
			inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),
					inputMethodManager.HIDE_NOT_ALWAYS);
	}
}
