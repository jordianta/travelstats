package com.trebol.travelstats.controllers;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Holicontroller
{
    @RequestMapping("/holi")
    public String sayHoli()
    {

        Arrays.asList(1, 2, 3, 4).stream().filter(integer -> integer > 2).collect(Collectors.toList());
        return "Holi!!!!";
    }

}
