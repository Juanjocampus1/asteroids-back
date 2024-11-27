package com.empresa.Service.Impl;

import com.empresa.Entities.Score;
import com.empresa.Repository.ScoreRepository;
import com.empresa.Service.Inter.IScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreServiceImpl implements IScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> findAll() {
        return scoreRepository.findAll();
    }

    public Optional<Score> findById(Long id) {
        return scoreRepository.findById(id);
    }

    public Score save(Score score) {
        return scoreRepository.save(score);
    }

    public void deleteById(Long id) {
        scoreRepository.deleteById(id);
    }
}
