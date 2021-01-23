import java.time.LocalDate;

public class TaskEvent extends Task {

    private final LocalDate time;

    public TaskEvent(String name, LocalDate time) {
        super(name);
        this.time = time;
    }

    public TaskEvent(String name, boolean done, LocalDate time) {
        super(name, done);
        this.time = time;
    }

    public TaskEvent setDone(boolean done) {
        return new TaskEvent(this.name, done, this.time);
    }

    @Override
    protected TaskEvent clone() {
        return new TaskEvent(this.name, this.done, this.time);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.time.format(Task.formatter));
    }
}
