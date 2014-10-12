package com.paddy.mynotes.tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.paddy.mynotes.ui.NotesListActivity.ListMode;

public class PreferenceUtils {

	private static final String FLAG_LIST_MODE = "notes.list_mode";

	public static ListMode getListMode(Context context) {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		String modeName = preferences.getString(FLAG_LIST_MODE, null);
		if (TextUtils.isEmpty(modeName)) {
			ListMode defaultMode = ListMode.LIST;
			setListMode(context, defaultMode);
			modeName = defaultMode.name();
		}
		return ListMode.valueOf(modeName);
	}

	public static void setListMode(Context context, ListMode mode) {
		PreferenceManager.getDefaultSharedPreferences(context).edit()
				.putString(FLAG_LIST_MODE, mode.name()).apply();
	}
}
