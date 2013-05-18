package com.twoqubed.annotation.processor.processor;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static javax.lang.model.type.TypeKind.DECLARED;

public class BuilderMetaData {
    String fqClassName;
    String className;
    String packageName;

    final Set<String> imports = newHashSet();
    final List<ConstructorParam> parameters = newArrayList();

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
