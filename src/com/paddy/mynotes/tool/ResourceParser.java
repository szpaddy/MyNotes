package com.paddy.mynotes.tool;

import android.content.Context;

import com.paddy.mynotes.R;

public class ResourceParser {

	public static enum Color {
		yellow, white, red, green, blue
	}

	public static int getDefaultBgId(Context context) {
		int bgIndex = 0;
		boolean useRandomColor = false;
		if (useRandomColor) {
			bgIndex = (int) (Math.random() * Color.values().length);
		}
		return bgIndex;
	}

	public static class NoteItemBgResources {
		private static final int[] BG_RESOURCES = { R.color.yellow,
				R.color.white, R.color.red, R.color.green, R.color.blue };

		public static int getNoteItemBgRes(int colorIndex) {
			return BG_RESOURCES[colorIndex];
		}
	}

	public static class NoteBgResources {
		private static final int[] BG_EDIT_PANEL_RESOURCES = { 2130837531,
				2130837516, 2130837530, 2130837518, 2130837522 };
		private static final int[] BG_EDIT_TITLE_RESOURCES = { 2130837529,
				2130837525, 2130837528, 2130837526, 2130837527 };
		private static final int[] BG_ICON_PANEL_RESOURCES = { 2130837626,
				2130837612, 2130837625, 2130837618, 2130837619 };
		private static final int[] BG_ICON_TITLE_RESOURCES = { 2130837624,
				2130837620, 2130837623, 2130837621, 2130837622 };

		public static int getEditPanelBgResource(int resIndex) {
			return BG_EDIT_PANEL_RESOURCES[resIndex];
		}

		public static int getEditTitleBgResource(int resIndex) {
			return BG_EDIT_TITLE_RESOURCES[resIndex];
		}

		public static int getIconPanelBgResource(int resIndex) {
			return BG_ICON_PANEL_RESOURCES[resIndex];
		}

		public static int getIconTitleBgResource(int resIndex) {
			return BG_ICON_TITLE_RESOURCES[resIndex];
		}
	}
}
