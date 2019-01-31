// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Ints.java

package jersey.repackaged.com.google.common.primitives;

import java.util.Arrays;

public final class Ints
{

            public static int saturatedCast(long l)
            {
/* 105*/        if(l > 0x7fffffffL)
/* 106*/            return 0x7fffffff;
/* 108*/        if(l < 0xffffffff80000000L)
/* 109*/            return 0x80000000;
/* 111*/        else
/* 111*/            return (int)l;
            }

            public static int compare(int i, int j)
            {
/* 127*/        if(i < j)
/* 127*/            return -1;
/* 127*/        return i <= j ? 0 : 1;
            }

            public static byte[] toByteArray(int i)
            {
/* 300*/        return (new byte[] {
/* 300*/            i >> 24, (byte)(i >> 16), (byte)(i >> 8), (byte)i
                });
            }

            public static int fromBytes(byte byte0, byte byte1, byte byte2, byte byte3)
            {
/* 335*/        return byte0 << 24 | (byte1 & 0xff) << 16 | (byte2 & 0xff) << 8 | byte3 & 0xff;
            }

            private static final byte asciiDigits[];

            static 
            {
/* 639*/        Arrays.fill(asciiDigits = new byte[128], (byte)-1);
/* 643*/        for(int i = 0; i <= 9; i++)
/* 644*/            asciiDigits[i + 48] = (byte)i;

/* 646*/        for(int j = 0; j <= 26; j++)
                {
/* 647*/            asciiDigits[j + 65] = (byte)(j + 10);
/* 648*/            asciiDigits[j + 97] = (byte)(j + 10);
                }

            }
}
