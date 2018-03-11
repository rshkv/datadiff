package com.rshkv.datadiff;

import org.junit.Test;
import static org.junit.Assert.*;

public class MyersTest {
    @Test
    public void canConstructMyers() throws Exception {
        assertEquals(5, new Myers("ABCABBA", "CBABAC").shortestEdit());
        assertEquals(5, new Myers("CBABAC", "ABCABBA").shortestEdit());
        assertEquals(6, new Myers("ABC", "XZY").shortestEdit());
        assertEquals(3, new Myers("", "XZY").shortestEdit());
        assertEquals(3, new Myers("ABC", "").shortestEdit());
    }
}