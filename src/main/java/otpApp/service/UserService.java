package otpApp.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otpApp.entity.User;
import otpApp.exception.UserNotFound;
import otpApp.repo.UserRepo;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;


    // Get User by Id
    public User getUser(Long id) {
        return userRepo.findById(id)
                .orElseThrow(UserNotFound::new);
    }

    // Get User by Email

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(UserNotFound::new);
    }




}
