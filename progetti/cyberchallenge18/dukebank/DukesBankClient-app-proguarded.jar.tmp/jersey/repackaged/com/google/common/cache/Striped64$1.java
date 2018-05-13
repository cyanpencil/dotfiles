// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Striped64.java

package jersey.repackaged.com.google.common.cache;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            Striped64

static class 
    implements PrivilegedExceptionAction
{

            public final Unsafe run()
                throws Exception
            {
                /*<invalid signature>*/java.lang.Object local;
                Field afield[];
/* 324*/        int i = (afield = (local = sun/misc/Unsafe).getDeclaredFields()).length;
/* 325*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/* 325*/            ((Field) (obj = afield[j])).setAccessible(true);
/* 327*/            obj = ((Field) (obj)).get(null);
/* 328*/            if(local.isInstance(obj))
/* 329*/                return (Unsafe)local.cast(obj);
                }

/* 331*/        throw new NoSuchFieldError("the Unsafe");
            }

            public final volatile Object run()
                throws Exception
            {
/* 322*/        return run();
            }

            ()
            {
            }
}
