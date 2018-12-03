package com.example.devan.remedaily;

import org.junit.Test;
import com.example.devan.remedaily.businesslayer.*;
import static org.junit.Assert.assertEquals;

public class BusinessLayerTest {
    @Test
    public void testBusinessLayerOutput(){
        assertEquals(new example().DemoBusinessLayerFunction(),"This is a demo business layer function");
    }
}
