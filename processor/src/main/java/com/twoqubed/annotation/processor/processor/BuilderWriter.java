package com.twoqubed.annotation.processor.processor;

import java.io.Writer;

public interface BuilderWriter {

    void writeBeanInfo(BuilderMetadata builderMetadata, Writer writer) throws Exception;

}
