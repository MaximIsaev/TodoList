package mobile.app.com.todolist.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import mobile.app.com.todolist.Db;
import mobile.app.com.todolist.R;
import mobile.app.com.todolist.dao.Task;

public class EditTaskDialog extends android.support.v4.app.DialogFragment {

	Db db;
	Task task;
	EditText text;

	public EditTaskDialog() {

	}

	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
	}

	@Override
	public void onStart() {

		super.onStart();
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.edit_dialog, null);
		Bundle arguments = getArguments();
		if (arguments != null) {
			long id = arguments.getLong("id");
			String name = arguments.getString("name");
			task = new Task(id, name, false);
			text = (EditText) view.findViewById(R.id.edit_dialog_task_name);
			text.setText(name);
		}
		builder.setView(view)
				.setTitle("Edit your task")
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Log.d("TEST", "Ok is clicked");
						String newName = text.getText().toString();
						task.setName(newName);
						db.update(task);
						getActivity().getSupportLoaderManager().getLoader(0).forceLoad();
					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Log.d("TEST", "Cancel is clicked");
						dialog.cancel();
					}
				});

		db = new Db(getActivity());
		db.open();
		return builder.create();
	}
}
