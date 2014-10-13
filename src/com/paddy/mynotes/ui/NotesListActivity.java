package com.paddy.mynotes.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.paddy.mynotes.data.Note;
import com.paddy.mynotes.data.NotesDataManager;

public class NotesListActivity extends Activity implements OnItemClickListener,
		OnItemLongClickListener, OnClickListener {

	private static final String TAG = "NotesListActivity";
	private final static int REQUEST_CODE_OPEN_NODE = 100;
	private final static int REQUEST_CODE_NEW_NODE = 101;

	private ListView mNotesListView;
	private NotesListAdapter mNotesListAdapter;
	private Button mAddNewNote;
	private Context mContext;

	private int mCurrentFolderId;

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
		new AsyncLoadNotesListTask().execute(Note.ROOT_FOLDER_ID);
	}

	private void initResources() {
		mContext = this;
		mCurrentFolderId = Note.ROOT_FOLDER_ID;

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
		Intent intent = new Intent(this, NoteEditActivity.class);
		intent.setAction(Intent.ACTION_INSERT_OR_EDIT);
		intent.putExtra(Note.INTENT_EXTRA_FOLDER_ID, mCurrentFolderId);
		this.startActivityForResult(intent, REQUEST_CODE_NEW_NODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK
				&& (requestCode == REQUEST_CODE_OPEN_NODE || requestCode == REQUEST_CODE_NEW_NODE)) {
			mNotesListAdapter.changeCursor(null);
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	private final class AsyncLoadNotesListTask<Params, Void, Result> extends
			AsyncTask<Params, Void, Result> {

		@Override
		protected Result doInBackground(Params... params) {
			return (Result) NotesDataManager.getInstance(mContext)
					.queryNoteListByFolderId((Integer) params[0]);
		}

		@Override
		protected void onPostExecute(Result result) {
			mNotesListAdapter.changeCursor((Cursor) result);
		}

	}
}
