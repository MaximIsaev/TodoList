package mobile.app.com.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import mobile.app.com.todolist.adapter.TaskListAdapter;
import mobile.app.com.todolist.configuration.DbHelper;
import mobile.app.com.todolist.dao.Task;
import mobile.app.com.todolist.services.TaskService;

public class MainActivity extends AppCompatActivity {

	List<Task> tasks;
	DbHelper dbHelper;
	TaskService taskService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		dbHelper = new DbHelper(this);
		taskService = new TaskService(dbHelper);
		tasks = taskService.getAllTasks();
		displayTasks(tasks);
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, AddNewTaskActivity.class);
				startActivityForResult(intent, 101);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			displayTasks(taskService.getAllTasks());
		}
	}

	private void displayTasks(List<Task> tasks) {
		ListView listView = (ListView) findViewById(R.id.taskList);
		TaskListAdapter adapter = new TaskListAdapter(this, tasks);
		if (listView != null) {
			listView.setAdapter(adapter);
		}
	}
}
