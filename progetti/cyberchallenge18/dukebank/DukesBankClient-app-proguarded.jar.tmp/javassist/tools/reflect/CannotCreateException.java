// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CannotCreateException.java

package javassist.tools.reflect;


public class CannotCreateException extends Exception
{

            public CannotCreateException(String s)
            {
/*  24*/        super(s);
            }

            public CannotCreateException(Exception exception)
            {
/*  28*/        super((new StringBuilder("by ")).append(exception.toString()).toString());
            }
}
