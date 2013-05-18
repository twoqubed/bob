package com.twoqubed.annotation.processor.processor;

import com.twoqubed.annotation.sample.ObjectsSample;
import com.twoqubed.annotation.sample.ObjectsSampleBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.rules.ExpectedException.*;

public class ObjectsTest {
    @Rule public final ExpectedException thrown = none();

    @Test
    public void buildsString() {
        ObjectsSample baseTypesSample = ObjectsSampleBuilder.builder()
                .withAString("foo")
                .withADate(new Date())
                .build();

        assertEquals("foo", baseTypesSample.getString());
    }

    @Test
    public void buildsDate() {

    }

}
