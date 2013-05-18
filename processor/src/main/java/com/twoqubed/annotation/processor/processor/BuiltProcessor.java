package com.twoqubed.annotation.processor.processor;

import com.twoqubed.annotation.Built;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
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
            writeBeanInfo(metaData);
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

    private void writeBeanInfo(BuilderMetaData builderMetaData) throws Exception {
        VelocityEngine engine = initializeVelocityEngine();
        VelocityContext context = initializeVelocityContext(builderMetaData);
        Template template = engine.getTemplate("beaninfo.vm");
        writeFile(builderMetaData, context, template);
    }

    private VelocityEngine initializeVelocityEngine() throws Exception {
        URL url = getClass().getClassLoader().getResource("velocity.properties");
        Properties props = new Properties();
        props.load(url.openStream());
        VelocityEngine engine = new VelocityEngine(props);
        engine.init();
        return engine;
    }

    private VelocityContext initializeVelocityContext(BuilderMetaData builderMetaData) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("className", builderMetaData.className);
        velocityContext.put("packageName", builderMetaData.packageName);
        velocityContext.put("parameters", builderMetaData.parameters);
        return velocityContext;
    }

    private void writeFile(BuilderMetaData metaData, VelocityContext context, Template template) throws IOException {
        JavaFileObject fileObject = processingEnv.getFiler().createSourceFile(metaData.fqClassName + "Builder");
        processingEnv.getMessager().printMessage(NOTE, "creating source file: " + fileObject.toUri());
        Writer writer = fileObject.openWriter();
        processingEnv.getMessager().printMessage(NOTE, "applying velocity template: " + template.getName());
        template.merge(context, writer);
        writer.close();
    }

    public static class BuilderMetaData {
        private String fqClassName;
        private String className;
        private String packageName;

        private final List<ConstructorParam> parameters = new ArrayList<ConstructorParam>();

        private void addConstructorParam(VariableElement element) {
            parameters.add(new ConstructorParam(element));
            parameters.get(parameters.size() - 1).last = true;
            if (parameters.size() > 1) {
                parameters.get(parameters.size() - 2).last = false;
            }
        }
    }

    public static class ConstructorParam {

        private final VariableElement variableElement;
        private boolean last;

        public ConstructorParam(VariableElement variableElement) {
            this.variableElement = variableElement;
        }

        public String getMethodName() {
            String simpleName = variableElement.getSimpleName().toString();
            return String.format("%s%s%s",
                    "with",
                    simpleName.substring(0, 1).toUpperCase(),
                    simpleName.substring(1));
        }

        public String getName() {
            return variableElement.getSimpleName().toString();
        }

        public String getParam() {
            if (last) {
                return getName();
            }
            return getName() + ",";
        }

        public String getType() {
            TypeMirror typeMirror = variableElement.asType();
            switch (typeMirror.getKind()) {
                case BOOLEAN:
                    return "boolean";
                case BYTE:
                    return "byte";
                case CHAR:
                    return "char";
                case SHORT:
                    return "short";
                case INT:
                    return "int";
                case LONG:
                    return "long";
                case FLOAT:
                    return "float";
                case DOUBLE:
                    return "double";

                case DECLARED:
                    return ((DeclaredType) typeMirror).asElement().getSimpleName().toString();

                default:
                    return "Object";
            }
        }
    }
}