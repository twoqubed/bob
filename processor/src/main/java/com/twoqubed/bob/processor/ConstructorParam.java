package com.twoqubed.bob.processor;

import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
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
                Element element = ((DeclaredType) typeMirror).asElement();
                TypeElement typeElement = (TypeElement) element;
                PackageElement packageElement = (PackageElement) typeElement.getEnclosingElement();
                String packageName = packageElement.getQualifiedName().toString();
                if (packageName.equals("java.lang")) {
                    return typeElement.getSimpleName().toString();
                }
                return typeElement.getQualifiedName().toString();

            default:
                return "Object";
        }
    }
}
