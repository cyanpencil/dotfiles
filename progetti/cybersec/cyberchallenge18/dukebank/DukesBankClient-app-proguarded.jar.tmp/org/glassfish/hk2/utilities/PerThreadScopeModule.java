// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PerThreadScopeModule.java

package org.glassfish.hk2.utilities;

import org.glassfish.hk2.internal.PerThreadContext;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class PerThreadScopeModule extends AbstractBinder
{

            public PerThreadScopeModule()
            {
            }

            protected void configure()
            {
/*  59*/        addActiveDescriptor(org/glassfish/hk2/internal/PerThreadContext);
            }
}
