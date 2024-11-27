package com.empresa.Entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long score;
    private Long enemysKilled;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}