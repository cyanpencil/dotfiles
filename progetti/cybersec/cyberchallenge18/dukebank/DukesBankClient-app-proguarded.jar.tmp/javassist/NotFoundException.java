// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NotFoundException.java

package javassist;


public class NotFoundException extends Exception
{

            public NotFoundException(String s)
            {
/*  24*/        super(s);
            }

            public NotFoundException(String s, Exception exception)
            {
/*  28*/        super((new StringBuilder()).append(s).append(" because of ").append(exception.toString()).toString());
            }
}
