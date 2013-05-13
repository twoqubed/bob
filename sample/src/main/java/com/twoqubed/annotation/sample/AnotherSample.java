package com.twoqubed.annotation.sample;

import com.twoqubed.annotation.Built;

@Built
public class AnotherSample {
    private final String aString;

    public AnotherSample(String aString) {
        this.aString = aString;
    }

    public String getString() {
        return aString;
    }

}