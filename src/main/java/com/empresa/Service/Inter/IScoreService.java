package com.empresa.Service.Inter;

import com.empresa.Entities.Score;

import java.util.List;
import java.util.Optional;

public interface IScoreService {
    List<Score> findAll();
    Optional<Score> findById(Long id);
    Score save(Score score);
    void deleteById(Long id);
}
