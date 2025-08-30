package uxie.interfaces;

import uxie.exceptions.UxieIllegalOpException;
import uxie.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    /**
     * Generates empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Generates initialized TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to end of list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes task matching index from list.
     *
     * @return desc of deleted task
     * @throws UxieIllegalOpException taskIndex is out of bounds in list
     */
    public String deleteTask(int taskIndex) throws UxieIllegalOpException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new UxieIllegalOpException("That task doesn't exist.");
        }
        Task t = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        return t.getDesc();
    }

    /**
     * Find tasks from search String.
     * Tasks are selected if their description contains exactly the search String.
     *
     * @return list of indices matching selected Tasks.
     */
    public List<Integer> findContainingString(String search) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDesc().contains(search)) { // contains exactly
                result.add(i);
            }
        }
        return result;
    }

    /**
     * Return task matching index.
     *
     * @throws UxieIllegalOpException when taskIndex is out of bounds.
     */
    public Task getTask(int taskIndex) throws UxieIllegalOpException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new UxieIllegalOpException("That task doesn't exist.");
        }
        return tasks.get(taskIndex);
    }

    /**
     * Returns size of list (no. of tasks).
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Marks task matching index as complete.
     * Returns description of task marked complete.
     * @throws UxieIllegalOpException if task index is out of bounds or task is already complete.
     */
    public String markCompleted(int taskIndex) throws UxieIllegalOpException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new UxieIllegalOpException("That task doesn't exist.");
        }
        Task t = tasks.get(taskIndex);
        t.markCompleted();
        return t.getDesc();
    }

    /**
     * Marks task matching index as incomplete.
     * Returns description of task marked incomplete.
     * @throws UxieIllegalOpException if task index is out of bounds or task is already incomplete.
     */
    public String markIncomplete(int taskIndex) throws UxieIllegalOpException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new UxieIllegalOpException("That task doesn't exist.");
        }
        Task t = tasks.get(taskIndex);
        t.markIncomplete();
        return t.getDesc();
    }

}
