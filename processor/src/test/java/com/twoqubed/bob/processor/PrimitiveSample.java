package com.twoqubed.bob.processor;

import com.twoqubed.bob.annotation.Built;

@Built
public class PrimitiveSample {

    private final boolean aBoolean;
    private final byte aByte;
    private final char aChar;
    private final short aShort;
    private final int anInt;
    private final long aLong;
    private final float aFloat;
    private final double aDouble;

    public PrimitiveSample(boolean aBoolean, byte aByte, char aChar, short aShort, int anInt, long aLong, float aFloat,
                           double aDouble) {
        this.aBoolean = aBoolean;
        this.aByte = aByte;
        this.aChar = aChar;
        this.aShort = aShort;
        this.anInt = anInt;
        this.aLong = aLong;
        this.aFloat = aFloat;
        this.aDouble = aDouble;
    }

    public boolean isBoolean() {
        return aBoolean;
    }

    public byte getByte() {
        return aByte;
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

}