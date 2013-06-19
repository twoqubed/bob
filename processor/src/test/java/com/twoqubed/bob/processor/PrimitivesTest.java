package com.twoqubed.bob.processor;

import com.twoqubed.bob.sample.PrimitiveSample;

import java.io.IOException;

public class PrimitivesTest extends AbstractElementProcessorTestCase {

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

    public void testProcessesBooleanParam() throws Exception {
        ConstructorParam aBoolean = builderMetadata.getParameters().get(0);

        assertEquals("boolean", aBoolean.getType());
    }

    public void testProcessesByteParam() throws Exception {
        ConstructorParam aByte = builderMetadata.getParameters().get(1);

        assertEquals("byte", aByte.getType());
    }

    public void testProcessesCharParam() throws Exception {
        ConstructorParam aChar = builderMetadata.getParameters().get(2);

        assertEquals("char", aChar.getType());
    }

    public void testProcessesShortParam() throws Exception {
        ConstructorParam aShort = builderMetadata.getParameters().get(3);

        assertEquals("short", aShort.getType());
    }

    public void testProcessesIntParam() throws Exception {
        ConstructorParam anInt = builderMetadata.getParameters().get(4);

        assertEquals("int", anInt.getType());
    }

    public void testProcessesLongParam() throws Exception {
        ConstructorParam aLong = builderMetadata.getParameters().get(5);

        assertEquals("long", aLong.getType());
    }

    public void testProcessesFloatParam() throws Exception {
        ConstructorParam aFloat = builderMetadata.getParameters().get(6);

        assertEquals("float", aFloat.getType());
    }

    public void testProcessesDoubleParam() throws Exception {
        ConstructorParam aDouble = builderMetadata.getParameters().get(7);

        assertEquals("double", aDouble.getType());
    }
}
