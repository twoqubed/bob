package com.twoqubed.bob.processor;

import com.twoqubed.bob.sample.PrimitiveSample;

public class PrimitivesTest extends AbstractElementProcessorTestCase {

    @Override
    protected Class<?> classToCompile() {
        return PrimitiveSample.class;
    }

    public void testProcessesBooleanParam() throws Exception {
        ConstructorParam aBoolean = builderMetadata.parameters.get(0);

        assertEquals("boolean", aBoolean.getType());
    }

    public void testProcessesByteParam() throws Exception {
        ConstructorParam aByte = builderMetadata.parameters.get(1);

        assertEquals("byte", aByte.getType());
    }

    public void testProcessesCharParam() throws Exception {
        ConstructorParam aChar = builderMetadata.parameters.get(2);

        assertEquals("char", aChar.getType());
    }

    public void testProcessesShortParam() throws Exception {
        ConstructorParam aShort = builderMetadata.parameters.get(3);

        assertEquals("short", aShort.getType());
    }

    public void testProcessesIntParam() throws Exception {
        ConstructorParam anInt = builderMetadata.parameters.get(4);

        assertEquals("int", anInt.getType());
    }

    public void testProcessesLongParam() throws Exception {
        ConstructorParam aLong = builderMetadata.parameters.get(5);

        assertEquals("long", aLong.getType());
    }

    public void testProcessesFloatParam() throws Exception {
        ConstructorParam aFloat = builderMetadata.parameters.get(6);

        assertEquals("float", aFloat.getType());
    }

    public void testProcessesDoubleParam() throws Exception {
        ConstructorParam aDouble = builderMetadata.parameters.get(7);

        assertEquals("double", aDouble.getType());
    }
}
