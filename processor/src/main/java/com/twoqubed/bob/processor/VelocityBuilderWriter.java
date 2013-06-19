package com.twoqubed.bob.processor;

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

    @Override
    public void writeBuilder(BuilderMetadata builderMetadata, Writer writer) throws Exception {
        VelocityContext context = initializeVelocityContext(builderMetadata);
        Template template = engine.getTemplate("builder.vm");
        writeFile(context, template, writer);
    }

    private VelocityContext initializeVelocityContext(BuilderMetadata builderMetadata) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("className", builderMetadata.className);
        velocityContext.put("packageName", builderMetadata.packageName);
        velocityContext.put("parameters", builderMetadata.getParameters());
        return velocityContext;
    }

    private void writeFile(VelocityContext context, Template template, Writer writer) throws IOException {
        template.merge(context, writer);
        writer.close();
    }
}
