package com.twoqubed.bob.processor;

import java.io.IOException;
import java.io.Writer;

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

    private void writeParamMethod(BuilderMetadata metadata, Writer writer, ConstructorParam param) throws IOException {
        writer.write(format("    public %sBuilder %s(%s %s) {\n",
                metadata.className, param.getMethodName(), param.getType(), param.getName()));
        writer.write(format("        this.%s = %s;\n", param.getName(), param.getName()));
        writer.write(("        return this;\n    }\n\n"));
    }

}
