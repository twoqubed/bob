package com.twoqubed.bob.processor;

public class ElementProcessorTest extends AbstractElementProcessorTestCase {

    @Override
    protected Class<PrimitiveSample> classToCompile() {
        return PrimitiveSample.class;
    }

    public void testExtractsPackageName() throws Exception {
        assertEquals(getClass().getPackage().getName(), builderMetadata.packageName);
    }

    public void testExtractsSimpleClassName() throws Exception {
        assertEquals("PrimitiveSample", builderMetadata.className);
    }

}
