package com.twoqubed.annotation.processor.processor;

import com.twoqubed.annotation.Built;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.Set;

import static javax.lang.model.SourceVersion.*;
import static javax.lang.model.element.ElementKind.*;
import static javax.tools.Diagnostic.Kind.*;

@SupportedAnnotationTypes("com.twoqubed.annotation.Built")
@SupportedSourceVersion(RELEASE_6)
public class BuiltProcessor extends AbstractProcessor {

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            doProcess(roundEnv);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void doProcess(RoundEnvironment environment) throws Exception {
        for (Element e : environment.getElementsAnnotatedWith(Built.class)) {
            BuilderMetaData metaData = handleAnnotatedClass(e);
            BuilderWriter writer = new VelocityBuilderWriter();
            writer.writeBeanInfo(metaData, processingEnv);
        }
    }

    private BuilderMetaData handleAnnotatedClass(Element e) {
        BuilderMetaData metaData = new BuilderMetaData();
        TypeElement classElement = (TypeElement) e;
        PackageElement packageElement = (PackageElement) classElement.getEnclosingElement();

        processingEnv.getMessager().printMessage(NOTE, "annotated class: " + classElement.getQualifiedName(), e);

        metaData.fqClassName = classElement.getQualifiedName().toString();
        metaData.className = classElement.getSimpleName().toString();
        metaData.packageName = packageElement.getQualifiedName().toString();

        addConstructorParameters(e, metaData);
        return metaData;
    }

    private void addConstructorParameters(Element e, BuilderMetaData metaData) {
        for (Element enclosed : e.getEnclosedElements()) {
            if (enclosed.getKind() == CONSTRUCTOR) {
                processingEnv.getMessager().printMessage(NOTE, "constructor", e);
                handleAnnotatedConstructor(metaData, enclosed);
            }
        }
    }

    private void handleAnnotatedConstructor(BuilderMetaData builderMetaData, Element e) {
        ExecutableElement constructorElement = (ExecutableElement) e;
        processingEnv.getMessager().printMessage(NOTE, "annotated field: " + constructorElement.getSimpleName(), e);
        for (VariableElement each : constructorElement.getParameters()) {
            builderMetaData.addConstructorParam(each);
        }
    }

}