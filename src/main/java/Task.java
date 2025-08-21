/**
 * Class representing Tasks.
 */
public class Task {
    private boolean completed; // whether Task is completed
    private String desc; // description of Task

    public Task(String desc) {
        this.desc = desc;
        this.completed = false;
    }

    /**
     * Returns whether Task is completed.
     *
     * @return whether Task is completed.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Returns this Task as String in following format:
     * "[<X if complete, blank if not>] <desc>"
     *
     * @return formatted String
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.completed ? "X" : " ", this.desc);
    }
}
