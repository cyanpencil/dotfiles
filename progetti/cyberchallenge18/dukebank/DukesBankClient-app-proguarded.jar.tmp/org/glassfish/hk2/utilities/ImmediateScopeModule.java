// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmediateScopeModule.java

package org.glassfish.hk2.utilities;

import org.glassfish.hk2.internal.ImmediateHelper;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

// Referenced classes of package org.glassfish.hk2.utilities:
//            ImmediateContext

public class ImmediateScopeModule extends AbstractBinder
{

            public ImmediateScopeModule()
            {
            }

            protected void configure()
            {
/*  59*/        addActiveDescriptor(org/glassfish/hk2/utilities/ImmediateContext);
/*  60*/        addActiveDescriptor(org/glassfish/hk2/internal/ImmediateHelper);
            }
}
