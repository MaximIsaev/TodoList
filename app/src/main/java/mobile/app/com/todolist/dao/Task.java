package mobile.app.com.todolist.dao;

public class Task {

	private Long id;
	private String name;
	private boolean isDone;

	public Task() {
	}

	public Task(String name, boolean isDone) {
		this.name = name;
		this.isDone = isDone;
	}

	public Task(Long id, String name, boolean isDone) {
		this.id = id;
		this.name = name;
		this.isDone = isDone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
