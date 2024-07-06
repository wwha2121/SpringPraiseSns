package service.backend_spring3.repository;

import service.backend_spring3.domain.User;

import java.util.List;
import java.util.Optional;
public interface  UserRepository {
    void save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByName(String name);
    List<User> findAll();
}