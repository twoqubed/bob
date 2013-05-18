package com.twoqubed.annotation.processor.processor;

import com.twoqubed.annotation.sample.BaseTypesSample;
import com.twoqubed.annotation.sample.BaseTypesSampleBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.junit.rules.ExpectedException.*;

public class BaseTypesTest {
    @Rule public final ExpectedException thrown = none();

    @Test
    public void buildsString() {
        BaseTypesSample baseTypesSample = BaseTypesSampleBuilder.builder()
                .withAString("foo")
                .build();

        assertEquals("foo", baseTypesSample.getString());
    }

}
