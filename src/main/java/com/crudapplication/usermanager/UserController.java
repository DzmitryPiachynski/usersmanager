package com.crudapplication.usermanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public List<User> getAll() {
        try {
            return userRepository.getAll();
        } catch (Throwable err) {
            throw new Error(err);
        }
    }
    @GetMapping("/{id}")
    public User getById(@PathVariable("id") int id ) {
        return userRepository.getById(id);
    }
    @PostMapping("")
    public int add(@RequestBody List<User> users) {
        return userRepository.save(users);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody User updatedUser) {
        User user = userRepository.getById(id);

        if(user != null) {
            user.setFirstname(updatedUser.getFirstname());
            user.setLastname(updatedUser.getLastname());
            user.setAge(updatedUser.getAge());

            userRepository.update(user);

            return 1;
        } else {
            return -1;
        }
    }
}
