// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PerLocatorUtilities.java

package org.jvnet.hk2.internal;

import java.util.WeakHashMap;
import org.glassfish.hk2.utilities.general.Hk2ThreadLocal;

// Referenced classes of package org.jvnet.hk2.internal:
//            PerLocatorUtilities

class Local extends Hk2ThreadLocal
{

            protected WeakHashMap initialValue()
            {
/*  79*/        return new WeakHashMap();
            }

            protected volatile Object initialValue()
            {
/*  76*/        return initialValue();
            }

            final PerLocatorUtilities this$0;

            Local()
            {
/*  76*/        this$0 = PerLocatorUtilities.this;
/*  76*/        super();
            }
}
