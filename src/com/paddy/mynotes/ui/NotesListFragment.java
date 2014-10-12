package com.paddy.mynotes.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.paddy.mynotes.R;
import com.paddy.mynotes.view.NoteListView;

public class NotesListFragment extends NotesBaseFragment {
	private int DOUBLE_LINE_HEIGHT;
	private int SINGLE_LINE_HEIGHT;
	private NotesListActivity mActivity;
	private NotesListAdapter mAdapter;
	private ViewGroup mFragmentLayout;
	private NoteListView mNotesListView;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		this.mActivity = ((NotesListActivity) activity);
		this.mAdapter = new NotesListAdapter(this.mActivity);

		this.SINGLE_LINE_HEIGHT = (int) this.mActivity.getResources()
				.getDimension(R.dimen.single_line_height);
		this.DOUBLE_LINE_HEIGHT = (int) this.mActivity.getResources()
				.getDimension(R.dimen.double_line_height);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.mFragmentLayout = ((ViewGroup) inflater.inflate(
				R.layout.notes_list, container, false));
		this.mNotesListView = ((NoteListView) this.mFragmentLayout
				.findViewById(R.id.noteList));
		setupListView(this.mNotesListView);
		return this.mFragmentLayout;
	}

	private void setupListView(NoteListView notesListView) {
		if (notesListView != null) {
			notesListView.setOnItemLongClickListener(this);
			notesListView.setAdapter(getAdapter());
		}
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public NotesBaseAdapter getAdapter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		return false;
	}

}
