// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Optional.java

package jersey.repackaged.com.google.common.base;

import java.io.Serializable;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Absent, Preconditions, Present

public abstract class Optional
    implements Serializable
{

            public static Optional absent()
            {
/*  78*/        return Absent.withType();
            }

            public static Optional of(Object obj)
            {
/*  85*/        return new Present(Preconditions.checkNotNull(obj));
            }

            public static Optional fromNullable(Object obj)
            {
/*  93*/        if(obj == null)
/*  93*/            return absent();
/*  93*/        else
/*  93*/            return new Present(obj);
            }

            Optional()
            {
            }

            public abstract Object orNull();
}
