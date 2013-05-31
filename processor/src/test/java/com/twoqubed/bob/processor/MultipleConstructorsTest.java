package com.twoqubed.bob.processor;

import com.twoqubed.bob.sample.MultipleConstructorsSample;

import java.io.IOException;

public class MultipleConstructorsTest extends AbstractElementProcessorTestCase {

    @Override
    protected void setUp() throws IOException, BuilderException {
        super.setUp();
    }

    @Override
    protected Class<?> classToCompile() {
        return MultipleConstructorsSample.class;
    }

    public void testFailsWhenMultipleConstructorsWithArgs() throws Exception {
        try {
            elementProcessor.handleAnnotatedClass(element);
            fail("Expected " + BuilderException.class);
        } catch (BuilderException e) {
            assertEquals("Ambiguous constructors", e.getMessage());
        }

    }
}
