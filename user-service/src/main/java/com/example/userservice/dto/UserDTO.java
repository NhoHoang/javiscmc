package com.example.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
public class UserDTO {
    @NotBlank
    @Pattern(regexp = "^([A-Z|a-z|0-9](\\.|_){0,1})+[A-Z|a-z|0-9]\\@([A-Z|a-z|0-9])+((\\.){0,1}[A-Z|a-z|0-9]){2}\\.[a-z]{2,3}$" , message = "invalid email format")
    private String email;

    @NotBlank
    @Length(min = 8)
    private String password;

    @NotNull
    @Min(value = 15000,message = "Basic salary is below require")
    @JsonProperty("based_salary")
    private Double salary;
}
