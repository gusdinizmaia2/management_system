package com.gustavo.managementsystem.util;

import lombok.Data;

@Data
public class ErrorMessageDTO {
    private String message;
    private String field;

    public ErrorMessageDTO(String message, String field){
        this.message = message;
        this.field = field;
    }

    public String getField(){
        return this.field;
    }

    // public void setField(String field){
    //     this.field = field;
    // }
}
