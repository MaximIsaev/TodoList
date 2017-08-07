package mobile.app.com.todolist.services;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mobile.app.com.todolist.configuration.DbHelper;
import mobile.app.com.todolist.dao.Task;

public class TaskService {

	private DbHelper dbHelper;

	public TaskService(DbHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	public List<Task> getAllTasks() {
		if (dbHelper == null) {
			return null;
		}
		List<Task> tasks = new ArrayList<>();
		SQLiteDatabase database = dbHelper.getReadableDatabase();
		Cursor cursor = database.query(DbHelper.TABLE_TASKS, null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			int idColumnIndex = cursor.getColumnIndex("id");
			int nameColumnIndex = cursor.getColumnIndex("name");
			int isDoneColumnIndex = cursor.getColumnIndex("isDone");
			do {
				Task task = new Task();
				task.setId(cursor.getInt(idColumnIndex));
				task.setName(cursor.getString(nameColumnIndex));
				int isDone = cursor.getInt(isDoneColumnIndex);
				if (isDone == 0) {
					task.setDone(false);
				} else {
					task.setDone(true);
				}
				tasks.add(task);
			} while (cursor.moveToNext());
		}
		database.close();
		return tasks;
	}

	public void saveTask(Task task) {
		if (task == null) {
			return;
		}
		ContentValues cv = new ContentValues();
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		cv.put("name", task.getName());
		cv.put("isDone", task.isDone());
		database.insert(DbHelper.TABLE_TASKS, null, cv);
		database.close();
	}


}
