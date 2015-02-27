package com.twoqubed.bob.processor;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static java.lang.String.*;

public class InternalBuilderWriter implements BuilderWriter {

    @Override
    public void writeBuilder(BuilderMetadata builderMetadata, Writer writer) throws Exception {
        writePackageName(builderMetadata, writer);
        writeClassBody(builderMetadata, writer);
        writer.close();
    }

    private void writePackageName(BuilderMetadata builderMetadata, Writer writer) throws IOException {
        writer.write("package ");
        writer.write(builderMetadata.packageName);
        writer.write(";\n\n");
    }

    private void writeClassBody(BuilderMetadata metadata, Writer writer) throws IOException {
        writer.write(format("public class %sBuilder {\n\n", metadata.className));

        for (ConstructorParam param : metadata.getParameters()) {
            writeField(writer, param);
        }

        writer.write("\n");
        writeStaticBuilderMethod(metadata, writer);
        writeStaticFromObjectMethod(metadata, writer);

        for (ConstructorParam param : metadata.getParameters()) {
            writeParamMethod(metadata, writer, param);
        }

        writer.write(format("    public %s build() {\n", metadata.className));
        writer.write(format("        return new %s(\n", metadata.className));
        for (ConstructorParam param : metadata.getParameters()) {
            writer.write(format("                %s\n", param.getParam()));
        }
        writer.write("        );\n");
        writer.write("    }\n}\n");
    }

    private void writeField(Writer writer, ConstructorParam param) throws IOException {
        writer.write(format("    private %s %s;\n", param.getType(), param.getName()));
    }

    private void writeStaticBuilderMethod(BuilderMetadata builderMetadata, Writer writer) throws IOException {
        writer.write(format("    public static %sBuilder builder() {\n", builderMetadata.className));
        writer.write(format("        return new %sBuilder();\n", builderMetadata.className));
        writer.write("    }\n\n");
    }

    private void writeStaticFromObjectMethod(BuilderMetadata metadata, Writer writer) throws IOException {
        writer.write(format("    public static %sBuilder from%s(%s %s) {\n", metadata.className, metadata.className,
                metadata.className, metadata.className.toLowerCase()));
        writer.write(format("        return new %sBuilder()\n", metadata.className));
        List<ConstructorParam> parameters = metadata.getParameters();
        for (ConstructorParam param : parameters) {
            writer.write(format("                .%s(%s.get%s())%s\n",
                    param.getMethodName(), metadata.className.toLowerCase(), capitalize(param.getName()),
                    maybeAppendSemiColon(param, parameters)));
        }

        writer.write("    }\n\n");
    }

    private String capitalize(String name) {
        return String.format("%s%s",
                name.substring(0, 1).toUpperCase(),
                name.substring(1));
    }

    private String maybeAppendSemiColon(ConstructorParam param, List<ConstructorParam> parameters) {
        if (parameters.indexOf(param) == parameters.size() - 1) {
            return ";";
        } else {
            return "";
        }
    }

    private void writeParamMethod(BuilderMetadata metadata, Writer writer, ConstructorParam param) throws IOException {
        writer.write(format("    public %sBuilder %s(%s %s) {\n",
                metadata.className, param.getMethodName(), param.getType(), param.getName()));
        writer.write(format("        this.%s = %s;\n", param.getName(), param.getName()));
        writer.write(("        return this;\n    }\n\n"));
    }

}
