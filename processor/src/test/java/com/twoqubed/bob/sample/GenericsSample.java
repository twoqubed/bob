package com.twoqubed.bob.sample;

import com.twoqubed.bob.annotation.Built;

import java.util.List;

@Built
public class GenericsSample {

    private final List<String> aList;
    private final List<?> aWildcardList;
    private final List<? extends Number> aBoundedList;

    public GenericsSample(List<String> aList, List<?> aWildcardList, List<? extends Number> aBoundedList) {
        this.aList = aList;
        this.aWildcardList = aWildcardList;
        this.aBoundedList = aBoundedList;
    }

}