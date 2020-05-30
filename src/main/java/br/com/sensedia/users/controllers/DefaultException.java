package br.com.sensedia.users.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultException {

    private String message;

    public DefaultException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultException that = (DefaultException) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "ApplicationException{" +
                "message='" + message + '\'' +
                '}';
    }
}

