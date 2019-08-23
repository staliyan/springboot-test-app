package com.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Validation exception")
public class ValidationException extends RuntimeException
{

    static final long serialVersionUID = -3387516993224229948L;


    public ValidationException(String message)
    {
        super(message);
    }

}
