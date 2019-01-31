// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Present.java

package jersey.repackaged.com.google.common.base;


// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Optional

final class Present extends Optional
{

            Present(Object obj)
            {
/*  36*/        reference = obj;
            }

            public final Object orNull()
            {
/*  63*/        return reference;
            }

            public final boolean equals(Object obj)
            {
/*  76*/        if(obj instanceof Present)
                {
/*  77*/            obj = (Present)obj;
/*  78*/            return reference.equals(((Present) (obj)).reference);
                } else
                {
/*  80*/            return false;
                }
            }

            public final int hashCode()
            {
/*  84*/        return 0x598df91c + reference.hashCode();
            }

            public final String toString()
            {
/*  88*/        String s = String.valueOf(String.valueOf(reference));
/*  88*/        return (new StringBuilder(13 + s.length())).append("Optional.of(").append(s).append(")").toString();
            }

            private final Object reference;
}
