package com.udacity.jdnd.course3.critter.infrastructure.exception.model;

import com.sun.istack.NotNull;

public class ErrorModel {
    @NotNull
    private int code;

    @NotNull
    private String message;

    @NotNull
    private String description;

    public ErrorModel() {
    }

    public ErrorModel(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
