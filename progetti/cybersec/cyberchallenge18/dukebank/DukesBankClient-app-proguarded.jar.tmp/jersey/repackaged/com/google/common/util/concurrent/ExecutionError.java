// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExecutionError.java

package jersey.repackaged.com.google.common.util.concurrent;


public class ExecutionError extends Error
{

            protected ExecutionError()
            {
            }

            public ExecutionError(Error error)
            {
/*  60*/        super(error);
            }
}
