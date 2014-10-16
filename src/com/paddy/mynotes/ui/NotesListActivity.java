package com.paddy.mynotes.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.paddy.mynotes.R;
import com.paddy.mynotes.data.Note;
import com.paddy.mynotes.data.NotesDataManager;
import com.paddy.mynotes.tool.CommonUtils;
import com.paddy.mynotes.tool.ResourceParser;
import com.paddy.mynotes.view.NotesListItemView;

public class NotesListActivity extends Activity implements OnItemClickListener,
		OnItemLongClickListener, OnClickListener {

	private static final String TAG = "NotesListActivity";
	private final static int REQUEST_CODE_OPEN_NODE = 100;
	private final static int REQUEST_CODE_NEW_NODE = 101;

	private ListView mNotesListView;
	private NotesListAdapter mNotesListAdapter;
	private Button mAddNewNote;
	private Button mAddNewFolder;
	private Context mContext;

	private int mCurrentFolderId;
	private View mSearchView;
	private AlertDialog mActiveDialog;
	private MultiChoiceModeCallback mModeCallBack;

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

		this.mSearchView = LayoutInflater.from(this).inflate(
				R.layout.note_list_search, null, false);

		mNotesListView = (ListView) findViewById(R.id.noteList);
		mNotesListView.addHeaderView(this.mSearchView, null, false);
		mNotesListView.setOnItemClickListener(this);
		mNotesListView.setOnItemLongClickListener(this);
		mNotesListAdapter = new NotesListAdapter(this);
		mNotesListView.setAdapter(mNotesListAdapter);

		mAddNewNote = (Button) findViewById(R.id.btn_new_note);
		mAddNewNote.setOnClickListener(this);
		mAddNewFolder = (Button) findViewById(R.id.btn_new_folder);
		mAddNewFolder.setOnClickListener(this);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		if (view instanceof NotesListItemView) {
			Note note = ((NotesListItemView) view).getItemData();
			if (note.getType() == Note.TYPE_FOLDER) {
				openFolder(note);
			} else {

			}

			startActionMode(this.mModeCallBack);
		}
		return true;
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
		case R.id.btn_new_folder:
			createNewFolder();
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

	private void createNewFolder() {
		this.mActiveDialog = new FolderNameDialog(this);
		this.mActiveDialog.show();
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

	private class FolderNameDialog extends AlertDialog {
		private Context nContext;
		protected EditText nFolderEditor;
		protected Button nPositiveButton;
		private String nFolderName;

		protected FolderNameDialog(Context context) {
			super(context);
			this.nContext = NotesListActivity.this;
			initialize();
		}

		protected void initialize() {
			View folderEditView = LayoutInflater.from(this.nContext).inflate(
					R.layout.foldername_edit, null);
			this.nFolderEditor = ((EditText) folderEditView
					.findViewById(R.id.et_folder_name));
			this.nFolderEditor.addTextChangedListener(new TextWatcher() {
				public void onTextChanged(CharSequence charSequence, int start,
						int before, int count) {
					if (nPositiveButton != null) {
						if (TextUtils.isEmpty(nFolderEditor.getText()
								.toString().trim())) {
							nPositiveButton.setEnabled(false);
						} else {
							nPositiveButton.setEnabled(true);
						}
					}
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
				}

				@Override
				public void afterTextChanged(Editable s) {
				}
			});
			setView(folderEditView);
			setButton(BUTTON_POSITIVE,
					this.nContext.getString(R.string.confirm_ok),
					(DialogInterface.OnClickListener) null);
			setButton(BUTTON_NEGATIVE,
					this.nContext.getString(R.string.confirm_cancel),
					(DialogInterface.OnClickListener) null);

			this.nFolderEditor.setText(this.nContext
					.getString(R.string.hint_foler_name));
			this.nFolderEditor.setSelection(this.nFolderEditor.getText()
					.length());
			setTitle(this.nContext.getString(R.string.create_new_folder));
		}

		public void show() {
			super.show();

			this.nPositiveButton = getButton(BUTTON_POSITIVE);
			this.nPositiveButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					saveFolderName();
				}

			});

			CommonUtils.showSoftInput(nFolderEditor);
			this.nFolderEditor.requestFocus();
		}

		public void dismiss() {
			CommonUtils.hideSoftInput(this.nFolderEditor);
			super.dismiss();
		}

		protected void saveFolderName() {
			nFolderName = this.nFolderEditor.getText().toString().trim();
			if (NotesDataManager.getInstance(this.nContext).isFolderNameExist(
					mCurrentFolderId, nFolderName)) {
				Toast.makeText(
						this.nContext,
						this.nContext.getString(R.string.rename_folder_message),
						Toast.LENGTH_LONG).show();
				this.nFolderEditor.setSelection(0, this.nFolderEditor.length());
			} else {
				int _id = -1;
				int parent_id = mCurrentFolderId;
				int type = Note.TYPE_FOLDER;
				int bg_color_id = ResourceParser.getDefaultBgId(this.nContext);
				String content = this.nFolderName;
				long created_date = System.currentTimeMillis();
				long modified_date = System.currentTimeMillis();

				Note folder = new Note(_id, parent_id, type, bg_color_id,
						content, created_date, modified_date);
				NotesDataManager.getInstance(this.nContext).insertNote(folder);
				startAsyncNotesListQuery();

				dismiss();
			}
		}
	}

	private class MultiChoiceModeCallback implements MultiChoiceModeListener {

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onItemCheckedStateChanged(ActionMode mode, int position,
				long id, boolean checked) {
			// TODO Auto-generated method stub

		}

	}
}
