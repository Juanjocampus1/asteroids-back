package com.empresa.Controller.DTO;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDTO {

    private Long id;
    private Long score;
    private Long enemysKilled;
}
