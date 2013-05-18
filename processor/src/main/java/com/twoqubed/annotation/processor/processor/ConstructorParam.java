package com.twoqubed.annotation.processor.processor;

import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
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
        TypeMirror typeMirror = variableElement.asType();
        switch (typeMirror.getKind()) {
            case BOOLEAN:
                return "boolean";
            case BYTE:
                return "byte";
            case CHAR:
                return "char";
            case SHORT:
                return "short";
            case INT:
                return "int";
            case LONG:
                return "long";
            case FLOAT:
                return "float";
            case DOUBLE:
                return "double";

            case DECLARED:
                return ((DeclaredType) typeMirror).asElement().getSimpleName().toString();

            default:
                return "Object";
        }
    }
}
