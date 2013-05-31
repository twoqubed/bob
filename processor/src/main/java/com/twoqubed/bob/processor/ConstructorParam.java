package com.twoqubed.bob.processor;

import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

public class ConstructorParam {

    private final VariableElement variableElement;
    boolean last;

    public ConstructorParam(VariableElement variableElement) {
        this.variableElement = variableElement;
    }

    public String getMethodName() {
        String simpleName = variableElement.getSimpleName().toString();
        return String.format("%s%s%s",
                "with",
                simpleName.substring(0, 1).toUpperCase(),
                simpleName.substring(1));
    }

    public String getName() {
        return variableElement.getSimpleName().toString();
    }

    public String getParam() {
        if (last) {
            return getName();
        }
        return getName() + ",";
    }


    public String getType() {
        return getType(variableElement);
    }

    private String getType(Element element) {
        TypeMirror typeMirror = element.asType();
        return typeMirror.toString();
    }
}
