// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SecurityActions.java

package javassist.util.proxy;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;

// Referenced classes of package javassist.util.proxy:
//            SecurityActions

static class val.value
    implements PrivilegedExceptionAction
{

            public final Object run()
                throws Exception
            {
/* 123*/        val$fld.set(val$target, val$value);
/* 124*/        return null;
            }

            final Field val$fld;
            final Object val$target;
            final Object val$value;

            (Field field, Object obj, Object obj1)
            {
/* 121*/        val$fld = field;
/* 121*/        val$target = obj;
/* 121*/        val$value = obj1;
/* 121*/        super();
            }
}
