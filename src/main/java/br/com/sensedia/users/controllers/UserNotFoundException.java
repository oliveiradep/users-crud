package br.com.sensedia.users.controllers;

public class UserNotFoundException extends RuntimeException {

    UserNotFoundException(String id) {
        super("User " + id + " not found.");
    }

}
