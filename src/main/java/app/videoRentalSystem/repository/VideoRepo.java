package app.videoRentalSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.videoRentalSystem.model.Video;

import java.util.Optional;

public interface VideoRepo extends JpaRepository<Video, Long> {

    // We can add methods here and JPA automatically converts it into SQL.
    // THE FOLLOWING SYNTAX IS USED :

    // findBy / readBy / getBy / countBy / existsBy / deleteBy + <Property> + [Keyword + Property]...
    Optional<Video> findByTitleIgnoreCase(String title); // Using Optional class for the null safety in case video doesn't exist.
    Optional<Video> findById(Long id);


}
