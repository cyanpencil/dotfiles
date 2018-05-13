// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LinkedTransferQueue.java

package org.glassfish.jersey.internal.util.collection;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            LinkedTransferQueue

static class I
    implements PrivilegedExceptionAction
{

            public final Unsafe run()
                throws Exception
            {
                /*<invalid signature>*/java.lang.Object local;
                Field afield[];
/*1379*/        int i = (afield = (local = sun/misc/Unsafe).getDeclaredFields()).length;
/*1380*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/*1380*/            ((Field) (obj = afield[j])).setAccessible(true);
/*1382*/            obj = ((Field) (obj)).get(null);
/*1383*/            if(local.isInstance(obj))
/*1384*/                return (Unsafe)local.cast(obj);
                }

/*1386*/        throw new NoSuchFieldError("the Unsafe");
            }

            public final volatile Object run()
                throws Exception
            {
/*1377*/        return run();
            }

            I()
            {
            }
}
