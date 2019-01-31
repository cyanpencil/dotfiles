// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ScopeInfo.java

package org.glassfish.hk2.utilities.reflection;

import java.lang.annotation.Annotation;

public class ScopeInfo
{

            public ScopeInfo(Annotation annotation, Class class1)
            {
/*  61*/        scope = annotation;
/*  62*/        annoType = class1;
            }

            public Annotation getScope()
            {
/*  70*/        return scope;
            }

            public Class getAnnoType()
            {
/*  79*/        return annoType;
            }

            private final Annotation scope;
            private final Class annoType;
}
