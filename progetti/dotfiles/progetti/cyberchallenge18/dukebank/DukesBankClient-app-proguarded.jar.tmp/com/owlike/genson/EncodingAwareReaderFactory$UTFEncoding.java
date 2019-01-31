// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EncodingAwareReaderFactory.java

package com.owlike.genson;


// Referenced classes of package com.owlike.genson:
//            EncodingAwareReaderFactory

static final class bytes extends Enum
{

            public static bytes[] values()
            {
/*  11*/        return (bytes[])$VALUES.clone();
            }

            public static g_3B_.clone valueOf(String s)
            {
/*  11*/        return (g_3B_.clone)Enum.valueOf(com/owlike/genson/EncodingAwareReaderFactory$UTFEncoding, s);
            }

            public final String encoding()
            {
/*  21*/        return name().replace('_', '-');
            }

            public static final UNKNOWN UTF_32BE;
            public static final UNKNOWN UTF_32LE;
            public static final UNKNOWN UTF_16BE;
            public static final UNKNOWN UTF_16LE;
            public static final UNKNOWN UTF_8;
            public static final UNKNOWN UNKNOWN;
            final int bytes;
            private static final UNKNOWN $VALUES[];

            static 
            {
/*  12*/        UTF_32BE = new <init>("UTF_32BE", 0, 4);
/*  12*/        UTF_32LE = new <init>("UTF_32LE", 1, 4);
/*  12*/        UTF_16BE = new <init>("UTF_16BE", 2, 2);
/*  12*/        UTF_16LE = new <init>("UTF_16LE", 3, 2);
/*  12*/        UTF_8 = new <init>("UTF_8", 4, 1);
/*  12*/        UNKNOWN = new <init>("UNKNOWN", 5, -1);
/*  11*/        $VALUES = (new .VALUES[] {
/*  11*/            UTF_32BE, UTF_32LE, UTF_16BE, UTF_16LE, UTF_8, UNKNOWN
                });
            }

            private (String s, int i, int j)
            {
/*  16*/        super(s, i);
/*  17*/        bytes = j;
            }
}
