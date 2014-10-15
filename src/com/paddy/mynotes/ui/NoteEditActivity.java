package com.paddy.mynotes.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.paddy.mynotes.R;
import com.paddy.mynotes.data.Note;
import com.paddy.mynotes.data.NotesDataManager;
import com.paddy.mynotes.tool.DateUtil;
import com.paddy.mynotes.tool.ResourceParser;
import com.paddy.mynotes.view.NoteEditView;

public class NoteEditActivity extends Activity implements OnClickListener {
	private static final String TAG = "NoteEditActivity";

	private ImageView mHeaderUp;
	private TextView mHeaderDate;
	private TextView mHeaderTime;
	private ImageView mHeaderColor;
	private NoteEditView mNoteEditor;
	private Note mNote;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.note_edit);

		initResources();
	}

	@Override
	protected void onResume() {
		super.onResume();

		initNoteData();
		initNoteScreen();
	}

	@Override
	public void onBackPressed() {
		saveNote();
		super.onBackPressed();
	}

	private void initResources() {
		mHeaderUp = (ImageView) this.findViewById(R.id.headerUp);
		mHeaderDate = (TextView) this.findViewById(R.id.headerDate);
		mHeaderTime = (TextView) this.findViewById(R.id.headerTime);
		mHeaderColor = (ImageView) this.findViewById(R.id.headerColor);
		View[] headerViewAry = { mHeaderUp, mHeaderColor };
		for (View view : headerViewAry) {
			view.setOnClickListener(this);
		}

		mNoteEditor = (NoteEditView) this.findViewById(R.id.noteEditorView);
	}

	private void initNoteData() {
		Intent intent = this.getIntent();
		if (TextUtils.equals(Intent.ACTION_VIEW, intent.getAction())) {
			int noteId = intent.getIntExtra(Intent.EXTRA_UID, 0);
			mNote = NotesDataManager.getInstance(this).queryNoteById(noteId);
			getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		} else if (TextUtils.equals(Intent.ACTION_INSERT_OR_EDIT,
				intent.getAction())) {
			int _id = -1;
			int parent_id = intent.getIntExtra(Note.INTENT_EXTRA_FOLDER_ID, 0);
			int type = Note.TYPE_NOTE;
			int bg_color_id = ResourceParser.getDefaultBgId(this);
			String content = "";
			long created_date = System.currentTimeMillis();
			long modified_date = System.currentTimeMillis();
			mNote = new Note(_id, parent_id, type, bg_color_id, content,
					created_date, modified_date);
		}

		if (mNote == null) {
			Log.e(TAG, "failed to initNoteData action:" + intent.getAction());
			finish();
		}
	}

	private void initNoteScreen() {
		mHeaderDate
				.setText(DateUtil.getShowDate(this, mNote.getModified_date()));
		mHeaderTime.setText(DateUtil.getShowTimeWeek(this,
				mNote.getModified_date()));
		mNoteEditor.setText(mNote.getContent());
		mNoteEditor.setSelection(mNoteEditor.getText().length());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.headerUp:
			saveNote();
			gotoNotesList();
			this.finish();
			break;
		}
	}

	private void gotoNotesList() {
		Intent intent = new Intent(this, NotesListActivity.class);
		this.startActivity(intent);
	}

	private void saveNote() {
		if (!TextUtils.isEmpty(mNoteEditor.getText())) {
			mNote.setContent(mNoteEditor.getText().toString());
			mNote.setModified_date(System.currentTimeMillis());
			// insert or update note here
			if (mNote.get_id() == -1) {
				NotesDataManager.getInstance(this).insertNote(mNote);
			} else {
				NotesDataManager.getInstance(this).updateNote(mNote);
			}
		} else {
			// if user open a note and delete all contents, delete the note here
			if (mNote.get_id() != -1) {
				NotesDataManager.getInstance(this).deleteNote(mNote);
			}
		}
	}

}
