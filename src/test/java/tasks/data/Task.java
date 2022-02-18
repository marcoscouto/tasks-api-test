package tasks.data;

public class Task {

    private String task;
    private String dueDate;

    public Task() {
    }

    public Task(String task, String dueDate) {
        this.task = task;
        this.dueDate = dueDate;
    }

    public String getTask() {
        return task;
    }

    public String getDueDate() {
        return dueDate;
    }

}
