package com.twoqubed.bob.processor;

import com.twoqubed.bob.sample.PrimitiveSample;
import com.twoqubed.bob.sample.PrimitiveSampleBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrimitivesTest {

    private PrimitiveSample primitiveSample;

    @Before
    public void setUp() {
        primitiveSample = PrimitiveSampleBuilder.builder()
                .withABoolean(true)
                .withAChar('a')
                .withAShort((short) 1)
                .withAnInt(2)
                .withALong(3)
                .withAFloat(1.0F)
                .withADouble(2.0)
                .build();
    }

    @Test
    public void buildsBoolean() {
        assertEquals(true, primitiveSample.isABoolean());
    }

    @Test
    public void buildsChar() {
        assertEquals('a', primitiveSample.getAChar());
    }

    @Test
    public void buildShort() {
        assertEquals(1, primitiveSample.getAShort());
    }

    @Test
    public void buildsInt() {
        assertEquals(2, primitiveSample.getAnInt());
    }

    @Test
    public void buildsLong() {
        assertEquals(3, primitiveSample.getALong());
    }

    @Test
    public void buildsFloat() {
        assertEquals(1.0, primitiveSample.getAFloat(), 0.0);
    }

    @Test
    public void buildsDouble() {
        assertEquals(2.0, primitiveSample.getADouble(), 0.0);
    }

}
