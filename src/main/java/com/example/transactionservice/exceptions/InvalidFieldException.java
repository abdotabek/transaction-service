package com.example.transactionservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class InvalidFieldException extends RuntimeException {

    private String message;
    private Set<String> fields;
}
