package com.twoqubed.bob.processor;

import com.twoqubed.bob.annotation.Built;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static javax.lang.model.SourceVersion.*;

@SupportedAnnotationTypes("com.twoqubed.bob.annotation.Built")
@SupportedSourceVersion(RELEASE_6)
public class CapturingProcessor extends AbstractProcessor {

    private Map<Class, Element> elements = new HashMap<Class, Element>();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Built.class)) {
            Class type = determineType((TypeElement) element);
            elements.put(type, element);
        }
        return true;
    }

    private Class<?> determineType(TypeElement element) {
        String qualifiedName = element.getQualifiedName().toString();
        try {
            return Class.forName(qualifiedName);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Could not load class for name " + qualifiedName);
        }
    }

    public Element getElement(Class type) {
        return elements.get(type);
    }
}
