// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EncodingAwareReaderFactory.java

package com.owlike.genson;

import java.io.*;

public final class EncodingAwareReaderFactory
{
    static final class UTFEncoding extends Enum
    {

                public static UTFEncoding[] values()
                {
/*  11*/            return (UTFEncoding[])$VALUES.clone();
                }

                public static UTFEncoding valueOf(String s)
                {
/*  11*/            return (UTFEncoding)Enum.valueOf(com/owlike/genson/EncodingAwareReaderFactory$UTFEncoding, s);
                }

                public final String encoding()
                {
/*  21*/            return name().replace('_', '-');
                }

                public static final UTFEncoding UTF_32BE;
                public static final UTFEncoding UTF_32LE;
                public static final UTFEncoding UTF_16BE;
                public static final UTFEncoding UTF_16LE;
                public static final UTFEncoding UTF_8;
                public static final UTFEncoding UNKNOWN;
                final int bytes;
                private static final UTFEncoding $VALUES[];

                static 
                {
/*  12*/            UTF_32BE = new UTFEncoding("UTF_32BE", 0, 4);
/*  12*/            UTF_32LE = new UTFEncoding("UTF_32LE", 1, 4);
/*  12*/            UTF_16BE = new UTFEncoding("UTF_16BE", 2, 2);
/*  12*/            UTF_16LE = new UTFEncoding("UTF_16LE", 3, 2);
/*  12*/            UTF_8 = new UTFEncoding("UTF_8", 4, 1);
/*  12*/            UNKNOWN = new UTFEncoding("UNKNOWN", 5, -1);
/*  11*/            $VALUES = (new UTFEncoding[] {
/*  11*/                UTF_32BE, UTF_32LE, UTF_16BE, UTF_16LE, UTF_8, UNKNOWN
                    });
                }

                private UTFEncoding(String s, int i, int j)
                {
/*  16*/            super(s, i);
/*  17*/            bytes = j;
                }
    }


            public EncodingAwareReaderFactory()
            {
            }

            public final Reader createReader(InputStream inputstream)
                throws IOException
            {
/*  37*/        byte abyte0[] = new byte[4];
                int i;
/*  38*/        if((i = fetchBytes(abyte0, inputstream)) <= 0)
/*  40*/            return new InputStreamReader(inputstream);
/*  43*/        int j = (abyte0[0] & 0xff) << 24 | (abyte0[1] & 0xff) << 16 | (abyte0[2] & 0xff) << 8 | abyte0[3] & 0xff;
/*  48*/        UTFEncoding utfencoding = UTFEncoding.UNKNOWN;
/*  49*/        boolean flag = false;
/*  52*/        if(i == 4)
/*  52*/            utfencoding = detectEncodingFromBOM(j);
/*  55*/        if(utfencoding == UTFEncoding.UNKNOWN)
/*  56*/            utfencoding = detectEncodingUsingJSONSpec(j);
/*  57*/        else
/*  57*/            flag = true;
/*  60*/        if(utfencoding == UTFEncoding.UNKNOWN)
/*  61*/            throw new UnsupportedEncodingException("The encoding could not be detected from the stream.");
/*  64*/        j = flag ? i - (4 - utfencoding.bytes) : 0;
/*  65*/        if((i -= j) == 0)
                {
/*  69*/            return new InputStreamReader(inputstream, utfencoding.encoding());
                } else
                {
/*  71*/            (inputstream = new PushbackInputStream(inputstream, i)).unread(abyte0, j, i);
/*  73*/            return new InputStreamReader(inputstream, utfencoding.encoding());
                }
            }

            private UTFEncoding detectEncodingFromBOM(int i)
            {
/*  78*/        int j = i >>> 16;
/*  80*/        if(i == 65279)
/*  80*/            return UTFEncoding.UTF_32BE;
/*  81*/        if(i == 0xfffe0000)
/*  81*/            return UTFEncoding.UTF_32LE;
/*  82*/        if(j == 65279)
/*  82*/            return UTFEncoding.UTF_16BE;
/*  83*/        if(j == 65534)
/*  83*/            return UTFEncoding.UTF_16LE;
/*  84*/        if(i >>> 8 == 0xefbbbf)
/*  84*/            return UTFEncoding.UTF_8;
/*  85*/        else
/*  85*/            return UTFEncoding.UNKNOWN;
            }

            private UTFEncoding detectEncodingUsingJSONSpec(int i)
            {
/*  89*/        int j = i >>> 16;
/*  91*/        if(i >>> 8 == 0)
/*  91*/            return UTFEncoding.UTF_32BE;
/*  92*/        if((i & 0xffffff) == 0)
/*  92*/            return UTFEncoding.UTF_32LE;
/*  93*/        if((j & 0xff00) == 0)
/*  93*/            return UTFEncoding.UTF_16BE;
/*  94*/        if((j & 0xff) == 0)
/*  94*/            return UTFEncoding.UTF_16LE;
/*  95*/        else
/*  95*/            return UTFEncoding.UTF_8;
            }

            private int fetchBytes(byte abyte0[], InputStream inputstream)
                throws IOException
            {
                int i;
                int j;
/*  99*/        for(i = 0; i < abyte0.length - 1 && (j = inputstream.read(abyte0, i, abyte0.length - i)) >= 0; i += j);
/* 106*/        return i;
            }
}
