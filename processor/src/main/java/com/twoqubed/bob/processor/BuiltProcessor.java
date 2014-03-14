package com.twoqubed.bob.processor;

import com.twoqubed.bob.annotation.Built;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.Writer;
import java.util.Set;

import static javax.lang.model.SourceVersion.*;

@SupportedAnnotationTypes("com.twoqubed.bob.annotation.Built")
@SupportedSourceVersion(RELEASE_6)
public class BuiltProcessor extends AbstractProcessor {

    private final BuilderWriter builderWriter;

    public BuiltProcessor() {
        this.builderWriter = new InternalBuilderWriter();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            doProcess(roundEnv);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void doProcess(RoundEnvironment environment) throws Exception {
        ElementProcessor processor = new ElementProcessor(processingEnv.getMessager());
        for (Element e : environment.getElementsAnnotatedWith(Built.class)) {
            BuilderMetadata metadata = processor.handleAnnotatedClass(e);
            writeMetadata(metadata);
        }
    }

    private void writeMetadata(BuilderMetadata metadata) throws Exception {
        Filer filer = processingEnv.getFiler();
        JavaFileObject fileObject = filer.createSourceFile(metadata.fqClassName + "Builder");
        Writer writer = fileObject.openWriter();
        builderWriter.writeBuilder(metadata, writer);
    }

}