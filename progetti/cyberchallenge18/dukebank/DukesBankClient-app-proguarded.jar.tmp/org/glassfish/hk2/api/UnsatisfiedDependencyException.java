// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UnsatisfiedDependencyException.java

package org.glassfish.hk2.api;


// Referenced classes of package org.glassfish.hk2.api:
//            HK2RuntimeException, Injectee

public class UnsatisfiedDependencyException extends HK2RuntimeException
{

            public UnsatisfiedDependencyException()
            {
/*  61*/        this(null);
            }

            public UnsatisfiedDependencyException(Injectee injectee)
            {
/*  70*/        super((new StringBuilder("There was no object available for injection at ")).append(injectee != null ? injectee.toString() : "<null>").toString());
/*  72*/        injectionPoint = injectee;
            }

            public Injectee getInjectee()
            {
/*  81*/        return injectionPoint;
            }

            private static final long serialVersionUID = 0x1087739452eb4787L;
            private final transient Injectee injectionPoint;
}
