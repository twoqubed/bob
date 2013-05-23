package com.twoqubed.annotation.processor.processor;

import com.twoqubed.annotation.Built;

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

@SupportedAnnotationTypes("com.twoqubed.annotation.Built")
@SupportedSourceVersion(RELEASE_6)
public class BuiltProcessor extends AbstractProcessor {

    private final BuilderWriter builderWriter;

    public BuiltProcessor() {
        this(new VelocityBuilderWriter());
    }

    BuiltProcessor(BuilderWriter builderWriter) {
        this.builderWriter = builderWriter;
    }

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
            BuilderMetaData metaData = processor.handleAnnotatedClass(e);

            Filer filer = processingEnv.getFiler();
            JavaFileObject fileObject = filer.createSourceFile(metaData.fqClassName + "Builder");
            Writer writer = fileObject.openWriter();
            builderWriter.writeBeanInfo(metaData, writer);
        }
    }

}