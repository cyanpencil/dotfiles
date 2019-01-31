// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Utilities.java

package org.jvnet.hk2.internal;

import java.security.PrivilegedAction;
import java.util.Set;
import org.glassfish.hk2.api.Unqualified;

// Referenced classes of package org.jvnet.hk2.internal:
//            Utilities

static class unqualified
{

            private final Set qualifiers;
            private final boolean optional;
            private final boolean self;
            private final Unqualified unqualified;





            private (Set set, boolean flag, boolean flag1, Unqualified unqualified1)
            {
/*2228*/        qualifiers = set;
/*2229*/        optional = flag;
/*2230*/        self = flag1;
/*2231*/        unqualified = unqualified1;
            }


            // Unreferenced inner class org/jvnet/hk2/internal/Utilities$1

/* anonymous class */
    static class Utilities._cls1
        implements PrivilegedAction
    {

                public final Boolean run()
                {
/* 140*/            return Boolean.valueOf(Boolean.parseBoolean(System.getProperty("org.jvnet.hk2.properties.useSoftReference", "true")));
                }

                public final volatile Object run()
                {
/* 136*/            return run();
                }

    }

}
