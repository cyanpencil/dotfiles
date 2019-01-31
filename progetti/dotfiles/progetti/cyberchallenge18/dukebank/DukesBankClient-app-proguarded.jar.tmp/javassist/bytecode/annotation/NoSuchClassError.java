// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NoSuchClassError.java

package javassist.bytecode.annotation;


public class NoSuchClassError extends Error
{

            public NoSuchClassError(String s, Error error)
            {
/*  30*/        super(error.toString(), error);
/*  31*/        className = s;
            }

            public String getClassName()
            {
/*  38*/        return className;
            }

            private String className;
}
