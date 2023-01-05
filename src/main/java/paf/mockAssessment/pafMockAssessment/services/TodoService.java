package paf.mockAssessment.pafMockAssessment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paf.mockAssessment.pafMockAssessment.models.User;
import paf.mockAssessment.pafMockAssessment.repositories.TaskRepository;

@Service
public class TodoService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Transactional(rollbackFor = TodoException.class)

    public void upsertTask(User user) throws TodoException {

        //Find the user if it exists
        if (userService.findUserByUserId(user.getUserId()).isEmpty()) {
            userService.insertUser(user);
            taskRepository.insertTask(user.getTasks(), user.getUserId());
        }

        else {
            taskRepository.insertTask(user.getTasks(), user.getUserId());
        }
    }
}
