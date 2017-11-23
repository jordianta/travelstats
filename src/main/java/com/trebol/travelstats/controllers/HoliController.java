package com.trebol.travelstats.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
public class HoliController {

    @RequestMapping("/holi2")
    public String sayHoli() {
        Arrays.asList(1, 2, 3, 4).stream().filter(integer -> integer > 2).collect(Collectors.toList());
        return "Holi 2!!!!";
    }

}
