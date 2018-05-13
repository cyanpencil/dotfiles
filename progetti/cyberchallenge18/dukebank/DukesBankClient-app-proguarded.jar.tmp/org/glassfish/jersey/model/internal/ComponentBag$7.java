// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ComponentBag.java

package org.glassfish.jersey.model.internal;

import jersey.repackaged.com.google.common.base.Predicate;

// Referenced classes of package org.glassfish.jersey.model.internal:
//            ComponentBag

class val.filter
    implements Predicate
{

            public boolean apply(Object obj)
            {
/* 562*/        obj = getModel(obj.getClass());
/* 563*/        return val$filter.apply(obj);
            }

            final Predicate val$filter;
            final ComponentBag this$0;

            ()
            {
/* 559*/        this$0 = final_componentbag;
/* 559*/        val$filter = Predicate.this;
/* 559*/        super();
            }
}
