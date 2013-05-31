package com.twoqubed.bob.processor;

import com.twoqubed.bob.sample.PrimitiveSample;

public class ElementProcessorTest extends AbstractElementProcessorTestCase {

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
