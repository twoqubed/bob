package com.twoqubed.bob.sample;

import com.twoqubed.bob.annotation.Built;

@Built
public class MultipleConstructorsSample {

    private String aString;
    private String anotherString;

    public MultipleConstructorsSample(String aString) {
        this.aString = aString;
    }

    public MultipleConstructorsSample(String aString, String anotherString) {
        this.aString = aString;
        this.anotherString = anotherString;
    }

}