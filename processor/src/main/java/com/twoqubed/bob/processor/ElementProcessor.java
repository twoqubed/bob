package com.twoqubed.bob.processor;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import static javax.lang.model.element.ElementKind.*;
import static javax.tools.Diagnostic.Kind.*;

class ElementProcessor {

    private final Messager messager;

    ElementProcessor(Messager messager) {
        this.messager = messager;
    }

    BuilderMetadata handleAnnotatedClass(Element e) {
        BuilderMetadata metadata = new BuilderMetadata();
        TypeElement classElement = (TypeElement) e;
        PackageElement packageElement = (PackageElement) classElement.getEnclosingElement();

        messager.printMessage(NOTE, "annotated class: " + classElement.getQualifiedName(), e);

        metadata.fqClassName = classElement.getQualifiedName().toString();
        metadata.className = classElement.getSimpleName().toString();
        metadata.packageName = packageElement.getQualifiedName().toString();

        addConstructorParameters(e, metadata);
        return metadata;
    }

    private void addConstructorParameters(Element e, BuilderMetadata metadata) {
        for (Element enclosed : e.getEnclosedElements()) {
            if (enclosed.getKind() == CONSTRUCTOR) {
                messager.printMessage(NOTE, "constructor", e);
                handleAnnotatedConstructor(metadata, enclosed);
            }
        }
    }
    
    private void handleAnnotatedConstructor(BuilderMetadata builderMetadata, Element e) {
        ExecutableElement constructorElement = (ExecutableElement) e;
        messager.printMessage(NOTE, "annotated field: " + constructorElement.getSimpleName(), e);
        for (VariableElement each : constructorElement.getParameters()) {
            builderMetadata.addConstructorParam(each);
        }
    }
}
