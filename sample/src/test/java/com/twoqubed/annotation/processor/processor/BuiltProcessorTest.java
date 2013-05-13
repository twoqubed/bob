package com.twoqubed.annotation.processor.processor;

import com.twoqubed.annotation.sample.Sample;
import com.twoqubed.annotation.sample.SampleBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BuiltProcessorTest {

    private Sample sample;

    @Before
    public void setUp() {
        sample = SampleBuilder.builder()
                .withABoolean(true)
                .withAChar('a')
                .withAShort((short) 1)
                .withAnInt(2)
                .withALong(3)
                .withAFloat(1.0F)
                .withADouble(2.0)
                .withAString("foo")
                .build();
    }

    @Test
    public void buildsBoolean() {
        assertEquals(true, sample.isBoolean());
    }

    @Test
    public void buildsChar() {
        assertEquals('a', sample.getChar());
    }

    @Test
    public void buildShort() {
        assertEquals(1, sample.getShort());
    }

    @Test
    public void buildsInt() {
        assertEquals(2, sample.getInt());
    }

    @Test
    public void buildsLong() {
        assertEquals(3, sample.getLong());
    }

    @Test
    public void buildsFloat() {
        assertEquals(1.0, sample.getFloat(), 0.0);
    }

    @Test
    public void buildsDouble() {
        assertEquals(2.0, sample.getDouble(), 0.0);
    }

    @Test
    public void buildsString() {
        assertEquals("foo", sample.getString());
    }
}
