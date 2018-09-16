package com.supunkanushka.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private int age;
}
