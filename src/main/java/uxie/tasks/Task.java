package uxie.tasks;

import uxie.exceptions.UxieIllegalOpException;

/**
 * Tasks are tasks to be completed.
 *
 * @author junyan-k
 */
public class Task {

    /** Whether Task is completed. */
    private boolean isCompleted;

    /** Description of Task. */
    private String desc;


    public Task(String desc) {
        this.desc = desc;
        this.isCompleted = false;
    }

    /**
     * Returns whether Task is completed.
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Marks Task as complete.
     * If Task is already complete, throws {@link UxieIllegalOpException}.
     */
    public void markCompleted() throws UxieIllegalOpException {
        if (!this.isCompleted) {
            this.isCompleted = true;
        } else {
            throw new UxieIllegalOpException("This task is already complete.");
        }
    }

    /**
     * Marks Task as incomplete.
     * If Task is already incomplete, throws {@link UxieIllegalOpException}.
     */
    public void markIncomplete() throws UxieIllegalOpException {
        if (this.isCompleted) {
            this.isCompleted = false;
        } else {
            throw new UxieIllegalOpException("This task is already incomplete.");
        }
    }

    /**
     * Returns description of Task.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Returns this Task as String.
     * Format: "[<X if complete, blank if not>] <desc>"
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.completed ? "X" : " ", this.desc);
    }
}
