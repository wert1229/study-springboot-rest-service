package com.example.restful.controller;

import com.example.restful.domain.HelloWorldBean;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class HelloWorldController {

    private final MessageSource messageSource;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/hello-bean")
    public HelloWorldBean helloBean() {
        return new HelloWorldBean("hello world");
    }

    @GetMapping("/hello-inter")
    public String helloInter(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale);
    }
}
