package com.twoqubed.bob.processor;

import com.twoqubed.bob.sample.ObjectsSample;
import com.twoqubed.bob.sample.ObjectsSampleBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.rules.ExpectedException.*;

public class ObjectsTest {
    @Rule public final ExpectedException thrown = none();

    @Test
    public void buildsString() {
        Date now = new Date();
        List<String> strings = new ArrayList<String>();
        strings.add("bar");

        ObjectsSample objectsSample = ObjectsSampleBuilder.builder()
                .withAString("foo")
                .withADate(now)
                .withAListOfStrings(strings)
                .build();

        assertEquals("foo", objectsSample.getString());
        assertEquals(now, objectsSample.getDate());
        assertEquals(strings, objectsSample.getListOfStrings());
    }

    @Test
    public void buildsWithoutListOfStrings() {
        Date now = new Date();

        ObjectsSample objectsSample = ObjectsSampleBuilder.builder()
                .withAString("foo")
                .withADate(now)
                .build();

        assertEquals("foo", objectsSample.getAString());
        assertEquals(now, objectsSample.getADate());
        assertEquals(0, objectsSample.getAListOfStrings().size());
    }
}
