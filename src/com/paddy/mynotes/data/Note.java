package com.paddy.mynotes.data;

public class Note {

	private long _id;
	private long parent_id;
	private long bg_color_id;
	private String content;
	private String created_date;
	private String modified_date;

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public long getParent_id() {
		return parent_id;
	}

	public void setParent_id(long parent_id) {
		this.parent_id = parent_id;
	}

	public long getBg_color_id() {
		return bg_color_id;
	}

	public void setBg_color_id(long bg_color_id) {
		this.bg_color_id = bg_color_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getModified_date() {
		return modified_date;
	}

	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}

}
