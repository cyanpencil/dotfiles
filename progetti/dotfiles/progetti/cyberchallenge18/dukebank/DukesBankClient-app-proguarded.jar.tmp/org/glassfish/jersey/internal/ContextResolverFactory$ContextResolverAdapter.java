// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ContextResolverFactory.java

package org.glassfish.jersey.internal;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.ext.ContextResolver;

// Referenced classes of package org.glassfish.jersey.internal:
//            ContextResolverFactory

static final class cra
    implements ContextResolver
{

            public final Object getContext(Class class1)
            {
                ContextResolver acontextresolver[];
/* 173*/        int i = (acontextresolver = cra).length;
/* 173*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/* 173*/            if((obj = ((ContextResolver) (obj = acontextresolver[j])).getContext(class1)) != null)
/* 176*/                return obj;
                }

/* 179*/        return null;
            }

            final ContextResolver reduce()
            {
/* 183*/        if(cra.length == 0)
/* 184*/            return ContextResolverFactory.access$100();
/* 186*/        if(cra.length == 1)
/* 187*/            return cra[0];
/* 189*/        else
/* 189*/            return this;
            }

            private static transient List removeNull(ContextResolver acontextresolver[])
            {
/* 194*/        ArrayList arraylist = new ArrayList(acontextresolver.length);
/* 195*/        int i = (acontextresolver = acontextresolver).length;
/* 195*/        for(int j = 0; j < i; j++)
                {
                    ContextResolver contextresolver;
/* 195*/            if((contextresolver = acontextresolver[j]) != null)
/* 197*/                arraylist.add(contextresolver);
                }

/* 200*/        return arraylist;
            }

            private final ContextResolver cra[];

            transient ter(ContextResolver acontextresolver[])
            {
/* 164*/        this(removeNull(acontextresolver));
            }

            removeNull(List list)
            {
/* 168*/        cra = (ContextResolver[])list.toArray(new ContextResolver[list.size()]);
            }
}
