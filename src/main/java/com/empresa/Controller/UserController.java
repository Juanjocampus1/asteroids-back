package com.empresa.Controller;

import com.empresa.Controller.DTO.UserDTO;
import com.empresa.Entities.User;
import com.empresa.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<UserDTO> userlist = userService.findAll()
                .stream()
                .map(user -> UserDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .scoreslist(user.getScores())
                        .build())
                .toList();

        return ResponseEntity.ok(userlist);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isPresent()){
            User user = userOptional.get();

            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .scoreslist(user.getScores())
                    .build();
            return ResponseEntity.ok(userDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        User user = userService.findByName(name);

        if (user != null){
            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .scoreslist(user.getScores())
                    .build();
            return ResponseEntity.ok(userDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);

        if (user != null){
            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .scoreslist(user.getScores())
                    .build();
            return ResponseEntity.ok(userDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isPresent()) {
            userService.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody UserDTO userDTO) {
        User user = User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();

        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setScores(userDTO.getScoreslist());

            User updatedUser = userService.save(user);
            UserDTO updatedUserDTO = UserDTO.builder()
                    .id(updatedUser.getId())
                    .name(updatedUser.getName())
                    .email(updatedUser.getEmail())
                    .password(updatedUser.getPassword())
                    .scoreslist(updatedUser.getScores())
                    .build();

            return ResponseEntity.ok(updatedUserDTO);
        }

        return ResponseEntity.notFound().build();
    }
}