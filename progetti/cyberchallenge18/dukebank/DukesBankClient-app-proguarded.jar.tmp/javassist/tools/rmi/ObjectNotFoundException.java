// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ObjectNotFoundException.java

package javassist.tools.rmi;


public class ObjectNotFoundException extends Exception
{

            public ObjectNotFoundException(String s)
            {
/*  21*/        super((new StringBuilder()).append(s).append(" is not exported").toString());
            }

            public ObjectNotFoundException(String s, Exception exception)
            {
/*  25*/        super((new StringBuilder()).append(s).append(" because of ").append(exception.toString()).toString());
            }
}
