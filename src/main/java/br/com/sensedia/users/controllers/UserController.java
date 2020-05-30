package br.com.sensedia.users.controllers;

import br.com.sensedia.users.entities.UserEntity;
import br.com.sensedia.users.repositories.UserRepository;
import br.com.sensedia.users.repositories.UserRepositoryCustom;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final UserRepositoryCustom userRepositoryCustom;

    public UserController(UserRepository userRepository, UserRepositoryCustom userRepositoryCustom) {
        this.userRepository = userRepository;
        this.userRepositoryCustom = userRepositoryCustom;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    UserEntity createUser(@RequestBody UserEntity newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping(path = "/users/{id}")
    UserEntity getUser(@PathVariable String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/users")
    List<UserEntity> getUsersByQueryParam(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) String email) {
        if (Objects.nonNull(firstName) || Objects.nonNull(lastName) || Objects.nonNull(email)) {
            List<UserEntity> users = userRepositoryCustom.findAll(firstName, lastName, email);
            return users;
        } else {
            List<UserEntity> users = userRepository.findAll();
            return users;
        }
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/users/{id}")
    UserEntity updateUser(@RequestBody UserEntity newUser, @PathVariable String id) {
        newUser.setId(id);
        return userRepository.save(newUser);
    }

    @PatchMapping("/users/{id}")
    UserEntity partialUpdateUser(@RequestBody UserEntity partialUser, @PathVariable String id) {

        return userRepository.findById(id)
                .map(user -> {
                    if (Objects.nonNull(partialUser.getFirstName())) {
                        user.setFirstName(partialUser.getFirstName());
                    }
                    if (Objects.nonNull(partialUser.getLastName())) {
                        user.setLastName(partialUser.getLastName());
                    }
                    if (Objects.nonNull(partialUser.getBirthDate())) {
                        user.setBirthDate(partialUser.getBirthDate());
                    }
                    if (Objects.nonNull(partialUser.getEmail())) {
                        user.setEmail(partialUser.getEmail());
                    }
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

}
