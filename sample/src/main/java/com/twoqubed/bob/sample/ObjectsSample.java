package com.twoqubed.bob.sample;

import com.twoqubed.bob.annotation.Built;

import java.util.Date;
import java.util.List;

@Built
public class ObjectsSample {
    private final String aString;
    private final Date aDate;
    private final List<String> aListOfStrings;

    public ObjectsSample(String aString, Date aDate, List<String> aListOfStrings) {
        this.aString = aString;
        this.aDate = aDate;
        this.aListOfStrings = aListOfStrings;
    }

    public String getString() {
        return aString;
    }

    public Date getDate() {
        return aDate;
    }

    public List<String> getListOfStrings() {
        return aListOfStrings;
    }
}