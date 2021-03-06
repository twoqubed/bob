package com.twoqubed.bob.processor;

import org.mockito.Mockito;
import org.seasar.aptina.unit.AptinaTestCase;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import java.io.File;
import java.io.IOException;

import static java.nio.charset.Charset.*;

public abstract class AbstractElementProcessorTestCase extends AptinaTestCase {

    protected Messager messager;
    protected ElementProcessor elementProcessor;
    protected Element element;

    @Override
    protected void setUp() throws IOException, BuilderException {
        messager = Mockito.mock(Messager.class);

        setCharset(defaultCharset());

        File anchorPath = new File(getClass().getResource("/.anchor").getPath());
        addSourcePath(anchorPath
                .getParentFile()
                .getParentFile()
                .getParentFile()
                .getAbsolutePath() + "/src/test/java");

        CapturingProcessor processor = new CapturingProcessor();
        addProcessor(processor);

        addCompilationUnit(classToCompile());
        compile();

        element = processor.getElement(classToCompile());
        elementProcessor = new ElementProcessor(messager);
    }

    protected abstract Class<?> classToCompile();

}
