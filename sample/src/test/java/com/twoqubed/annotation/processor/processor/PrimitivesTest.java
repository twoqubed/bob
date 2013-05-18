package com.twoqubed.annotation.processor.processor;

import com.twoqubed.annotation.sample.PrimitiveSample;
import com.twoqubed.annotation.sample.PrimitiveSampleBuilder;
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
        assertEquals(true, primitiveSample.isBoolean());
    }

    @Test
    public void buildsChar() {
        assertEquals('a', primitiveSample.getChar());
    }

    @Test
    public void buildShort() {
        assertEquals(1, primitiveSample.getShort());
    }

    @Test
    public void buildsInt() {
        assertEquals(2, primitiveSample.getInt());
    }

    @Test
    public void buildsLong() {
        assertEquals(3, primitiveSample.getLong());
    }

    @Test
    public void buildsFloat() {
        assertEquals(1.0, primitiveSample.getFloat(), 0.0);
    }

    @Test
    public void buildsDouble() {
        assertEquals(2.0, primitiveSample.getDouble(), 0.0);
    }

}
