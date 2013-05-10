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
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.JavaFileObject;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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

    private void doProcess(RoundEnvironment roundEnv) throws Exception {
        String fqClassName = null;
        String className = null;
        String packageName = null;
        Map<String, VariableElement> fields = new HashMap<String, VariableElement>();
        Map<String, ExecutableElement> methods = new HashMap<String, ExecutableElement>();

        for (Element e : roundEnv.getElementsAnnotatedWith(BeanInfo.class)) {

            if (e.getKind() == ElementKind.CLASS) {

                TypeElement classElement = (TypeElement) e;
                PackageElement packageElement = (PackageElement) classElement.getEnclosingElement();

                processingEnv.getMessager().printMessage(NOTE, "annotated class: " + classElement.getQualifiedName(), e);

                fqClassName = classElement.getQualifiedName().toString();
                className = classElement.getSimpleName().toString();
                packageName = packageElement.getQualifiedName().toString();

            } else if (e.getKind() == ElementKind.FIELD) {

                VariableElement varElement = (VariableElement) e;

                processingEnv.getMessager().printMessage(
                        NOTE,
                        "annotated field: " + varElement.getSimpleName(), e);

                fields.put(varElement.getSimpleName().toString(), varElement);

            } else if (e.getKind() == ElementKind.METHOD) {

                ExecutableElement exeElement = (ExecutableElement) e;

                processingEnv.getMessager().printMessage(NOTE, "annotated method: " + exeElement.getSimpleName(), e);

                methods.put(exeElement.getSimpleName().toString(), exeElement);
            }
        }

        if (fqClassName != null) {

            Properties props = new Properties();
            URL url = this.getClass().getClassLoader().getResource("velocity.properties");
            props.load(url.openStream());

            VelocityEngine ve = new VelocityEngine(props);
            ve.init();

            VelocityContext vc = new VelocityContext();

            vc.put("className", className);
            vc.put("packageName", packageName);
            vc.put("fields", fields);
            vc.put("methods", methods);

            Template vt = ve.getTemplate("beaninfo.vm");

            JavaFileObject jfo = processingEnv.getFiler().createSourceFile(fqClassName + "BeanInfo");

            processingEnv.getMessager().printMessage(NOTE, "creating source file: " + jfo.toUri());

            Writer writer = jfo.openWriter();

            processingEnv.getMessager().printMessage(NOTE, "applying velocity template: " + vt.getName());

            vt.merge(vc, writer);

            writer.close();
        }
    }
}