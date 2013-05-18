package com.twoqubed.annotation.processor.processor;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.Properties;

import static javax.tools.Diagnostic.Kind.*;

public class VelocityBuilderWriter implements BuilderWriter {

    @Override
    public void writeBeanInfo(BuilderMetaData builderMetaData, ProcessingEnvironment processingEnv) throws Exception {
        VelocityEngine engine = initializeVelocityEngine();
        VelocityContext context = initializeVelocityContext(builderMetaData);
        Template template = engine.getTemplate("beaninfo.vm");
        writeFile(builderMetaData, context, template, processingEnv);
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
        velocityContext.put("imports", builderMetaData.imports);
        return velocityContext;
    }

    private void writeFile(BuilderMetaData metaData, VelocityContext context, Template template,
                           ProcessingEnvironment processingEnv) throws IOException {
        JavaFileObject fileObject = processingEnv.getFiler().createSourceFile(metaData.fqClassName + "Builder");
        processingEnv.getMessager().printMessage(NOTE, "creating source file: " + fileObject.toUri());
        Writer writer = fileObject.openWriter();
        processingEnv.getMessager().printMessage(NOTE, "applying velocity template: " + template.getName());
        template.merge(context, writer);
        writer.close();
    }
}
