package com.paddy.mynotes.ui;

import android.app.Fragment;
import android.widget.AdapterView.OnItemLongClickListener;

public abstract class NotesBaseFragment extends Fragment implements
		OnItemLongClickListener {

	public abstract NotesBaseAdapter getAdapter();
}
