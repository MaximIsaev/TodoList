package mobile.app.com.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import mobile.app.com.todolist.configuration.DbHelper;
import mobile.app.com.todolist.dao.Task;

public class Db {

	private static final String DB_NAME = "todolistdb";
	private static final int DB_VERSION = 1;
	private static final String DB_TABLE = "tasks";

	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_ITEM_NAME = "name";
	public static final String COLUMN_IS_DONE = "isDone";

	public static final String CREATE =
			"CREATE TABLE " + DB_TABLE + "("
					+ COLUMN_ID + " integer primary key autoincrement,"
					+ COLUMN_ITEM_NAME + " text,"
					+ COLUMN_IS_DONE + " integer default 0" + ");";

	private final Context myCtx;

	private DbHelper dbHelper;
	private SQLiteDatabase db;

	public Db(Context ctx) {
		myCtx = ctx;
	}

	public void open() {
		dbHelper = new DbHelper(myCtx, DB_NAME, null, DB_VERSION);
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		if (dbHelper != null) {
			dbHelper.close();
		}
	}

	public Cursor getAllData() {
		return db.query(DB_TABLE, null, null, null, null, null, null);
	}

	public void saveTask(Task task) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_IS_DONE, false);
		values.put(COLUMN_ITEM_NAME, task.getName());
		db.insert(DB_TABLE, null, values);
	}

	public void delete(long id) {
		int delete = db.delete(DB_TABLE, COLUMN_ID + " = " + id, null);
		Log.d("TEST", "delete rows " + delete);
	}

	public Task getOne(Long id) {
		Cursor cursor = db.query(DB_TABLE, null, COLUMN_ID + " = " + id, null, null, null, null);
		Task task = new Task();
		if (cursor.moveToFirst()) {
			int idIndex = cursor.getColumnIndex(COLUMN_ID);
			int nameIndex = cursor.getColumnIndex(COLUMN_ITEM_NAME);
			task.setId(cursor.getLong(idIndex));
			task.setName(cursor.getString(nameIndex));
		}
		return task;
	}

	public void update(Task task) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_ITEM_NAME, task.getName());
		db.update(DB_TABLE, values, COLUMN_ID + " = " + task.getId(), null);
	}

}
