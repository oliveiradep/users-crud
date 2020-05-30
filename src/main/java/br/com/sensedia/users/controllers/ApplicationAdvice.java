package br.com.sensedia.users.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@PropertySource(value = {"classpath:ValidationMessages.properties"})
@ControllerAdvice
public class ApplicationAdvice extends DefaultErrorAttributes {

    @Value("${invalid_format_exception}")
    private String invalidFormatMessage;

    @Value("${invalid_method_exception}")
    private String invalidMethod;

    @Value("${invalid_media_type_exception}")
    private String invalid_media_type_exception;

    @Value("${unexpected_exception}")
    private String unexpected;

    @ResponseBody
    @ExceptionHandler({HttpMessageNotReadableException.class})
    ResponseEntity<DefaultException> notReadableHandler() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        DefaultException messageResponse = new DefaultException(invalidFormatMessage);
        return new ResponseEntity(messageResponse, headers, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    ResponseEntity<DefaultException> methodNotSupportedHandler() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        DefaultException messageResponse = new DefaultException(invalidMethod);
        return new ResponseEntity(messageResponse, headers, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ResponseBody
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    ResponseEntity<DefaultException> mediaTypeNotSupportedHandler() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        DefaultException messageResponse = new DefaultException(invalid_media_type_exception);
        return new ResponseEntity(messageResponse, headers, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ResponseBody
    @ExceptionHandler({UserNotFoundException.class})
    ResponseEntity<DefaultException> userNotFoundHandler(UserNotFoundException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        DefaultException messageResponse = new DefaultException(ex.getMessage());
        return new ResponseEntity(messageResponse, headers, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler({java.lang.Exception.class})
    ResponseEntity<DefaultException> exceptionHandler() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        DefaultException messageResponse = new DefaultException(unexpected);
        return new ResponseEntity(messageResponse, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}