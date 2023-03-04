package com.designedbyhenryp.urlshortener.model;

import lombok.Data;

import java.util.Date;

@Data
public class Url {

    Long id;
    String longUrl;
    Date createdDate;
    Date expirationDate;
}
