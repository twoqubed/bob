package com.twoqubed.bob.sample;

import com.twoqubed.bob.annotation.Built;

@Built
public class NoArgConstructorSample {
    private String aString;

    public NoArgConstructorSample() {
    }

    public NoArgConstructorSample(String aString) {
        this.aString = aString;
    }

    public String getString() {
        return aString;
    }
}