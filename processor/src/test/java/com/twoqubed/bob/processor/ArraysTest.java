package com.twoqubed.bob.processor;

import com.twoqubed.bob.sample.ArraysSample;

import java.io.IOException;

public class ArraysTest extends AbstractElementProcessorTestCase {

    private BuilderMetadata builderMetadata;

    @Override
    protected void setUp() throws IOException, BuilderException {
        super.setUp();
        builderMetadata = elementProcessor.handleAnnotatedClass(element);
    }

    @Override
    protected Class<?> classToCompile() {
        return ArraysSample.class;
    }

    public void testProcessesBooleanArrayParam() throws Exception {
        ConstructorParam aBoolean = builderMetadata.getParameters().get(0);

        assertEquals("boolean[]", aBoolean.getType());
    }

    public void testProcessesByteArrayParam() throws Exception {
        ConstructorParam aByte = builderMetadata.getParameters().get(1);

        assertEquals("byte[]", aByte.getType());
    }

    public void testProcessesCharArrayParam() throws Exception {
        ConstructorParam aChar = builderMetadata.getParameters().get(2);

        assertEquals("char[]", aChar.getType());
    }


    public void testProcessesShortArrayParam() throws Exception {
        ConstructorParam aShort = builderMetadata.getParameters().get(3);

        assertEquals("short[]", aShort.getType());
    }

    public void testProcessesIntArrayParam() throws Exception {
        ConstructorParam anInt = builderMetadata.getParameters().get(4);

        assertEquals("int[]", anInt.getType());
    }

    public void testProcessesLongArrayParam() throws Exception {
        ConstructorParam aLong = builderMetadata.getParameters().get(5);

        assertEquals("long[]", aLong.getType());
    }

    public void testProcessesFloatArrayParam() throws Exception {
        ConstructorParam aFloat = builderMetadata.getParameters().get(6);

        assertEquals("float[]", aFloat.getType());
    }

    public void testProcessesDoubleArrayParam() throws Exception {
        ConstructorParam aDouble = builderMetadata.getParameters().get(7);

        assertEquals("double[]", aDouble.getType());
    }

    public void testProcessesStringArrayParam() throws Exception {
        ConstructorParam aString = builderMetadata.getParameters().get(8);

        assertEquals("java.lang.String[]", aString.getType());
    }

    public void testProcessesDateArrayParam() throws Exception {
        ConstructorParam aDate = builderMetadata.getParameters().get(9);

        assertEquals("java.util.Date[]", aDate.getType());
    }

    public void testProcessesCustomDateArrayParam() throws Exception {
        ConstructorParam aCustomDate = builderMetadata.getParameters().get(10);

        assertEquals("com.twoqubed.bob.sample.Date[]", aCustomDate.getType());
    }

    public void testProcessesTwoDimensionalArray() throws Exception {
        ConstructorParam aCustomDate = builderMetadata.getParameters().get(11);

        assertEquals("boolean[][]", aCustomDate.getType());
    }
}
