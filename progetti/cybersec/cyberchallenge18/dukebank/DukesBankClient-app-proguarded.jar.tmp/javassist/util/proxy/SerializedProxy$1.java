// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SerializedProxy.java

package javassist.util.proxy;

import java.security.PrivilegedExceptionAction;

// Referenced classes of package javassist.util.proxy:
//            SerializedProxy

class val.className
    implements PrivilegedExceptionAction
{

            public Object run()
                throws Exception
            {
/*  65*/        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
/*  66*/        return Class.forName(val$className, true, classloader);
            }

            final String val$className;
            final SerializedProxy this$0;

            ()
            {
/*  63*/        this$0 = final_serializedproxy;
/*  63*/        val$className = String.this;
/*  63*/        super();
            }
}
