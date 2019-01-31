// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ErrorResults.java

package org.jvnet.hk2.internal;

import org.glassfish.hk2.api.*;

public class ErrorResults
{

            ErrorResults(ActiveDescriptor activedescriptor, Injectee injectee1, MultiException multiexception)
            {
/*  60*/        descriptor = activedescriptor;
/*  61*/        injectee = injectee1;
/*  62*/        me = multiexception;
            }

            ActiveDescriptor getDescriptor()
            {
/*  69*/        return descriptor;
            }

            Injectee getInjectee()
            {
/*  76*/        return injectee;
            }

            MultiException getMe()
            {
/*  83*/        return me;
            }

            public String toString()
            {
/*  87*/        return (new StringBuilder("ErrorResult(")).append(descriptor).append(",").append(injectee).append(",").append(me).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private final ActiveDescriptor descriptor;
            private final Injectee injectee;
            private final MultiException me;
}
