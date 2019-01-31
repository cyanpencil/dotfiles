// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Base64.java

package org.glassfish.jersey.internal.util;

import java.io.UnsupportedEncodingException;

public class Base64
{

            public Base64()
            {
            }

            public static byte[] encode(byte abyte0[])
            {
/*  93*/        int i = abyte0.length / 3;
/*  94*/        int j = abyte0.length % 3;
/*  95*/        byte abyte1[] = new byte[i + (j <= 0 ? 0 : 1) << 2];
/*  97*/        for(int k = 0; k < i; k++)
                {
/*  98*/            abyte1[k << 2] = CHAR_SET[abyte0[k * 3] >> 2 & 0xff];
/*  99*/            abyte1[(k << 2) + 1] = CHAR_SET[((abyte0[k * 3] & 3) << 4 | abyte0[k * 3 + 1] >> 4) & 0xff];
/* 100*/            abyte1[(k << 2) + 2] = CHAR_SET[((abyte0[k * 3 + 1] & 0xf) << 2 | abyte0[k * 3 + 2] >> 6) & 0xff];
/* 101*/            abyte1[(k << 2) + 3] = CHAR_SET[abyte0[k * 3 + 2] & 0x3f];
                }

/* 104*/        int l = 0;
/* 106*/        if(j > 0)
                {
/* 107*/            if(j == 2)
                    {
/* 108*/                abyte1[(i << 2) + 2] = CHAR_SET[(abyte0[i * 3 + 1] & 0xf) << 2 & 0xff];
/* 109*/                l = abyte0[i * 3 + 1] >> 4;
                    } else
                    {
/* 111*/                abyte1[(i << 2) + 2] = CHAR_SET[CHAR_SET.length - 1];
                    }
/* 113*/            abyte1[(i << 2) + 3] = CHAR_SET[CHAR_SET.length - 1];
/* 114*/            abyte1[(i << 2) + 1] = CHAR_SET[((abyte0[i * 3] & 3) << 4 | l) & 0xff];
/* 115*/            abyte1[i << 2] = CHAR_SET[abyte0[i * 3] >> 2 & 0xff];
                }
/* 118*/        return abyte1;
            }

            public static byte[] decode(byte abyte0[])
            {
                byte abyte1[];
/* 129*/label0:
                {
/* 129*/            if(abyte0.length < 4 && abyte0.length % 4 != 0)
/* 130*/                return new byte[0];
/* 133*/            int i = abyte0.length / 4;
/* 134*/            int j = (abyte0[abyte0.length - 1] != 61 ? 0 : 1) + (abyte0[abyte0.length - 2] != 61 ? 0 : 1);
/* 135*/            abyte1 = new byte[3 * (i - 1) + (3 - j)];
/* 137*/            for(int k = 0; k < i - 1; k++)
                    {
/* 138*/                abyte1[k * 3] = (byte)(BASE64INDEXES[abyte0[k << 2]] << 2 | BASE64INDEXES[abyte0[(k << 2) + 1]] >> 4);
/* 139*/                abyte1[k * 3 + 1] = (byte)(BASE64INDEXES[abyte0[(k << 2) + 1]] << 4 | BASE64INDEXES[abyte0[(k << 2) + 2]] >> 2);
/* 140*/                abyte1[k * 3 + 2] = (byte)(BASE64INDEXES[abyte0[(k << 2) + 2]] << 6 | BASE64INDEXES[abyte0[(k << 2) + 3]]);
                    }

/* 143*/            int l = i - 1;
/* 144*/            switch(j)
                    {
/* 146*/            default:
/* 146*/                break label0;

/* 146*/            case 0: // '\0'
/* 146*/                abyte1[l * 3 + 2] = (byte)(BASE64INDEXES[abyte0[(l << 2) + 2]] << 6 | BASE64INDEXES[abyte0[(l << 2) + 3]]);
/* 147*/                abyte1[l * 3 + 1] = (byte)(BASE64INDEXES[abyte0[(l << 2) + 1]] << 4 | BASE64INDEXES[abyte0[(l << 2) + 2]] >> 2);
/* 149*/                break;

/* 129*/            case 2: // '\002'
                        break;

/* 151*/            case 1: // '\001'
/* 151*/                abyte1[l * 3 + 1] = (byte)(BASE64INDEXES[abyte0[(l << 2) + 1]] << 4 | BASE64INDEXES[abyte0[(l << 2) + 2]] >> 2);
                        break;
                    }
/* 155*/            abyte1[l * 3] = (byte)(BASE64INDEXES[abyte0[l << 2]] << 2 | BASE64INDEXES[abyte0[(l << 2) + 1]] >> 4);
                }
/* 159*/        return abyte1;
            }

            public static String encodeAsString(byte abyte0[])
            {
/* 169*/        abyte0 = encode(abyte0);
/* 171*/        return new String(abyte0, "ASCII");
/* 172*/        JVM INSTR pop ;
/* 174*/        return new String(abyte0);
            }

            public static String encodeAsString(String s)
            {
/* 185*/        return encodeAsString(s.getBytes());
            }

            public static String decodeAsString(byte abyte0[])
            {
/* 195*/        abyte0 = decode(abyte0);
/* 197*/        return new String(abyte0, "ASCII");
/* 198*/        JVM INSTR pop ;
/* 200*/        return new String(abyte0);
            }

            public static String decodeAsString(String s)
            {
/* 211*/        return decodeAsString(s.getBytes());
            }

            private static final byte CHAR_SET[];
            private static final byte BASE64INDEXES[] = {
/*  67*/        64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 
/*  67*/        64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 
/*  67*/        64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 
/*  67*/        64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 
/*  67*/        64, 64, 64, 62, 64, 64, 64, 63, 52, 53, 
/*  67*/        54, 55, 56, 57, 58, 59, 60, 61, 64, 64, 
/*  67*/        64, 64, 64, 64, 64, 0, 1, 2, 3, 4, 
/*  67*/        5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 
/*  67*/        15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 
/*  67*/        25, 64, 64, 64, 64, 64, 64, 26, 27, 28, 
/*  67*/        29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 
/*  67*/        39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 
/*  67*/        49, 50, 51, 64, 64, 64, 64, 64, 64, 64, 
/*  67*/        64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 
/*  67*/        64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 
/*  67*/        64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 
/*  67*/        64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 
/*  67*/        64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 
/*  67*/        64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 
/*  67*/        64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 
/*  67*/        64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 
/*  67*/        64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 
/*  67*/        64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 
/*  67*/        64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 
/*  67*/        64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 
/*  67*/        64, 64, 64, 64, 64, 64
            };

            static 
            {
                byte abyte0[];
/*  59*/        try
                {
/*  59*/            abyte0 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".getBytes("ASCII");
                }
/*  60*/        catch(UnsupportedEncodingException _ex)
                {
/*  62*/            abyte0 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".getBytes();
                }
/*  64*/        CHAR_SET = abyte0;
            }
}
