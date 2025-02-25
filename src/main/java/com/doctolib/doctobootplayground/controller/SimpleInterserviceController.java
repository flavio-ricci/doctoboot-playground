package com.doctolib.doctobootplayground.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/__internal__/doctoboot-playground/v1/hello")
@Slf4j
public class SimpleInterserviceController {

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public String hello() {
        log.debug("inter-service endpoint invoked. Sending back hello world payload");
        return "Hello World";
    }
}
