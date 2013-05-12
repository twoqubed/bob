package com.twoqubed.annotation.processor;

import com.twoqubed.annotation.BeanInfo;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import static javax.lang.model.element.ElementKind.*;
import static javax.tools.Diagnostic.Kind.*;

@SupportedAnnotationTypes("com.twoqubed.annotation.BeanInfo")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class BeanInfoProcessor extends AbstractProcessor {

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            doProcess(roundEnv);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private void doProcess(RoundEnvironment environment) throws Exception {
        MetaBeanInfo metaBeanInfo = extractMetaBeanInfo(environment);
        if (metaBeanInfo.fqClassName != null) {
            writeBeanInfo(metaBeanInfo);
        }
    }

    private MetaBeanInfo extractMetaBeanInfo(RoundEnvironment environment) {
        MetaBeanInfo metaBeanInfo = new MetaBeanInfo();
        for (Element e : environment.getElementsAnnotatedWith(BeanInfo.class)) {
            if (e.getKind() == CLASS) {
                handleAnnotatedClass(metaBeanInfo, e);
            } else if (e.getKind() == FIELD) {
                handleAnnotatedField(metaBeanInfo, e);
            } else if (e.getKind() == METHOD) {
                handleAnnotatedMethod(metaBeanInfo, e);
            }
        }
        return metaBeanInfo;
    }

    private void handleAnnotatedClass(MetaBeanInfo metaBeanInfo, Element e) {
        TypeElement classElement = (TypeElement) e;
        PackageElement packageElement = (PackageElement) classElement.getEnclosingElement();

        processingEnv.getMessager().printMessage(NOTE, "annotated class: " + classElement.getQualifiedName(), e);

        metaBeanInfo.fqClassName = classElement.getQualifiedName().toString();
        metaBeanInfo.className = classElement.getSimpleName().toString();
        metaBeanInfo.packageName = packageElement.getQualifiedName().toString();
    }

    private void handleAnnotatedField(MetaBeanInfo metaBeanInfo, Element e) {
        VariableElement varElement = (VariableElement) e;
        processingEnv.getMessager().printMessage(NOTE, "annotated field: " + varElement.getSimpleName(), e);
        metaBeanInfo.fields.put(varElement.getSimpleName().toString(), varElement);
    }

    private void handleAnnotatedMethod(MetaBeanInfo metaBeanInfo, Element e) {
        ExecutableElement exeElement = (ExecutableElement) e;
        processingEnv.getMessager().printMessage(NOTE, "annotated method: " + exeElement.getSimpleName(), e);
        metaBeanInfo.methods.put(exeElement.getSimpleName().toString(), exeElement);
    }

    private void writeBeanInfo(MetaBeanInfo metaBeanInfo) throws Exception {
        VelocityEngine engine = initializeVelocityEngine();
        VelocityContext context = initializeVelocityContext(metaBeanInfo);
        Template template = engine.getTemplate("beaninfo.vm");
        writeFile(metaBeanInfo, context, template);
    }

    private VelocityEngine initializeVelocityEngine() throws Exception {
        URL url = getClass().getClassLoader().getResource("velocity.properties");
        Properties props = new Properties();
        props.load(url.openStream());
        VelocityEngine engine = new VelocityEngine(props);
        engine.init();
        return engine;
    }

    private VelocityContext initializeVelocityContext(MetaBeanInfo metaBeanInfo) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("className", metaBeanInfo.className);
        velocityContext.put("packageName", metaBeanInfo.packageName);
        velocityContext.put("fields", metaBeanInfo.fields);
        velocityContext.put("methods", metaBeanInfo.methods);
        return velocityContext;
    }

    private void writeFile(MetaBeanInfo metaBeanInfo, VelocityContext context, Template template) throws IOException {
        JavaFileObject fileObject = processingEnv.getFiler().createSourceFile(metaBeanInfo.fqClassName + "BeanInfo");
        processingEnv.getMessager().printMessage(NOTE, "creating source file: " + fileObject.toUri());
        Writer writer = fileObject.openWriter();
        processingEnv.getMessager().printMessage(NOTE, "applying velocity template: " + template.getName());
        template.merge(context, writer);
        writer.close();
    }

    private static class MetaBeanInfo {
        private String fqClassName;
        private String className;
        private String packageName;

        private final Map<String, VariableElement> fields = new HashMap<String, VariableElement>();
        private final Map<String, ExecutableElement> methods = new HashMap<String, ExecutableElement>();
    }
}