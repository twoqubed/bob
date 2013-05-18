package com.twoqubed.annotation.processor.processor;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

public class VelocityBuilderWriter implements BuilderWriter {

    private final VelocityEngine engine;

    public VelocityBuilderWriter() {
        this.engine = initializeVelocityEngine();
    }

    @Override
    public void writeBeanInfo(BuilderMetaData builderMetaData, Writer writer) throws Exception {
        VelocityContext context = initializeVelocityContext(builderMetaData);
        Template template = engine.getTemplate("beaninfo.vm");
        writeFile(context, template, writer);
    }

    private VelocityEngine initializeVelocityEngine() {
        Properties props = new Properties();
        props.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.SystemLogChute");
        props.setProperty("resource.loader", "classpath");
        props.setProperty("classpath.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

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

    private void writeFile(VelocityContext context, Template template, Writer writer) throws IOException {
        template.merge(context, writer);
        writer.close();
    }
}
