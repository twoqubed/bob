package com.twoqubed.bob.sample;

import com.twoqubed.bob.annotation.Built;

@Built
public class PrimitiveSample {

    private final boolean aBoolean;
    private final char aChar;
    private final short aShort;
    private final int anInt;
    private final long aLong;
    private final float aFloat;
    private final double aDouble;

    public PrimitiveSample(boolean aBoolean, char aChar, short aShort, int anInt, long aLong, float aFloat,
                           double aDouble) {
        this.aBoolean = aBoolean;
        this.aChar = aChar;
        this.aShort = aShort;
        this.anInt = anInt;
        this.aLong = aLong;
        this.aFloat = aFloat;
        this.aDouble = aDouble;
    }

    public boolean isABoolean() {
        return aBoolean;
    }

    public char getAChar() {
        return aChar;
    }

    public short getAShort() {
        return aShort;
    }

    public int getAnInt() {
        return anInt;
    }

    public long getALong() {
        return aLong;
    }

    public float getAFloat() {
        return aFloat;
    }

    public double getADouble() {
        return aDouble;
    }

}