package mobile.app.com.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView listView;
	Db db;
	//	SimpleCursorAdapter cursorAdapter;
	ArrayAdapter<String> arrayAdapter;
	ActionMode actionMode;
	String[] data = {"one", "two", "three", "four", "five"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//		setSupportActionBar(toolbar);
//		db = new Db(this);
//		db.open();

//		String[] from = new String[]{R.id.};
//		int[] to = new int[]{R.id.item_name};

//		cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null, from, to, 0);
		arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, data);
		listView = (ListView) findViewById(R.id.taskList);
		listView.setAdapter(arrayAdapter);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
			@Override
			public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
				Log.d("TEST", "position is " + position + ", checked " + checked);

			}

			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				mode.getMenuInflater().inflate(R.menu.task_context_menu, menu);
				return true;
			}

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				mode.finish();
				return false;
			}

			@Override
			public void onDestroyActionMode(ActionMode mode) {

			}
		});

//		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//		fab.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				Intent intent = new Intent(MainActivity.this, AddNewTaskActivity.class);
//				startActivity(intent);
			}
//		});
//		getSupportLoaderManager().initLoader(0, null, this);
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.task_context_menu, menu);
		return true;
	}

	//	@Override
//	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//		super.onCreateContextMenu(menu, v, menuInfo);
//		MenuInflater menuInflater = getMenuInflater();
//		menuInflater.inflate(R.menu.task_context_menu, menu);
//	}

//	@Override
//	protected void onDestroy() {
//		super.onDestroy();
//		db.close();
//	}

//	@Override
//	public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
//		return new MyCursorLoader(this, db);
//	}
//
//	@Override
//	public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor cursor) {
//		cursorAdapter.swapCursor(cursor);
//	}
//
//	@Override
//	public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {
//
//	}
}
