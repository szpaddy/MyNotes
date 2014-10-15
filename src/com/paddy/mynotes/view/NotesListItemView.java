package com.paddy.mynotes.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.paddy.mynotes.R;
import com.paddy.mynotes.data.Note;
import com.paddy.mynotes.tool.DateUtil;
import com.paddy.mynotes.tool.ResourceParser.NoteItemBgResources;

public class NotesListItemView extends LinearLayout {
	private Note mNote;
	private Context mContext;

	public NotesListItemView(Context context) {
		super(context);
		this.mContext = context;
		this.inflate(context, R.layout.note_item, this);
	}

	public void bind(Note note) {
		this.mNote = note;

		ImageView iv_list_icon = (ImageView) this
				.findViewById(R.id.iv_list_icon);
		TextView tv_time = (TextView) this.findViewById(R.id.tv_time);
		TextView tv_title = (TextView) this.findViewById(R.id.tv_title);

		this.setBackgroundResource(NoteItemBgResources.getNoteItemBgRes(note
				.getBg_color_id()));
		if (note.getType() == Note.TYPE_FOLDER) {
			iv_list_icon.setVisibility(View.VISIBLE);
			tv_time.setVisibility(View.GONE);
		} else {
			iv_list_icon.setVisibility(View.GONE);
			tv_time.setVisibility(View.VISIBLE);
			tv_time.setText(DateUtil.formatSameDayTime(mContext,
					note.getModified_date()));
		}
		tv_title.setText(note.getContent());
	}

	public Note getItemData() {
		return this.mNote;
	}
}
