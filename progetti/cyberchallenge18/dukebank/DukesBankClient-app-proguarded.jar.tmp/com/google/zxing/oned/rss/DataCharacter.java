// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DataCharacter.java

package com.google.zxing.oned.rss;


public class DataCharacter
{

            public DataCharacter(int i, int j)
            {
/*  25*/        value = i;
/*  26*/        checksumPortion = j;
            }

            public final int getValue()
            {
/*  30*/        return value;
            }

            public final int getChecksumPortion()
            {
/*  34*/        return checksumPortion;
            }

            public final String toString()
            {
/*  39*/        return (new StringBuilder()).append(value).append("(").append(checksumPortion).append(')').toString();
            }

            public final boolean equals(Object obj)
            {
/*  44*/        if(!(obj instanceof DataCharacter))
/*  45*/            return false;
/*  47*/        obj = (DataCharacter)obj;
/*  48*/        return value == ((DataCharacter) (obj)).value && checksumPortion == ((DataCharacter) (obj)).checksumPortion;
            }

            public final int hashCode()
            {
/*  53*/        return value ^ checksumPortion;
            }

            private final int value;
            private final int checksumPortion;
}
