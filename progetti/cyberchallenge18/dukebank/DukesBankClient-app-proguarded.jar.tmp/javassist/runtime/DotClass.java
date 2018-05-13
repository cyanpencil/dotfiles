// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DotClass.java

package javassist.runtime;


public class DotClass
{

            public DotClass()
            {
            }

            public static NoClassDefFoundError fail(ClassNotFoundException classnotfoundexception)
            {
/*  27*/        return new NoClassDefFoundError(classnotfoundexception.getMessage());
            }
}
