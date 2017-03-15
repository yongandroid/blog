package com.xielaoban.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by SeanWu on 2017/2/13.
 */
@RestController
@RequestMapping("/hello")
public class HelloWorldController {


    @RequestMapping("/world")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello" + name;
    }

}
