package com.designedbyhenryp.urlshortener.model;

import lombok.Data;

import java.util.Date;

@Data
public class UrlLongRequest {

    private String longUrl;
    private Date expiresDate;
}
