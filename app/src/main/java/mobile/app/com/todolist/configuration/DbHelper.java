package mobile.app.com.todolist.configuration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mobile.app.com.todolist.Db;

public class DbHelper extends SQLiteOpenHelper {


	public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(Db.CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
