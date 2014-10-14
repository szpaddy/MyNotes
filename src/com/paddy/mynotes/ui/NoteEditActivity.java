package com.paddy.mynotes.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.paddy.mynotes.R;
import com.paddy.mynotes.data.Note;
import com.paddy.mynotes.data.NotesDataManager;
import com.paddy.mynotes.view.NoteEditView;

public class NoteEditActivity extends Activity implements OnClickListener {

	private ImageView mHeaderUp;
	private TextView mHeaderDate;
	private TextView mHeaderTime;
	private ImageView mHeaderColor;
	private NoteEditView mNoteEditor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.note_edit);

		initResources();
	}

	@Override
	protected void onResume() {
		super.onResume();

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
		View[] headerViewAry = { mHeaderUp, mHeaderDate, mHeaderTime,
				mHeaderColor };
		for (View view : headerViewAry) {
			view.setOnClickListener(this);
		}

		mNoteEditor = (NoteEditView) this.findViewById(R.id.noteEditorView);
	}

	private void initNoteScreen() {
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
		int _id = -1;
		int parent_id = Note.ROOT_FOLDER_ID;
		int type = Note.TYPE_NOTE;
		int bg_color_id = -1;
		String content = mNoteEditor.getText().toString();
		String created_date = "11-22-33";
		String modified_date = "11-22-33";
		Note note = new Note(_id, parent_id, type, bg_color_id, content,
				created_date, modified_date);
		NotesDataManager.getInstance(this).insertNote(note);
	}

}
