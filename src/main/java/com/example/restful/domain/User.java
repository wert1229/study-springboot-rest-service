package com.example.restful.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    private int id;

    @Size(min = 2, message = "2글자 이상")
    private String name;

    @Past
    private Date birthDate;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String ssn;
}
