package paf.mockAssessment.pafMockAssessment.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import paf.mockAssessment.pafMockAssessment.models.User;
import static paf.mockAssessment.pafMockAssessment.repositories.Queries.*;


@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<String> findUserIdByUsername (String username) {

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_FIND_USERID_BY_USERNAME, username);
        if (!rs.next()) {
            return Optional.empty();
        } else {
            String userId = rs.getString("user_id");
            return Optional.of(userId);
        }

    }

    public Optional<User> findUserByUserId (String userId) {

        //perform the query
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_FIND_USER_BY_ID, userId);

        if (!rs.next()) {
            return Optional.empty();
        }  else {
            User user = User.create(rs);
            return Optional.of(user);
        }

    }

    public int insertUser(User user) {

        
        //perform the query 
        return jdbcTemplate.update(SQL_INSERT_NEW_USER, 
                            user.getUserId(),
                            user.getUsername(),
                            user.getFullName());

    }
    
}
