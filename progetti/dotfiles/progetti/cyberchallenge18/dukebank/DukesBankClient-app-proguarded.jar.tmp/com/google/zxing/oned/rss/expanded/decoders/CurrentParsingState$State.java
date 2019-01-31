// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CurrentParsingState.java

package com.google.zxing.oned.rss.expanded.decoders;


// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            CurrentParsingState

static final class  extends Enum
{

            public static [] values()
            {
/*  37*/        return ([])$VALUES.clone();
            }

            public static e_3B_.clone valueOf(String s)
            {
/*  37*/        return (e_3B_.clone)Enum.valueOf(com/google/zxing/oned/rss/expanded/decoders/CurrentParsingState$State, s);
            }

            public static final ISO_IEC_646 NUMERIC;
            public static final ISO_IEC_646 ALPHA;
            public static final ISO_IEC_646 ISO_IEC_646;
            private static final ISO_IEC_646 $VALUES[];

            static 
            {
/*  38*/        NUMERIC = new <init>("NUMERIC", 0);
/*  39*/        ALPHA = new <init>("ALPHA", 1);
/*  40*/        ISO_IEC_646 = new <init>("ISO_IEC_646", 2);
/*  37*/        $VALUES = (new .VALUES[] {
/*  37*/            NUMERIC, ALPHA, ISO_IEC_646
                });
            }

            private (String s, int i)
            {
/*  37*/        super(s, i);
            }
}
