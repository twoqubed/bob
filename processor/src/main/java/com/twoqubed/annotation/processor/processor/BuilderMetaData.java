package com.twoqubed.annotation.processor.processor;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.lang.model.type.TypeKind.*;

public class BuilderMetadata {
    String fqClassName;
    String className;
    String packageName;

    final Set<String> imports = new HashSet<String>();
    final List<ConstructorParam> parameters = new ArrayList<ConstructorParam>();

    void addConstructorParam(VariableElement element) {
        ConstructorParam constructorParam = new ConstructorParam(element);
        parameters.add(constructorParam);
        parameters.get(parameters.size() - 1).last = true;
        if (parameters.size() > 1) {
            parameters.get(parameters.size() - 2).last = false;
        }

        maybeAddImport(element);
    }

    private void maybeAddImport(VariableElement element) {
        TypeMirror typeMirror = element.asType();
        if (typeMirror.getKind() == DECLARED) {
            DeclaredType declaredType = (DeclaredType) typeMirror;
            TypeElement typeElement = (TypeElement) declaredType.asElement();
            String packageName = typeElement.getQualifiedName().toString();
            if (!packageName.startsWith("java.lang")) {
                imports.add(packageName);
            }
        }
    }
}
