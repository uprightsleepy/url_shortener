package com.designedbyhenryp.urlshortener.service;

import com.designedbyhenryp.urlshortener.exception.BadRequestException;
import com.designedbyhenryp.urlshortener.exception.NotFoundException;
import com.designedbyhenryp.urlshortener.model.Redirect;
import com.designedbyhenryp.urlshortener.model.RedirectRequest;
import com.designedbyhenryp.urlshortener.repository.RedirectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class RedirectService {

    private final RedirectRepository redirectRepository;

    @Autowired
    public RedirectService(RedirectRepository redirectRepository) {
        this.redirectRepository = redirectRepository;
    }

    public Optional<Redirect> createRedirect(RedirectRequest redirectRequest) {

        if (redirectRepository.existsByAlias(redirectRequest.getAlias())) {
            throw new BadRequestException("Alias already exists.");
        }

        System.out.println("Redirect Request: " + redirectRequest);
        Redirect redirect = new Redirect(redirectRequest.getAlias(), redirectRequest.getUrl());

        Redirect postSaveRedirect = redirectRepository.save(redirect);
        System.out.println("Redirect: " + postSaveRedirect);

        return Optional.of(postSaveRedirect);
    }

    public Redirect getRedirect(String alias) {
        return redirectRepository.findByAlias(alias)
                .orElseThrow(() -> new NotFoundException("That alias does not exist! Try making it \uD83D\uDE03."));
    }
}
