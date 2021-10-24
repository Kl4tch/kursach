package com.oxak.kursach.repo;

import com.oxak.kursach.models.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {
    List<Platform> findByTitleContainingIgnoreCase(String title);
}
