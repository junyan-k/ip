package uxie.tasks;

import uxie.exceptions.UxieIllegalOpException;

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
     * Marks Task as complete. If Task is already complete,
     * throws Exception.
     */
    public void markCompleted() throws UxieIllegalOpException {
        if (!this.completed) {
            this.completed = true;
        } else {
            throw new UxieIllegalOpException("This task is already complete.");
        }
    }

    /**
     * Marks Task as incomplete. If Task is already incomplete,
     * throws Exception.
     */
    public void markIncomplete() throws UxieIllegalOpException {
        if (this.completed) {
            this.completed = false;
        } else {
            throw new UxieIllegalOpException("This task is already incomplete.");
        }
    }

    /**
     * Returns description of Task.
     * @return description of Task.
     */
    public String getDesc() {
        return this.desc;
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
