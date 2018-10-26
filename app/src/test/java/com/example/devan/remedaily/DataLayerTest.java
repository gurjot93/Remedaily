package com.example.devan.remedaily;


import com.example.devan.remedaily.datalayer.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataLayerTest
{
    @Test
    public void testBusinessLayerOutput(){
        assertEquals(new example().DemoBusinessLayerFunction(),"This is a demo data layer function");
    }
}
