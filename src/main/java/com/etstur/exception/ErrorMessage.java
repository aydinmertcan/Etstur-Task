package com.etstur.exception;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
    private int status;
    private String message;
    private String path;
    private long timestamp=new Date().getTime();


    private Map<String,String> validationErrors;

    public ErrorMessage(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }
}