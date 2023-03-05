package com.designedbyhenryp.urlshortener.controller;

import com.designedbyhenryp.urlshortener.model.EncryptionRequest;
import com.designedbyhenryp.urlshortener.service.EncryptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UrlController {

    @Autowired
    EncryptionService encryptionService;

    @PostMapping("test")
    public String convertToShortUrl(@RequestBody @Valid EncryptionRequest request) {

        String secretKey = encryptionService.generateString();
        String encryptedString = EncryptionService.encrypt(request.getOriginalUrl(), secretKey) ;
        String decryptedString = EncryptionService.decrypt(encryptedString, secretKey) ;

        System.out.println(secretKey);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
        return "Hello World";
    }

    @GetMapping(value = "url/{shortUrl}")
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}

