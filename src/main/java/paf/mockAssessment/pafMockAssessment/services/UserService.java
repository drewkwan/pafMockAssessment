package paf.mockAssessment.pafMockAssessment.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paf.mockAssessment.pafMockAssessment.models.User;
import paf.mockAssessment.pafMockAssessment.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUserByUserId(String userId) {
        return userRepository.findUserByUserId(userId);
    }

    public String insertUser(User user) {

        String userId = UUID.randomUUID().toString().substring(0, 8);
        System.out.printf("UserId = %s\n", userId);

        user.setUserId(userId);

        //Perform the query
        userRepository.insertUser(user);
        
        return userId;

    }
}
