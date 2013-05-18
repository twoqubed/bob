package com.twoqubed.annotation.sample;

import com.twoqubed.annotation.Built;

@Built
public class BaseTypesSample {
    private final String aString;

    public BaseTypesSample(String aString) {
        this.aString = aString;
    }

    public String getString() {
        return aString;
    }

}