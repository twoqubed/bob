package com.twoqubed.annotation.processor.processor;

import org.mockito.Mockito;
import org.seasar.aptina.unit.AptinaTestCase;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import java.io.File;
import java.io.IOException;

import static java.nio.charset.Charset.*;

public class ElementProcessorTest extends AptinaTestCase {

    private CapturingProcessor processor;
    private Messager messager;

    @Override
    protected void setUp() throws IOException {
        messager = Mockito.mock(Messager.class);

        setCharset(defaultCharset());

        File anchorPath = new File(getClass().getResource("/.anchor").getPath());
        addSourcePath(anchorPath
                .getParentFile()
                .getParentFile()
                .getParentFile()
                .getAbsolutePath() + "/src/test/java");

        processor = new CapturingProcessor();
        addProcessor(processor);
        addCompilationUnit(PrimitiveSample.class);
        compile();
    }

    public void testExtractsPackageName() throws Exception {
        Element element = processor.getElement(PrimitiveSample.class);
        ElementProcessor elementProcessor = new ElementProcessor(messager);
        BuilderMetadata builderMetadata = elementProcessor.handleAnnotatedClass(element);

        assertEquals(getClass().getPackage().getName(), builderMetadata.packageName);
    }

    public void testExtractsSimpleClassName() throws Exception {
        Element element = processor.getElement(PrimitiveSample.class);
        ElementProcessor elementProcessor = new ElementProcessor(messager);
        BuilderMetadata builderMetadata = elementProcessor.handleAnnotatedClass(element);

        assertEquals("PrimitiveSample", builderMetadata.className);
    }

    public void testHasNoImports() throws Exception {
        Element element = processor.getElement(PrimitiveSample.class);
        ElementProcessor elementProcessor = new ElementProcessor(messager);
        BuilderMetadata builderMetadata = elementProcessor.handleAnnotatedClass(element);

        assertTrue(builderMetadata.imports.isEmpty());
    }

    public void testProcessesAllConstructorArguments() throws Exception {
        Element element = processor.getElement(PrimitiveSample.class);
        ElementProcessor elementProcessor = new ElementProcessor(messager);
        BuilderMetadata builderMetadata = elementProcessor.handleAnnotatedClass(element);

        assertEquals(7, builderMetadata.parameters.size());
    }
}

