package com.empresa.Repository;

import com.empresa.Entities.Score;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Long> {
    List<Score> findAll();
    Optional<Score> findById(Long id);
    Score save(Score score);
    void deleteById(Long id);
}