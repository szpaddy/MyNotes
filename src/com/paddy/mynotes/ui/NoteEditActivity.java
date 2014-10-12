package com.paddy.mynotes.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.paddy.mynotes.R;
import com.paddy.mynotes.editor.RichEditView;

public class NoteEditActivity extends Activity implements OnClickListener {

	private ImageView mHeaderUp;
	private TextView mHeaderDate;
	private TextView mHeaderTime;
	private ImageView mHeaderColor;
	private RichEditView noteEditView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.note_edit);
		this.getActionBar().setCustomView(R.layout.note_edit_header);

		initResources();
	}

	@Override
	protected void onResume() {
		super.onResume();

		// updateBackground();
		// updateTimeHeader();
	}

	private void initResources() {
		View header = this.getActionBar().getCustomView();
		mHeaderUp = (ImageView) header.findViewById(R.id.headerUp);
		mHeaderDate = (TextView) header.findViewById(R.id.headerDate);
		mHeaderTime = (TextView) header.findViewById(R.id.headerTime);
		mHeaderColor = (ImageView) header.findViewById(R.id.headerColor);
		View[] headerViewAry = { mHeaderUp, mHeaderDate, mHeaderTime,
				mHeaderColor };
		for (View view : headerViewAry) {
			view.setOnClickListener(this);
		}

		noteEditView = (RichEditView) this.findViewById(R.id.noteEditView);
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

	}

}
