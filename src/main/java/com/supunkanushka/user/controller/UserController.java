/*
 * Copyright (c) 2018.
 * Created By : Supun Kanushka
 */

package com.supunkanushka.user.controller;

import com.supunkanushka.user.model.UserModel;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/user")
@RestController
public class UserController {

    private List<UserModel> userModels = new ArrayList<>();

    @RequestMapping(
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public @NonNull
    List<UserModel> getAllUsers() {
        return this.userModels;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @NonNull
    ResponseEntity addUser(@RequestBody UserModel userModel) {
        this.userModels.add(userModel);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @NonNull
    ResponseEntity removeUser(@RequestBody UserModel userModel) {

        Optional<UserModel> user =
                this.userModels
                        .stream()
                        .filter(userData -> userData.getName().equals(userModel.getName()))
                        .findAny();

        user.ifPresent(userData -> this.userModels.remove(userData));

        if (user.isPresent()) {
            this.userModels.remove(user.get());
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
