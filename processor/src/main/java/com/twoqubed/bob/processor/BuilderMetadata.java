package com.twoqubed.bob.processor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BuilderMetadata {
    String fqClassName;
    String className;
    String packageName;

    private final List<ConstructorParam> parameters = new ArrayList<ConstructorParam>();

    void addConstructorParam(ConstructorParam constructorParam) {
        parameters.add(constructorParam);
    }

    public List<ConstructorParam> getParameters() {
        List<ConstructorParam> copy = new ArrayList<ConstructorParam>(parameters.size());
        for (Iterator<ConstructorParam> i = parameters.iterator(); i.hasNext(); ) {
            ConstructorParam param = i.next();
            copy.add(new ConstructorParam(param.getName(), param.getType(), !i.hasNext()));
        }
        return copy;
    }
}
