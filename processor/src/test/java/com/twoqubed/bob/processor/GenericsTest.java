package com.twoqubed.bob.processor;

import com.twoqubed.bob.sample.GenericsSample;

import java.io.IOException;

public class GenericsTest extends AbstractElementProcessorTestCase {

    private BuilderMetadata builderMetadata;

    @Override
    protected void setUp() throws IOException, BuilderException {
        super.setUp();
        builderMetadata = elementProcessor.handleAnnotatedClass(element);
    }

    @Override
    protected Class<?> classToCompile() {
        return GenericsSample.class;
    }

    public void testProcessesGenericTypes() throws Exception {
        ConstructorParam aString = builderMetadata.getParameters().get(0);

        assertEquals("java.util.List<java.lang.String>", aString.getType());
    }

    public void testProcessesWildcardTypes() throws Exception {
        ConstructorParam aString = builderMetadata.getParameters().get(1);

        assertEquals("java.util.List<?>", aString.getType());
    }

    public void testProcessesBoundedTypes() throws Exception {
        ConstructorParam aString = builderMetadata.getParameters().get(2);

        assertEquals("java.util.List<? extends java.lang.Number>", aString.getType());
    }
}
