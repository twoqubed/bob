package com.twoqubed.annotation.sample;

import com.twoqubed.annotation.Built;

@Built
public class Sample {

    private final boolean aBoolean;
    private final char aChar;
    private final short aShort;
    private final int anInt;
    private final long aLong;
    private final float aFloat;
    private final double aDouble;
    private final String aString;

    public Sample(boolean aBoolean, char aChar, short aShort, int anInt, long aLong, float aFloat, double aDouble,
                  String aString) {
        this.aBoolean = aBoolean;
        this.aChar = aChar;
        this.aShort = aShort;
        this.anInt = anInt;
        this.aLong = aLong;
        this.aFloat = aFloat;
        this.aDouble = aDouble;
        this.aString = aString;
    }

    public boolean isBoolean() {
        return aBoolean;
    }

    public char getChar() {
        return aChar;
    }

    public short getShort() {
        return aShort;
    }

    public int getInt() {
        return anInt;
    }

    public long getLong() {
        return aLong;
    }

    public float getFloat() {
        return aFloat;
    }

    public double getDouble() {
        return aDouble;
    }

    public String getString() {
        return aString;
    }

}