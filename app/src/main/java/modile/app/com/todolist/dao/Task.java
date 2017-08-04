package modile.app.com.todolist.dao;

public class Task {

	private String name;
	private boolean isDone;

	public Task(String name, boolean isDone) {
		this.name = name;
		this.isDone = isDone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean done) {
		isDone = done;
	}
}
