package com.designedbyhenryp.urlshortener.controller;

import com.designedbyhenryp.urlshortener.model.*;
import com.designedbyhenryp.urlshortener.service.RedirectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import static com.designedbyhenryp.urlshortener.constants.UrlShortenerConstants.CREATE_REDIRECT;
import static com.designedbyhenryp.urlshortener.constants.UrlShortenerConstants.HANDLE_REDIRECT;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;


@RestController
@RequestMapping("api/v1")
public class RedirectController {

    private final RedirectService redirectService;

    @Autowired
    public RedirectController(RedirectService redirectService) {
        this.redirectService = redirectService;
    }

    @GetMapping(HANDLE_REDIRECT)
    public ResponseEntity<?> handleRedirect(@PathVariable String alias) throws URISyntaxException {
        Redirect redirect = redirectService.getRedirect(alias);
        System.out.println("We're redirecting here!" + redirect);
        URI uri = new URI(redirect.getUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, MOVED_PERMANENTLY);
    }

    @PostMapping(CREATE_REDIRECT)
    public ResponseEntity<?> createRedirect(@Valid @RequestBody RedirectRequest redirectRequest) {
        return ResponseEntity.ok(redirectService.createRedirect(redirectRequest));
    }
}

