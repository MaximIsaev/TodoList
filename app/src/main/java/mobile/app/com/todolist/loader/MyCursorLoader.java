package mobile.app.com.todolist.loader;

import android.content.Context;
import android.database.Cursor;

import mobile.app.com.todolist.Db;

public class MyCursorLoader extends android.support.v4.content.CursorLoader {

	Db db;

	public MyCursorLoader(Context context, Db db) {
		super(context);
		this.db = db;
	}

	@Override
	public Cursor loadInBackground() {
		return db.getAllData();
	}
}
