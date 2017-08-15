package mobile.app.com.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import mobile.app.com.todolist.loader.MyCursorLoader;

public class MainActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {

	ListView listView;
	Db db;
	SimpleCursorAdapter cursorAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//		setSupportActionBar(toolbar);
		db = new Db(this);
		db.open();

		String[] from = new String[]{Db.COLUMN_ITEM_NAME};
		int[] to = new int[]{R.id.item_name};

		cursorAdapter = new SimpleCursorAdapter(this, R.layout.item_layout, null, from, to, 0);
		listView = (ListView) findViewById(R.id.taskList);
		listView.setAdapter(cursorAdapter);

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
}
