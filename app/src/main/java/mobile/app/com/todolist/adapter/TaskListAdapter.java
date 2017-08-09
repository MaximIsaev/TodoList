package mobile.app.com.todolist.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import mobile.app.com.todolist.R;
import mobile.app.com.todolist.dao.Task;

public class TaskListAdapter extends BaseAdapter implements View.OnCreateContextMenuListener {

	Context context;
	LayoutInflater layoutInflater;
	List<Task> tasks;
	TextView textView;
	CheckBox checkBox;

	public TaskListAdapter(Context context, List<Task> tasks) {
		this.context = context;
		this.tasks = tasks;
		this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return tasks.size();
	}

	@Override
	public Object getItem(int position) {
		return tasks.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = layoutInflater.inflate(R.layout.item_layout, parent, false);
		}
		textView = (TextView) view.findViewById(R.id.item_name);
		textView.setText(tasks.get(position).getName());
		checkBox = (CheckBox) view.findViewById(R.id.is_done_checkbox);
		checkBox.setChecked(tasks.get(position).isDone());
		view.setOnCreateContextMenuListener(this);
		return view;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

	}
}
