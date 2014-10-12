package com.paddy.mynotes.ui;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;

import com.paddy.mynotes.R;
import com.paddy.mynotes.tool.PreferenceUtils;
import com.paddy.mynotes.view.ListTitleBarView;

public class NotesListActivity extends Activity {

	private static final String TAG = "NotesListActivity";
	private ListMode mMode;
	private long mCurrentFolderId;
	private NotesBaseFragment mFragment;
	private NotesBaseAdapter mNotesListAdapter;
	private ListEditState mState;

	public static enum ListMode {
		LIST, GRID
	}

	public static enum AppearenceMode {
		LAUNCH_LIST
	}

	private static enum ListEditState {
		NOTE_LIST, SUB_FOLDER
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.mMode = PreferenceUtils.getListMode(this);
		if (this.mMode.equals(ListMode.LIST)) {
			this.setContentView(R.layout.notes_list);
			this.getActionBar().setCustomView(R.layout.notes_list_title_bar);
			initResources();
		}
	}

	private void initResources() {
		this.mCurrentFolderId = 0L;
		this.mState = ListEditState.NOTE_LIST;
		// switchMode(this.mMode);
	}

	private void switchMode(ListMode mode) {
		NotesBaseFragment targetFragment = getOrCreateFragment(mode);
		if (this.mFragment != targetFragment) {
			this.mFragment = targetFragment;
		}
		switch (mode) {
		case LIST:

			break;
		}
		this.mNotesListAdapter = this.mFragment.getAdapter();
	}

	private NotesBaseFragment getOrCreateFragment(ListMode mode) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		NotesBaseFragment fragment = (NotesBaseFragment) fragmentManager
				.findFragmentByTag(mode.name());
		if (fragment == null) {
			switch (mode) {
			case LIST:
				fragment = new NotesListFragment();
				break;
			default:
				break;
			}
			fragmentTransaction.add(fragment, mode.name());
		}

		NotesBaseFragment tmpFragment;
		for (ListMode listMode : ListMode.values()) {
			tmpFragment = (NotesBaseFragment) fragmentManager
					.findFragmentByTag(listMode.name());
			if (tmpFragment != null && tmpFragment != fragment) {
				fragmentTransaction.hide(tmpFragment);
			}
		}

		fragmentTransaction.show(fragment);
		fragmentTransaction.commit();
		fragmentManager.executePendingTransactions();

		return fragment;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.clear();
		switch (this.mState) {
		case NOTE_LIST:
			this.getMenuInflater().inflate(R.menu.notes_list, menu);
			break;
		}
		return true;
	}

}
