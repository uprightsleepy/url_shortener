package com.designedbyhenryp.urlshortener.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RedirectRequest {

    @NotNull
    private String alias;

    @NotBlank(message = "URL must be provided.")
    @Pattern(regexp = "(https?://(www\\.)?[-a-zA-Z\\d@:%._+~#=]{1,256}\\.[a-zA-Z\\d()]{1,6}\\b([-a-zA-Z\\d()@:%_+.~#?&/=]*))", message = "URL must be valid.")
    private String url;
}
