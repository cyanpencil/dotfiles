// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PathPattern.java

package org.glassfish.jersey.uri;


// Referenced classes of package org.glassfish.jersey.uri:
//            PathPattern

public static final class regex extends Enum
{

            public static regex[] values()
            {
/*  88*/        return (regex[])$VALUES.clone();
            }

            public static h_3B_.clone valueOf(String s)
            {
/*  88*/        return (h_3B_.clone)Enum.valueOf(org/glassfish/jersey/uri/PathPattern$RightHandPath, s);
            }

            private String getRegex()
            {
/* 108*/        return regex;
            }

            public static final capturingZeroSegments capturingZeroOrMoreSegments;
            public static final capturingZeroSegments capturingZeroSegments;
            private final String regex;
            private static final capturingZeroSegments $VALUES[];

            static 
            {
/*  94*/        capturingZeroOrMoreSegments = new <init>("capturingZeroOrMoreSegments", 0, "(/.*)?");
/*  99*/        capturingZeroSegments = new <init>("capturingZeroSegments", 1, "(/)?");
/*  88*/        $VALUES = (new .VALUES[] {
/*  88*/            capturingZeroOrMoreSegments, capturingZeroSegments
                });
            }


            private (String s, int i, String s1)
            {
/* 103*/        super(s, i);
/* 104*/        regex = s1;
            }
}
