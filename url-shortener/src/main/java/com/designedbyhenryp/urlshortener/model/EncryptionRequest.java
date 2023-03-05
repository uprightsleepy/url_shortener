package com.designedbyhenryp.urlshortener.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

@Validated
public class EncryptionRequest {

    @NotBlank(message = "URL must be provided.")
    @Pattern(regexp = "^(https?://)?([\\w\\-.]+)\\.([a-z]{2,6})([/\\w .-]*)*/?$", message = "URL must be valid.")
    private String originalUrl;

    public String getOriginalUrl() {
        return originalUrl;
    }
}
