package com.example.shoppingApp.user;

import com.example.shoppingApp.exception.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserService {

    private final EntityManager entityManager;
    
    private final AppUserRepository userRepository;

    @Transactional
    public AppUser addUser(AppUser user) {
        entityManager.persist(user);
        return user;
    }

    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }

    public AppUser getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    return  new NotFoundException("User with id " + id + " not found");
                });
    }
}
