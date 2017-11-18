package com.trebol.travelstats;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Holi
{
    public void testingJava8()
    {

        Arrays.asList(1, 2, 3, 4).stream().filter(integer -> integer > 2).collect(Collectors.toList());
    }

}
