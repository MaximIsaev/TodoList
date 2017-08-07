package mobile.app.com.todolist.configuration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

	public static final String TABLE_TASKS = "tasks";


	public DbHelper(Context context) {
		super(context, "todolistdb", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE " + TABLE_TASKS + "("
				+ "id integer primary key autoincrement,"
				+ "name text,"
				+ "isDone integer default 0" + ");");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
