// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Absent.java

package jersey.repackaged.com.google.common.base;


// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Optional

final class Absent extends Optional
{

            static Optional withType()
            {
/*  37*/        return INSTANCE;
            }

            private Absent()
            {
            }

            public final Object orNull()
            {
/*  65*/        return null;
            }

            public final boolean equals(Object obj)
            {
/*  78*/        return obj == this;
            }

            public final int hashCode()
            {
/*  82*/        return 0x598df91c;
            }

            public final String toString()
            {
/*  86*/        return "Optional.absent()";
            }

            static final Absent INSTANCE = new Absent();

}
