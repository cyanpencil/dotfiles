// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ComponentBag.java

package org.glassfish.jersey.model.internal;

import java.util.*;
import javax.ws.rs.core.Feature;
import jersey.repackaged.com.google.common.base.Predicate;
import jersey.repackaged.com.google.common.collect.Sets;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.jersey.model.ContractProvider;
import org.glassfish.jersey.process.Inflector;

// Referenced classes of package org.glassfish.jersey.model.internal:
//            ComponentBag

static class  extends ComponentBag
{

            public boolean register(Class class1, Inflector inflector)
            {
/* 651*/        throw new IllegalStateException("This instance is read-only.");
            }

            public boolean register(Class class1, int i, Inflector inflector)
            {
/* 658*/        throw new IllegalStateException("This instance is read-only.");
            }

            public boolean register(Class class1, Set set, Inflector inflector)
            {
/* 665*/        throw new IllegalStateException("This instance is read-only.");
            }

            public boolean register(Class class1, Map map, Inflector inflector)
            {
/* 672*/        throw new IllegalStateException("This instance is read-only.");
            }

            public boolean register(Object obj, Inflector inflector)
            {
/* 677*/        throw new IllegalStateException("This instance is read-only.");
            }

            public boolean register(Object obj, int i, Inflector inflector)
            {
/* 684*/        throw new IllegalStateException("This instance is read-only.");
            }

            public boolean register(Object obj, Set set, Inflector inflector)
            {
/* 691*/        throw new IllegalStateException("This instance is read-only.");
            }

            public boolean register(Object obj, Map map, Inflector inflector)
            {
/* 698*/        throw new IllegalStateException("This instance is read-only.");
            }

            public ComponentBag copy()
            {
/* 704*/        return this;
            }

            public ComponentBag immutableCopy()
            {
/* 710*/        return this;
            }

            public void clear()
            {
/* 715*/        throw new IllegalStateException("This instance is read-only.");
            }

            public (ComponentBag componentbag)
            {
/* 643*/        super(ComponentBag.access$200(componentbag), Sets.newLinkedHashSet(ComponentBag.access$300(componentbag)), Sets.newLinkedHashSet(ComponentBag.access$400(componentbag)), new IdentityHashMap(ComponentBag.access$000(componentbag)));
            }

            // Unreferenced inner class org/glassfish/jersey/model/internal/ComponentBag$1

/* anonymous class */
    static class ComponentBag._cls1
        implements Predicate
    {

                public final boolean apply(ContractProvider contractprovider)
                {
/* 101*/            if((contractprovider = contractprovider.getContracts()).isEmpty())
/* 103*/                return true;
/* 106*/            byte byte0 = 0;
/* 107*/            if(contractprovider.contains(javax/ws/rs/core/Feature))
/* 108*/                byte0 = 1;
/* 110*/            if(contractprovider.contains(org/glassfish/hk2/utilities/Binder))
/* 111*/                byte0++;
/* 113*/            return contractprovider.size() > byte0;
                }

                public final volatile boolean apply(Object obj)
                {
/*  98*/            return apply((ContractProvider)obj);
                }

    }

}
