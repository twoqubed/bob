package com.twoqubed.bob.sample;

import com.twoqubed.bob.annotation.Built;

@Built
public class ArraysSample {

    private final boolean[] aBoolean;
    private final byte[] aByte;
    private final char[] aChar;
    private final short[] aShort;
    private final int[] anInt;
    private final long[] aLong;
    private final float[] aFloat;
    private final double[] aDouble;
    private final String[] aString;
    private final java.util.Date[] aDate;
    private final Date[] aCustomDate;
    private final boolean[][] twoDimensional;

    public ArraysSample(boolean[] aBoolean, byte[] aByte, char[] aChar, short[] aShort, int[] anInt, long[] aLong,
                        float[] aFloat, double[] aDouble, String[] aString, java.util.Date[] aDate,
                        Date[] aCustomDate, boolean[][] twoDimensional) {
        this.aBoolean = aBoolean;
        this.aByte = aByte;
        this.aChar = aChar;
        this.aShort = aShort;
        this.anInt = anInt;
        this.aLong = aLong;
        this.aFloat = aFloat;
        this.aDouble = aDouble;
        this.aString = aString;
        this.aDate = aDate;
        this.aCustomDate = aCustomDate;
        this.twoDimensional = twoDimensional;
    }

}