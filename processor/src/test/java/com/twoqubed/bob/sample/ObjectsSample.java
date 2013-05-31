package com.twoqubed.bob.sample;

import com.twoqubed.bob.annotation.Built;

@Built
public class ObjectsSample {
    private final String aString;
    private final java.util.Date aDate;
    private final Date anotherDate;

    public ObjectsSample(String aString, java.util.Date aDate, Date anotherDate) {
        this.aString = aString;
        this.aDate = aDate;
        this.anotherDate = anotherDate;
    }

    public String getString() {
        return aString;
    }

    public java.util.Date getDate() {
        return aDate;
    }

    public Date getAnotherDate() {
        return anotherDate;
    }
}