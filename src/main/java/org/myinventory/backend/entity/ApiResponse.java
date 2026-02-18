package org.myinventory.backend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
@Schema(description = "공통 응답 객체")
public class ApiResponse<T> {

    private int status;
    private String message;
    private T data;
}