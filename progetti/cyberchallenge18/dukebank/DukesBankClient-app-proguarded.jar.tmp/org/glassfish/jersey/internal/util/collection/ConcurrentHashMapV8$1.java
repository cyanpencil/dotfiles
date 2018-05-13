// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConcurrentHashMapV8.java

package org.glassfish.jersey.internal.util.collection;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            ConcurrentHashMapV8

static class I
    implements PrivilegedExceptionAction
{

            public final Unsafe run()
                throws Exception
            {
                /*<invalid signature>*/java.lang.Object local;
                Field afield[];
/*3549*/        int i = (afield = (local = sun/misc/Unsafe).getDeclaredFields()).length;
/*3550*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/*3550*/            ((Field) (obj = afield[j])).setAccessible(true);
/*3552*/            obj = ((Field) (obj)).get(null);
/*3553*/            if(local.isInstance(obj))
/*3554*/                return (Unsafe)local.cast(obj);
                }

/*3556*/        throw new NoSuchFieldError("the Unsafe");
            }

            public final volatile Object run()
                throws Exception
            {
/*3547*/        return run();
            }

            I()
            {
            }
}
