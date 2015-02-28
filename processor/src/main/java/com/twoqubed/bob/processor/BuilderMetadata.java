package com.twoqubed.bob.processor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class BuilderMetadata {
    final boolean generateCopyMethod;

    String fqClassName;
    String className;
    String packageName;

    private final List<ConstructorParam> parameters = new ArrayList<ConstructorParam>();

    BuilderMetadata(boolean generateCopyMethod) {
        this.generateCopyMethod = generateCopyMethod;
    }

    void addConstructorParam(ConstructorParam constructorParam) {
        parameters.add(constructorParam);
    }

    List<ConstructorParam> getParameters() {
        List<ConstructorParam> copy = new ArrayList<ConstructorParam>(parameters.size());
        for (Iterator<ConstructorParam> i = parameters.iterator(); i.hasNext(); ) {
            ConstructorParam param = i.next();
            copy.add(new ConstructorParam(param.getName(), param.getType(), !i.hasNext()));
        }
        return copy;
    }
}
