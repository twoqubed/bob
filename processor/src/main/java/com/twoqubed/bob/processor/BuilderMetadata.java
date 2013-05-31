package com.twoqubed.bob.processor;

import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.List;

public class BuilderMetadata {
    String fqClassName;
    String className;
    String packageName;

    final List<ConstructorParam> parameters = new ArrayList<ConstructorParam>();

    void addConstructorParam(VariableElement element) {
        ConstructorParam constructorParam = new ConstructorParam(element);
        parameters.add(constructorParam);
        parameters.get(parameters.size() - 1).last = true;
        if (parameters.size() > 1) {
            parameters.get(parameters.size() - 2).last = false;
        }
    }
}
