package com.twoqubed.bob.processor;

import com.twoqubed.bob.sample.CopySample;
import com.twoqubed.bob.sample.CopySampleBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.rules.ExpectedException.*;

public class CopyTest {
    @Rule public final ExpectedException thrown = none();

    @Test
    public void buildsString() {
        CopySample sample = CopySampleBuilder.builder()
                .withString("foo")
                .build();

//        CopySampleBuilder.
    }

}
