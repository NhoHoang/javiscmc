package com.example.userservice.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse<T> implements Serializable {
    private String code;
    private String message;
    private T data;

    public static CustomResponse ok(Object data) {
        return CustomResponse.builder().code(HttpStatusConstants.SUCCESS_CODE).message(HttpStatusConstants.SUCCESS_MESSAGE).data(data).build();
    }

    public static CustomResponse error(String code, String message) {
        return CustomResponse.builder().code(code).message(message).build();
    }
    public static CustomResponse buildAll(String code, String message, Object data) {
        return CustomResponse.builder().code(code).message(message).data(data).build();
    }

}
