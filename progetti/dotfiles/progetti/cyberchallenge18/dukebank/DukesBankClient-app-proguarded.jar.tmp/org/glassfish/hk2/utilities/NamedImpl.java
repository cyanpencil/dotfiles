// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NamedImpl.java

package org.glassfish.hk2.utilities;

import javax.inject.Named;
import org.glassfish.hk2.api.AnnotationLiteral;

public class NamedImpl extends AnnotationLiteral
    implements Named
{

            public NamedImpl(String s)
            {
/*  65*/        name = s;
            }

            public String value()
            {
/*  73*/        return name;
            }

            public String toString()
            {
/*  77*/        return (new StringBuilder("@Named(")).append(name).append(")").toString();
            }

            private static final long serialVersionUID = 0x7e6e606bb99d7453L;
            private final String name;
}
