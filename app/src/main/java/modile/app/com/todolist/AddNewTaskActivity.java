package modile.app.com.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AddNewTaskActivity extends AppCompatActivity {


	TextView textView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_task_activity);
		textView = (TextView) findViewById(R.id.addNewTaskInput);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.new_task_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		String task = textView.getText().toString();
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("tmp", task);
		setResult(RESULT_OK, intent);
		finish();
		return super.onOptionsItemSelected(item);
	}
}
