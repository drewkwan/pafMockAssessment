package paf.mockAssessment.pafMockAssessment.models;

import java.util.Date;

public class Task {

    private String taskId;
    private String description;
    private int priority;
    private String dueDate;

    
    public Task(String description, int priority, String dueDate) {
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
    }
    
    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public String getDueDate() {
        return dueDate;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
