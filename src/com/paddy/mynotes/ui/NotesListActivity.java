package com.paddy.mynotes.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.paddy.mynotes.R;
import com.paddy.mynotes.data.Note;
import com.paddy.mynotes.data.NotesDataManager;
import com.paddy.mynotes.view.NotesListItemView;

public class NotesListActivity extends Activity implements OnItemClickListener,
		OnItemLongClickListener, OnClickListener {

	private static final String TAG = "NotesListActivity";
	private final static int REQUEST_CODE_OPEN_NODE = 100;
	private final static int REQUEST_CODE_NEW_NODE = 101;

	private enum ListEditState {
		NOTE_LIST, SUB_FOLDER
	};

	private ListView mNotesListView;
	private NotesListAdapter mNotesListAdapter;
	private Button mAddNewNote;
	private Context mContext;

	private int mCurrentFolderId;
	private View mSearchView;

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

		FrameLayout searchBarLayout = new FrameLayout(this);
		//searchBarLayout.setBackgroundResource(R.drawable.search_bar_bg);
		this.mSearchView = LayoutInflater.from(this).inflate(
				R.layout.note_list_search, searchBarLayout, false);
		searchBarLayout.addView(this.mSearchView);

		mNotesListView = (ListView) findViewById(R.id.noteList);
		mNotesListView.addHeaderView(searchBarLayout, null, false);
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
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (view instanceof NotesListItemView) {
			Note note = ((NotesListItemView) view).getItemData();
			if (note.getType() == Note.TYPE_FOLDER) {
				openFolder(note);
			} else {
				openNode(note);
			}
		}
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

	private void openNode(Note note) {
		Intent intent = new Intent(this, NoteEditActivity.class);
		intent.setAction(Intent.ACTION_VIEW);
		intent.putExtra(Intent.EXTRA_UID, note.get_id());
		this.startActivityForResult(intent, REQUEST_CODE_OPEN_NODE);
	}
	
	private void openFolder(Note note) {
		
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
