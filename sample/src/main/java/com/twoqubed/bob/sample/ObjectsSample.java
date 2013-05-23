package com.twoqubed.bob.sample;

import com.twoqubed.bob.annotation.Built;

import java.util.Date;

@Built
public class ObjectsSample {
    private final String aString;
    private final Date aDate;

    public ObjectsSample(String aString, Date aDate) {
        this.aString = aString;
        this.aDate = aDate;
    }

    public String getString() {
        return aString;
    }

    public Date getDate() {
        return aDate;
    }
}