package modile.app.com.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskListAdapter extends BaseAdapter {

	Context context;
	LayoutInflater layoutInflater;
	ArrayList<String> tasks;

	public TaskListAdapter(Context context, ArrayList<String> tasks) {
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
		TextView itemName = (TextView) view.findViewById(R.id.item_name);
		itemName.setText(tasks.get(position));
		return view;
	}
}
