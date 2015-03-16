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
        BuilderMetadata metadata = new BuilderMetadata();
        metadata.fqClassName = "com.twoqubed.bob.processor.generated.Foo";
        metadata.className = "Foo";
        metadata.packageName = "com.twoqubed.bob.processor.generated";
        metadata.addConstructorParam(new ConstructorParam("bar", "java.lang.String"));
        metadata.addConstructorParam(new ConstructorParam("baz", "java.lang.String"));
        metadata.addConstructorParam(new ConstructorParam("qux", "java.lang.Boolean"));
        metadata.addConstructorParam(new ConstructorParam("norf", "boolean"));
        metadata.addConstructorParam(new ConstructorParam("list", "java.util.List<java.lang.String>"));
        metadata.addConstructorParam(new ConstructorParam("set", "java.util.Set<java.lang.String>"));
        metadata.addConstructorParam(new ConstructorParam("map", "java.util.Map<java.lang.String, java.lang.Integer>"));
        metadata.addConstructorParam(new ConstructorParam("intArray", "int[]"));
        metadata.addConstructorParam(new ConstructorParam("objectArray", "java.lang.Object[]"));

        InternalBuilderWriter writer = new InternalBuilderWriter();
        StringWriter actual = new StringWriter();
        writer.writeBuilder(metadata, actual);

        String expected = loadExpected();
        assertEquals(expected, actual.getBuffer().toString());
    }

    private String loadExpected() throws IOException {
        InputStream in = getClass().getResourceAsStream("/com/twoqubed/bob/processor/generated/FooBuilder.java");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder expected = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            expected.append(line).append("\n");
        }
        return expected.toString();
    }

}
