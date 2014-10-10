package com.paddy.mynotes.ui;

import android.app.Activity;
import android.os.Bundle;

import com.paddy.mynotes.R;
import com.paddy.mynotes.editor.RichEditView;

public class NoteEditActivity extends Activity {

	private RichEditView noteEditView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.note_edit);

		initResources();
	}

	private void initResources() {
		noteEditView = (RichEditView) this.findViewById(R.id.noteEditView);
	}

}
