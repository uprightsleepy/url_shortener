package com.designedbyhenryp.urlshortener.controller;

import com.designedbyhenryp.urlshortener.model.UrlLongRequest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UrlController {

    @PostMapping("shorten")
    public String convertToShortUrl(@RequestBody UrlLongRequest request) {
        return "Hello World";
    }

    @GetMapping(value = "url/{shortUrl}")
    @Cacheable(value = "urls", key = "#shortUrl", sync = true)
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}

