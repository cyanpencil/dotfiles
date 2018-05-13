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

            public boolean apply(Class class1)
            {
/* 543*/        class1 = getModel(class1);
/* 544*/        return val$filter.apply(class1);
            }

            public volatile boolean apply(Object obj)
            {
/* 540*/        return apply((Class)obj);
            }

            final Predicate val$filter;
            final ComponentBag this$0;

            ()
            {
/* 540*/        this$0 = final_componentbag;
/* 540*/        val$filter = Predicate.this;
/* 540*/        super();
            }
}
