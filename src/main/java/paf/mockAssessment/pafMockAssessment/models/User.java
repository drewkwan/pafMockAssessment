package paf.mockAssessment.pafMockAssessment.models;

import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class User {
    private String userId;
    private String username;
    private String fullName;
    private List<Task> tasks = new LinkedList<>();
    

    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    public void addTask(Task task) {
        this.tasks.add(task);
    }
    public void deleteTask(Task task) {
        this.tasks.remove(task);
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public static User create(SqlRowSet rs) {
        User user = new User();
        user.setUserId(rs.getString("user_id"));
        user.setUsername(rs.getString("username"));
        user.setFullName(rs.getString("full_name"));
        return user;
    }
}
