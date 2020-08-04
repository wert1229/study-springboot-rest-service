package com.example.restful.controller;

import com.example.restful.domain.User;
import com.example.restful.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id) {
        User user = userService.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("test");
        }

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getUsers());
        entityModel.add(linkBuilder.withRel("all-users"));

//        SimpleBeanPropertyFilter fitler = SimpleBeanPropertyFilter
//                .filterOutAllExcept("id", "name", "birthDate");
//
//        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo", fitler);
//
//        MappingJacksonValue value = new MappingJacksonValue(user);
//        value.setFilters(filterProvider);

        return entityModel;
    }

    @PostMapping("/users")
    public void createUser(@Valid @RequestBody User user) {
        userService.save(user);
    }
}
