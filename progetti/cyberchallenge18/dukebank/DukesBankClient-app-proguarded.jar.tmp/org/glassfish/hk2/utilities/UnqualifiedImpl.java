// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UnqualifiedImpl.java

package org.glassfish.hk2.utilities;

import java.util.Arrays;
import org.glassfish.hk2.api.AnnotationLiteral;
import org.glassfish.hk2.api.Unqualified;

public class UnqualifiedImpl extends AnnotationLiteral
    implements Unqualified
{

            public transient UnqualifiedImpl(Class aclass[])
            {
/*  71*/        value = (Class[])Arrays.copyOf(aclass, aclass.length);
            }

            public Class[] value()
            {
/*  84*/        return (Class[])Arrays.copyOf(value, value.length);
            }

            public String toString()
            {
/*  89*/        return (new StringBuilder("UnqualifiedImpl(")).append(Arrays.toString(value)).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private static final long serialVersionUID = 0x6ec6ed02009c4183L;
            private final Class value[];
}
