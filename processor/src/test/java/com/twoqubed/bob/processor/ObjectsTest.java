package com.twoqubed.bob.processor;

import com.twoqubed.bob.sample.ObjectsSample;

public class ObjectsTest extends AbstractElementProcessorTestCase {

    @Override
    protected Class<?> classToCompile() {
        return ObjectsSample.class;
    }

    public void testProcessesStringParam() throws Exception {
        ConstructorParam aString = builderMetadata.parameters.get(0);

        assertEquals("String", aString.getType());
    }

    public void testProcessesDateParam() throws Exception {
        ConstructorParam aDate = builderMetadata.parameters.get(1);

        assertEquals("java.util.Date", aDate.getType());
    }
}
