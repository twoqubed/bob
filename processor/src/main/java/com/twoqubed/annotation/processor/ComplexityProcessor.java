package com.twoqubed.annotation.processor;

import com.twoqubed.annotation.Complexity;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

import static javax.lang.model.SourceVersion.*;
import static javax.tools.Diagnostic.Kind.*;

@SupportedAnnotationTypes("com.twoqubed.annotation.Complexity")
@SupportedSourceVersion(RELEASE_6)
public class ComplexityProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element elem : roundEnv.getElementsAnnotatedWith(Complexity.class)) {
            Complexity complexity = elem.getAnnotation(Complexity.class);
            String message = "annotation found in " + elem.getSimpleName() + " with complexity " + complexity.value();
            processingEnv.getMessager().printMessage(NOTE, message);
        }
        return true;
    }
}