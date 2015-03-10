package com.twoqubed.bob.sample;

import com.twoqubed.bob.annotation.Built;

@Built(generateCopyMethod = true)
public class CopySample {
    private final String string;
    private final boolean primitiveBoolean;
    private final Boolean objectBoolean;

    public CopySample(String string, boolean primitiveBoolean, Boolean objectBoolean) {
        this.string = string;
        this.primitiveBoolean = primitiveBoolean;
        this.objectBoolean = objectBoolean;
    }

    public String getString() {
        return string;
    }

    public boolean isPrimitiveBoolean() {
        return primitiveBoolean;
    }

    public Boolean getObjectBoolean() {
        return objectBoolean;
    }
}