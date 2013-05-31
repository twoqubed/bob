package com.twoqubed.bob.processor;

import com.twoqubed.bob.sample.NoArgConstructorSample;

import java.io.IOException;

public class NoArgConstructorTest extends AbstractElementProcessorTestCase {

    private BuilderMetadata builderMetadata;

    @Override
    protected void setUp() throws IOException, BuilderException {
        super.setUp();
        builderMetadata = elementProcessor.handleAnnotatedClass(element);
    }

    @Override
    protected Class<?> classToCompile() {
        return NoArgConstructorSample.class;
    }

    public void testPrefersConstructorWithArgs() throws Exception {
        assertEquals(1, builderMetadata.parameters.size());
    }
}
