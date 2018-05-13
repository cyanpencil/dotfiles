// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RequestScope.java

package org.glassfish.jersey.process.internal;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ScopedBindingBuilder;

// Referenced classes of package org.glassfish.jersey.process.internal:
//            RequestScope

public static class  extends AbstractBinder
{

            protected void configure()
            {
/* 202*/        bind(new RequestScope()).to(org/glassfish/jersey/process/internal/RequestScope);
            }

            public ()
            {
            }
}
