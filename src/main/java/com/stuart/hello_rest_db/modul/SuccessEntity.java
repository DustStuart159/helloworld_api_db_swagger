package com.stuart.hello_rest_db.modul;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
public class SuccessEntity {
    private String timeStamp;
    private String message;

    public SuccessEntity(String timeStamp, String message) {
        this.timeStamp = timeStamp;
        this.message = message;
    }
}
