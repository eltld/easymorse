package com.easymorse;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ItemList extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this
				.setListAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, new String[] {
								"视频", "听听","社区" }));
	}
}