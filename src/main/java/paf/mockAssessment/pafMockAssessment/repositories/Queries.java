package paf.mockAssessment.pafMockAssessment.repositories;

public class Queries {
    public static final String SQL_FIND_USERID_BY_USERNAME ="SELECT user_id FROM user WHERE username=?";
    public static final String SQL_FIND_USER_BY_ID = "SELECT * FROM user WHERE user_id = ?";
    public static final String SQL_INSERT_NEW_USER ="INSERT INTO user(user_id, username, full_name) VALUES(?,?,?)";
    public static final String SQL_INSERT_NEW_TASK ="INSERT INTO task(description, priority, user_id, due_date) VALUES(?, ?, ?, ?)";

}