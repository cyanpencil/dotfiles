// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RuntimeSupport.java

package javassist.util.proxy;

import java.io.Serializable;
import java.lang.reflect.Method;

// Referenced classes of package javassist.util.proxy:
//            MethodHandler, RuntimeSupport

static class 
    implements Serializable, MethodHandler
{

            public Object invoke(Object obj, Method method, Method method1, Object aobj[])
                throws Exception
            {
/*  38*/        return method1.invoke(obj, aobj);
            }

            ()
            {
            }
}
