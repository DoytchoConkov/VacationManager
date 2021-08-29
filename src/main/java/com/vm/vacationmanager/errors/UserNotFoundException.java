package com.vm.vacationmanager.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.vm.vacationmanager.constants.Constants.USERNAME_NOT_FOUND;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = USERNAME_NOT_FOUND)
public class UserNotFoundException extends NullPointerException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
