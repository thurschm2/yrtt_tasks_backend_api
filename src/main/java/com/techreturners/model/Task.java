package com.techreturners.model;

public class Task {
    private String taskId;
    private String description;
    private boolean complete;

    public Task(String taskId, String description, boolean complete) {
        this.taskId = taskId;
        this.description = description;
        this.complete = complete;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public boolean isComplete() {
        return complete;
    }
}
