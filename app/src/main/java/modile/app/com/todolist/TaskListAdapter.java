package modile.app.com.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import modile.app.com.todolist.dao.Task;

public class TaskListAdapter extends BaseAdapter {

	Context context;
	LayoutInflater layoutInflater;
	List<Task> tasks;

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
		TextView item = (TextView) view.findViewById(R.id.item_name);
		item.setText(tasks.get(position).getName());
		return view;
	}
}
