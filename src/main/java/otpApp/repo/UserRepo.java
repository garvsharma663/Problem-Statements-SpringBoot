package otpApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import otpApp.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Boolean existsUserByEmail(String email);
    Optional<User> findByEmail(String email);
}
