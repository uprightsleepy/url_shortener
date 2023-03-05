package com.designedbyhenryp.urlshortener.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static com.designedbyhenryp.urlshortener.constants.UrlShortenerConstants.WEBSITE_VALIDATION;

@Getter
@Setter
@AllArgsConstructor
public class RedirectRequest {

    @NotNull
    private String alias;

    @NotBlank(message = "URL must be provided.")
    @Pattern(regexp = WEBSITE_VALIDATION, message = "URL must be valid.")
    private String url;
}
