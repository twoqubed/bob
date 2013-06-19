package com.twoqubed.bob.processor;

public class ConstructorParam {

    private final String simpleName;
    private final String type;
    boolean last;

    public ConstructorParam(String simpleName, String type) {
        this(simpleName, type, false);
    }

    public ConstructorParam(String simpleName, String type, boolean last) {
        this.simpleName = simpleName;
        this.type = type;
        this.last = last;
    }

    public String getMethodName() {
        return String.format("%s%s%s",
                "with",
                simpleName.substring(0, 1).toUpperCase(),
                simpleName.substring(1));
    }

    public String getName() {
        return simpleName;
    }

    public String getParam() {
        if (last) {
            return getName();
        }
        return getName() + ",";
    }


    public String getType() {
        return type;
    }

}
