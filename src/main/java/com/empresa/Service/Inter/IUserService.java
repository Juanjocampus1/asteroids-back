// IUserService.java
package com.empresa.Service.Inter;

import com.empresa.Entities.User;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();
    Optional<User> findById(Long id); // Ensure this matches the implementation
    User save(User user);
    void deleteById(Long id);
    User findByName(String name);
    User findByEmail(String email);
}