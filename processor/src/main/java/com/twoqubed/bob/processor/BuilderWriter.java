package com.twoqubed.bob.processor;

import java.io.Writer;

public interface BuilderWriter {

    void writeBeanInfo(BuilderMetadata builderMetadata, Writer writer) throws Exception;

}
