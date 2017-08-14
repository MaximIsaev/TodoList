package mobile.app.com.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import mobile.app.com.todolist.dao.Task;

public class AddNewTaskActivity extends AppCompatActivity {

	TextView textView;
	Db db;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_task_activity);
		textView = (TextView) findViewById(R.id.addNewTaskInput);
		db = new Db(this);
		db.open();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.new_task_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		String taskName = textView.getText().toString();
		Task task = new Task(taskName, false);
		db.saveTask(task);
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		db.close();
	}
}
