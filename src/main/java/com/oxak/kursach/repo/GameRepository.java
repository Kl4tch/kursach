package com.oxak.kursach.repo;

import com.oxak.kursach.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByTitleContainingIgnoreCase(String title);
}