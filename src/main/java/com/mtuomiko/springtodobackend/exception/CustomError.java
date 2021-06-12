package com.mtuomiko.springtodobackend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomError {
    private String error;
    private String message;
    private String path;
    private int status;
}
