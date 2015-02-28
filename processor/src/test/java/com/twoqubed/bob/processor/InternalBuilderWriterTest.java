package com.twoqubed.bob.processor;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class InternalBuilderWriterTest {

    @Test
    public void generatesSourceFromMetadata() throws Exception {
        assertExpectedSourceIsGenerated("FooWithCopy", true);
    }

    @Test
    public void omitsCopyConstructor() throws Exception {
        assertExpectedSourceIsGenerated("FooNoCopy", false);
    }

    private void assertExpectedSourceIsGenerated(String className, boolean generateCopyMethod) throws Exception {
        BuilderMetadata metadata = new BuilderMetadata(generateCopyMethod);
        metadata.fqClassName = "com.twoqubed.bob.processor.generated." + className;
        metadata.className = className;
        metadata.packageName = "com.twoqubed.bob.processor.generated";
        metadata.addConstructorParam(new ConstructorParam("bar", "java.lang.String"));
        metadata.addConstructorParam(new ConstructorParam("baz", "java.lang.String"));
        metadata.addConstructorParam(new ConstructorParam("qux", "java.lang.Boolean"));
        metadata.addConstructorParam(new ConstructorParam("norf", "boolean"));

        InternalBuilderWriter builderWriter = new InternalBuilderWriter();
        StringWriter stringWriter = new StringWriter();
        builderWriter.writeBuilder(metadata, stringWriter);

        String expected = loadExpected(className);
        String actual = stringWriter.getBuffer().toString();
        assertEquals(generatedFailureMessage(expected, actual), expected, actual);
    }

    private String generatedFailureMessage(String expected, String actual) {
        return "Expected:\n===========\n" + expected + "\n===========\n\nBut was actually:\n===========\n" +
                actual + "\n===========";
    }

    private String loadExpected(String className) throws IOException {
        String sourceFile = "/com/twoqubed/bob/processor/generated/" + className + "Builder.java";
        InputStream in = getClass().getResourceAsStream(sourceFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder expected = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            expected.append(line).append("\n");
        }
        return expected.toString();
    }

}
