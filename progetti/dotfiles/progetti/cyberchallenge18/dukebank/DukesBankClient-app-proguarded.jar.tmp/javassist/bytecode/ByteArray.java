// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ByteArray.java

package javassist.bytecode;


public class ByteArray
{

            public ByteArray()
            {
            }

            public static int readU16bit(byte abyte0[], int i)
            {
/*  27*/        return (abyte0[i] & 0xff) << 8 | abyte0[i + 1] & 0xff;
            }

            public static int readS16bit(byte abyte0[], int i)
            {
/*  34*/        return abyte0[i] << 8 | abyte0[i + 1] & 0xff;
            }

            public static void write16bit(int i, byte abyte0[], int j)
            {
/*  41*/        abyte0[j] = (byte)(i >>> 8);
/*  42*/        abyte0[j + 1] = (byte)i;
            }

            public static int read32bit(byte abyte0[], int i)
            {
/*  49*/        return abyte0[i] << 24 | (abyte0[i + 1] & 0xff) << 16 | (abyte0[i + 2] & 0xff) << 8 | abyte0[i + 3] & 0xff;
            }

            public static void write32bit(int i, byte abyte0[], int j)
            {
/*  57*/        abyte0[j] = i >> 24;
/*  58*/        abyte0[j + 1] = (byte)(i >>> 16);
/*  59*/        abyte0[j + 2] = (byte)(i >>> 8);
/*  60*/        abyte0[j + 3] = (byte)i;
            }

            static void copy32bit(byte abyte0[], int i, byte abyte1[], int j)
            {
/*  72*/        abyte1[j] = abyte0[i];
/*  73*/        abyte1[j + 1] = abyte0[i + 1];
/*  74*/        abyte1[j + 2] = abyte0[i + 2];
/*  75*/        abyte1[j + 3] = abyte0[i + 3];
            }
}
