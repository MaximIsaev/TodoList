package mobile.app.com.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import mobile.app.com.todolist.loader.MyCursorLoader;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

	ListView listView;
	Db db;
	SimpleCursorAdapter cursorAdapter;
	Map<Long, Boolean> itemSelection = new HashMap<>();
	MenuItem editItem;
	private int count = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		db = new Db(this);
		db.open();

		String[] from = new String[]{Db.COLUMN_ITEM_NAME};
		int[] to = new int[]{R.id.item_name};

		cursorAdapter = new SimpleCursorAdapter(this, R.layout.item_layout, null, from, to, 0);
		listView = (ListView) findViewById(R.id.taskList);
		listView.setAdapter(cursorAdapter);
		listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
		listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

			@Override
			public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
				count = 0;
				mode.getMenuInflater().inflate(R.menu.task_context_menu, menu);
				editItem = menu.findItem(R.id.edit_item);
				return true;
			}

			@Override
			public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
				return false;
			}

			@Override
			public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {

				switch (item.getItemId()) {
					case R.id.delete_item:
						Set<Long> keys = itemSelection.keySet();
						Iterator<Long> iterator = keys.iterator();
						while (iterator.hasNext()) {
							db.delete(iterator.next());
						}
						getSupportLoaderManager().restartLoader(0, null, MainActivity.this);
						break;
					case R.id.edit_item:
						break;
				}
				mode.finish();
				return true;
			}

			@Override
			public void onDestroyActionMode(android.view.ActionMode mode) {
				clearSelections();
			}

			@Override
			public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long id, boolean checked) {
				Log.d("TEST", "id = " + id + ", checked = "
						+ checked);
				if (checked) {
					count++;
					setNewSelection(id, checked);
				} else {
					count--;
					removeSelection(id);
				}
				if (count > 1) {
					editItem.setVisible(false);
				} else {
					editItem.setVisible(true);
				}
			}
		});

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, AddNewTaskActivity.class);
				startActivity(intent);
			}
		});
		getSupportLoaderManager().initLoader(0, null, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.task_context_menu, menu);
		return true;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new MyCursorLoader(this, db);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		cursorAdapter.swapCursor(cursor);

	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		db.close();
	}

	public void setNewSelection(Long position, boolean value) {
		itemSelection.put(position, value);
	}

	public boolean isPositionChecked(Long position) {
		Boolean checked = itemSelection.get(position);
		return checked == null ? false : checked;
	}

	public void removeSelection(Long position) {
		itemSelection.remove(position);
	}

	public void clearSelections() {
		itemSelection.clear();
	}
}
