package com.paddy.mynotes.data;

public class Note {

	public static final String INTENT_EXTRA_FOLDER_ID = "notes.folder_id";
	public static final int ROOT_FOLDER_ID = 0;

	public static final int TYPE_NOTE = 0;
	public static final int TYPE_FOLDER = 1;

	private int _id;
	private int parent_id;
	private int type;
	private int bg_color_id;
	private String content;
	private long created_date;
	private long modified_date;

	public Note(int _id, int parent_id, int type, int bg_color_id,
			String content, long created_date, long modified_date) {
		this._id = _id;
		this.parent_id = parent_id;
		this.type = type;
		this.bg_color_id = bg_color_id;
		this.content = content;
		this.created_date = created_date;
		this.modified_date = modified_date;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getBg_color_id() {
		return bg_color_id;
	}

	public void setBg_color_id(int bg_color_id) {
		this.bg_color_id = bg_color_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getCreated_date() {
		return created_date;
	}

	public void setCreated_date(long created_date) {
		this.created_date = created_date;
	}

	public long getModified_date() {
		return modified_date;
	}

	public void setModified_date(long modified_date) {
		this.modified_date = modified_date;
	}

}
