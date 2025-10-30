package org.videoRentalSystem.repository;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.videoRentalSystem.model.Video;

import java.util.List;
import java.util.Optional;

public interface VideoRepo extends JpaRepository<Video, Long> {

    // We can add methods here and JPA automatically converts it into SQL.
    // THE FOLLOWING SYNTAX IS USED :

    // findBy / readBy / getBy / countBy / existsBy / deleteBy + <Property> + [Keyword + Property]...
    Optional<Video> findByTitleIgnoreCase(String title); // Using Optional class for the null safety in case video doesn't exist.
    Optional<Video> findById(Long id);


}
