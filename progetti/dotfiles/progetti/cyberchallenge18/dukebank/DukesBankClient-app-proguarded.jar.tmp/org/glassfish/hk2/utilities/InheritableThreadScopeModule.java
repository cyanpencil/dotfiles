// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InheritableThreadScopeModule.java

package org.glassfish.hk2.utilities;

import org.glassfish.hk2.internal.InheritableThreadContext;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class InheritableThreadScopeModule extends AbstractBinder
{

            public InheritableThreadScopeModule()
            {
            }

            protected void configure()
            {
/*  60*/        addActiveDescriptor(org/glassfish/hk2/internal/InheritableThreadContext);
            }
}
