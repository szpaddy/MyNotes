package com.paddy.mynotes.ui;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.paddy.mynotes.R;

public class NotesListActivity extends Activity implements OnItemClickListener,
		OnItemLongClickListener, OnClickListener {

	private static final String TAG = "NotesListActivity";
	private ListView mNotesListView;
	private NotesListAdapter mNotesListAdapter;
	private Button mAddNewNote;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.notes_list);
		initResources();
	}

	@Override
	protected void onStart() {
		super.onStart();
		startAsyncNotesListQuery();
	}

	private void startAsyncNotesListQuery() {

	}

	private void initResources() {
		mNotesListView = (ListView) findViewById(R.id.noteList);
		mNotesListView.setOnItemClickListener(this);
		mNotesListView.setOnItemLongClickListener(this);
		mNotesListAdapter = new NotesListAdapter(this);
		mNotesListView.setAdapter(mNotesListAdapter);

		mAddNewNote = (Button) findViewById(R.id.btn_new_note);
		mAddNewNote.setOnClickListener(this);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_new_note:
			createNewNote();
			break;
		default:
			break;
		}
	}

	private void createNewNote() {

	}

	private final class AsyncLoadNotesListTask<Long, Void, Cursor> extends
			AsyncTask<Long, Void, Cursor> {

		@Override
		protected Cursor doInBackground(Long... params) {
			return null;
		}

		@Override
		protected void onPostExecute(Cursor result) {
			mNotesListAdapter.changeCursor((android.database.Cursor) result);
		}

	}
}
