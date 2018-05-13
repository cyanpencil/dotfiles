// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MediaTypes.java

package org.glassfish.jersey.message.internal;

import jersey.repackaged.com.google.common.base.Predicate;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            MediaTypes

static class 
    implements Predicate
{

            public final boolean apply(String s)
            {
/* 161*/        return !"qs".equals(s) && !"q".equals(s);
            }

            public final volatile boolean apply(Object obj)
            {
/* 158*/        return apply((String)obj);
            }

            ()
            {
            }
}
