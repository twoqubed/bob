package com.twoqubed.annotation.processor.processor;

import java.io.Writer;

public interface BuilderWriter {

    void writeBeanInfo(BuilderMetaData builderMetaData, Writer writer) throws Exception;

}
