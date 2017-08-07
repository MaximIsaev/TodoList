package mobile.app.com.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import mobile.app.com.todolist.configuration.DbHelper;
import mobile.app.com.todolist.dao.Task;
import mobile.app.com.todolist.services.TaskService;

public class AddNewTaskActivity extends AppCompatActivity {


	TextView textView;
	DbHelper dbHelper;
	TaskService taskService;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_task_activity);
		textView = (TextView) findViewById(R.id.addNewTaskInput);
		dbHelper = new DbHelper(this);
		taskService = new TaskService(dbHelper);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.new_task_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		saveTask();
		Intent intent = new Intent(this, MainActivity.class);
		setResult(RESULT_OK, intent);
		finish();
		return super.onOptionsItemSelected(item);
	}

	private void saveTask() {
		String taskName = textView.getText().toString();
		Task task = new Task(taskName, false);
		taskService.saveTask(task);
	}
}
