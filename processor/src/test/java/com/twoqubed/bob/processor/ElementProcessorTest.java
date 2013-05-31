package com.twoqubed.bob.processor;

import com.twoqubed.bob.sample.PrimitiveSample;

import java.io.IOException;

public class ElementProcessorTest extends AbstractElementProcessorTestCase {

    private BuilderMetadata builderMetadata;

    @Override
    protected void setUp() throws IOException, BuilderException {
        super.setUp();
        builderMetadata = elementProcessor.handleAnnotatedClass(element);
    }

    @Override
    protected Class<?> classToCompile() {
        return PrimitiveSample.class;
    }

    public void testExtractsPackageName() throws Exception {
        assertEquals("com.twoqubed.bob.sample", builderMetadata.packageName);
    }

    public void testExtractsSimpleClassName() throws Exception {
        assertEquals("PrimitiveSample", builderMetadata.className);
    }

}
