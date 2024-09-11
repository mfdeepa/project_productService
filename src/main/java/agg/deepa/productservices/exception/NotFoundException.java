package agg.deepa.productservices.exception;

import org.springframework.web.bind.annotation.ResponseStatus;


public class NotFoundException extends Exception{
    public NotFoundException(String message){
        super(message);
    }
}
