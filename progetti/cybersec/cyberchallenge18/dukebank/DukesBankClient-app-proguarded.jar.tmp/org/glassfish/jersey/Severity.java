// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Severity.java

package org.glassfish.jersey;


public final class Severity extends Enum
{

            public static Severity[] values()
            {
/*  45*/        return (Severity[])$VALUES.clone();
            }

            public static Severity valueOf(String s)
            {
/*  45*/        return (Severity)Enum.valueOf(org/glassfish/jersey/Severity, s);
            }

            private Severity(String s, int i)
            {
/*  45*/        super(s, i);
            }

            public static final Severity FATAL;
            public static final Severity WARNING;
            public static final Severity HINT;
            private static final Severity $VALUES[];

            static 
            {
/*  49*/        FATAL = new Severity("FATAL", 0);
/*  53*/        WARNING = new Severity("WARNING", 1);
/*  57*/        HINT = new Severity("HINT", 2);
/*  45*/        $VALUES = (new Severity[] {
/*  45*/            FATAL, WARNING, HINT
                });
            }
}
