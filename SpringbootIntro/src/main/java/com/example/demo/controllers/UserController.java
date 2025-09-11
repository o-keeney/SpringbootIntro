package com.example.demo.controllers;

import com.example.demo.User;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.demo.repos.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController{
    private final UserRepository repository;

    public UserController(UserRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping
    public List<User> all()
    {
        return repository.findAll();
    }

    @PostMapping
    public User create(@RequestBody User user)
    {
            return repository.save(user);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id){
        return repository.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user)
    {
        return repository.findById(id)
                .map(u -> {
                    u.setName(user.getName());
                    u.setEmail((user.getEmail()));
                    return repository.save(u);
                }).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)
    {
        repository.deleteById(id);
    }
}