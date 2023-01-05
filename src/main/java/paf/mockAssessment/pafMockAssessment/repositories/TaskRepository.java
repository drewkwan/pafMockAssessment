package paf.mockAssessment.pafMockAssessment.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import paf.mockAssessment.pafMockAssessment.models.Task;
import static paf.mockAssessment.pafMockAssessment.repositories.Queries.*;

import java.util.List;

@Repository
public class TaskRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    // public Integer insertTask(User user, Task task) {

    //     return jdbcTemplate.update(SQL_INSERT_NEW_TASK, 
    //                                 task.getDescription(),
    //                                 task.getPriority(),
    //                                 user.getUserId(),
    //                                 task.getDueDate());

    // }

    public void insertTask(List<Task> t, String userId) {

        List<Object[]> data = t.stream()
                            .map(tasks -> {
                                Object[] l = new Object[4];
                                
                                l[0]=tasks.getDescription();
                                l[1]=tasks.getPriority();
                                l[2]=userId;
                                l[3]=tasks.getDueDate();
                                return l;
                            })
                            .toList();

        jdbcTemplate.batchUpdate(SQL_INSERT_NEW_TASK, data);

    }

}
