package com.twoqubed.annotation.processor.processor;

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

    BuilderMetaData handleAnnotatedClass(Element e) {
        BuilderMetaData metaData = new BuilderMetaData();
        TypeElement classElement = (TypeElement) e;
        PackageElement packageElement = (PackageElement) classElement.getEnclosingElement();

        messager.printMessage(NOTE, "annotated class: " + classElement.getQualifiedName(), e);

        metaData.fqClassName = classElement.getQualifiedName().toString();
        metaData.className = classElement.getSimpleName().toString();
        metaData.packageName = packageElement.getQualifiedName().toString();

        addConstructorParameters(e, metaData);
        return metaData;
    }

    private void addConstructorParameters(Element e, BuilderMetaData metaData) {
        for (Element enclosed : e.getEnclosedElements()) {
            if (enclosed.getKind() == CONSTRUCTOR) {
                messager.printMessage(NOTE, "constructor", e);
                handleAnnotatedConstructor(metaData, enclosed);
            }
        }
    }
    
    private void handleAnnotatedConstructor(BuilderMetaData builderMetaData, Element e) {
        ExecutableElement constructorElement = (ExecutableElement) e;
        messager.printMessage(NOTE, "annotated field: " + constructorElement.getSimpleName(), e);
        for (VariableElement each : constructorElement.getParameters()) {
            builderMetaData.addConstructorParam(each);
        }
    }
}
