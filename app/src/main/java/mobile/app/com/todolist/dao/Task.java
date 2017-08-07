package mobile.app.com.todolist.dao;

public class Task {

	private Integer id;
	private String name;
	private boolean isDone;

	public Task() {
	}

	public Task(String name, boolean isDone) {
		this.name = name;
		this.isDone = isDone;
	}

	public Task(Integer id, String name, boolean isDone) {
		this.id = id;
		this.name = name;
		this.isDone = isDone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
