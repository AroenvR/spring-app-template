package com.template.springapptemplate.controller;

import com.template.springapptemplate.service.FooService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {
    private final Logger logger = LoggerFactory.getLogger(FooController.class);

    private final FooService fooService;

    @Autowired
    public FooController(FooService fooService) {
        this.fooService = fooService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/foo")
    public ResponseEntity<?> foo(@RequestParam("id") long id) {
        logger.info("foo was called for id: {}", id);

        try {
            return ResponseEntity.status(HttpStatus.OK).body(fooService.findById(id));

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

    }
}
