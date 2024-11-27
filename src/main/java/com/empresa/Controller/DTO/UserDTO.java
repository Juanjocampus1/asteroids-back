package com.empresa.Controller.DTO;

import com.empresa.Entities.Score;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Score> scoreslist = new ArrayList<>();
}
