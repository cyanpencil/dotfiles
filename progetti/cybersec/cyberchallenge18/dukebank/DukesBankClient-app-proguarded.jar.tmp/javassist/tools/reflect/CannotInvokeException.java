// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CannotInvokeException.java

package javassist.tools.reflect;

import java.lang.reflect.InvocationTargetException;

public class CannotInvokeException extends RuntimeException
{

            public Throwable getReason()
            {
/*  37*/        return err;
            }

            public CannotInvokeException(String s)
            {
/*  43*/        super(s);
/*  32*/        err = null;
            }

            public CannotInvokeException(InvocationTargetException invocationtargetexception)
            {
/*  50*/        super((new StringBuilder("by ")).append(invocationtargetexception.getTargetException().toString()).toString());
/*  32*/        err = null;
/*  51*/        err = invocationtargetexception.getTargetException();
            }

            public CannotInvokeException(IllegalAccessException illegalaccessexception)
            {
/*  58*/        super((new StringBuilder("by ")).append(illegalaccessexception.toString()).toString());
/*  32*/        err = null;
/*  59*/        err = illegalaccessexception;
            }

            public CannotInvokeException(ClassNotFoundException classnotfoundexception)
            {
/*  66*/        super((new StringBuilder("by ")).append(classnotfoundexception.toString()).toString());
/*  32*/        err = null;
/*  67*/        err = classnotfoundexception;
            }

            private Throwable err;
}
