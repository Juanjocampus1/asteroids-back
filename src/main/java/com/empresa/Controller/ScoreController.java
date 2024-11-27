package com.empresa.Controller;

import com.empresa.Controller.DTO.ScoreDTO;
import com.empresa.Entities.Score;
import com.empresa.Service.Impl.ScoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/score")
public class ScoreController {

    @Autowired
    private ScoreServiceImpl scoreServiceImpl;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<ScoreDTO> scoreList = scoreServiceImpl.findAll()
                .stream()
                .map(score -> ScoreDTO.builder()
                        .id(score.getId())
                        .score(score.getScore())
                        .enemysKilled(score.getEnemysKilled())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(scoreList);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Score> scoreOptional = scoreServiceImpl.findById(id);

        if (scoreOptional.isPresent()) {
            Score score = scoreOptional.get();
            ScoreDTO scoreDTO = ScoreDTO.builder()
                    .id(score.getId())
                    .score(score.getScore())
                    .enemysKilled(score.getEnemysKilled())
                    .build();
            return ResponseEntity.ok(scoreDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ScoreDTO scoreDTO) {
        Score score = Score.builder()
                .id(scoreDTO.getId())
                .score(scoreDTO.getScore())
                .enemysKilled(scoreDTO.getEnemysKilled())
                .build();

        return ResponseEntity.ok(scoreServiceImpl.save(score));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody ScoreDTO scoreDTO, @PathVariable Long id) {
        Optional<Score> scoreOptional = scoreServiceImpl.findById(id);

        if (scoreOptional.isPresent()) {
            Score score = scoreOptional.get();
            score.setScore(scoreDTO.getScore());
            score.setEnemysKilled(scoreDTO.getEnemysKilled());

            Score updatedScore = scoreServiceImpl.save(score);
            ScoreDTO updatedScoreDTO = ScoreDTO.builder()
                    .id(updatedScore.getId())
                    .score(updatedScore.getScore())
                    .enemysKilled(updatedScore.getEnemysKilled())
                    .build();

            return ResponseEntity.ok(updatedScoreDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<Score> scoreOptional = scoreServiceImpl.findById(id);

        if (scoreOptional.isPresent()) {
            scoreServiceImpl.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}