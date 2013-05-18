package com.twoqubed.annotation.processor.processor;

import javax.annotation.processing.ProcessingEnvironment;

public interface BuilderWriter {

    void writeBeanInfo(BuilderMetaData builderMetaData, ProcessingEnvironment processingEnv) throws Exception;

}
