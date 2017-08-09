package mobile.app.com.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import mobile.app.com.todolist.adapter.TaskListAdapter;
import mobile.app.com.todolist.configuration.DbHelper;
import mobile.app.com.todolist.dao.Task;
import mobile.app.com.todolist.services.TaskService;

public class MainActivity extends AppCompatActivity {

	List<Task> tasks;
	DbHelper dbHelper;
	TaskService taskService;
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		dbHelper = new DbHelper(this);
		taskService = new TaskService(dbHelper);
		listView = (ListView) findViewById(R.id.taskList);
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
		registerForContextMenu(listView);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.task_context_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {
			case R.id.edit_item:
				Log.d("MYLOG", "id is " + info.id);
				return true;
			default:
				return super.onContextItemSelected(item);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			displayTasks(taskService.getAllTasks());
		}
	}

	private void displayTasks(List<Task> tasks) {
		TaskListAdapter adapter = new TaskListAdapter(this, tasks);
		if (listView != null) {
			listView.setAdapter(adapter);
		}
	}
}
