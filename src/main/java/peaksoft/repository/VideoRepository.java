package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}