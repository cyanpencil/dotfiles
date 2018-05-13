// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ContractProvider.java

package org.glassfish.jersey.model;

import java.util.*;
import javax.inject.Singleton;
import jersey.repackaged.com.google.common.collect.Maps;
import jersey.repackaged.com.google.common.collect.Sets;

// Referenced classes of package org.glassfish.jersey.model:
//            ContractProvider

public static final class _cls00
{

            public final _cls00 scope(Class class1)
            {
/* 117*/        scope = class1;
/* 118*/        return this;
            }

            public final scope addContract(Class class1)
            {
/* 128*/        return addContract(class1, defaultPriority);
            }

            public final defaultPriority addContract(Class class1, int i)
            {
/* 139*/        contracts.put(class1, Integer.valueOf(i));
/* 140*/        return this;
            }

            public final contracts addContracts(Map map)
            {
/* 150*/        contracts.putAll(map);
/* 151*/        return this;
            }

            public final contracts addContracts(Collection collection)
            {
                Class class1;
/* 161*/        for(collection = collection.iterator(); collection.hasNext(); addContract(class1, defaultPriority))
/* 161*/            class1 = (Class)collection.next();

/* 164*/        return this;
            }

            public final defaultPriority defaultPriority(int i)
            {
/* 174*/        defaultPriority = i;
/* 175*/        return this;
            }

            public final defaultPriority addNameBinding(Class class1)
            {
/* 185*/        nameBindings.add(class1);
/* 186*/        return this;
            }

            public final Class getScope()
            {
/* 196*/        return scope;
            }

            public final Map getContracts()
            {
/* 205*/        return contracts;
            }

            public final int getDefaultPriority()
            {
/* 214*/        return defaultPriority;
            }

            public final Set getNameBindings()
            {
/* 223*/        return nameBindings;
            }

            public final ContractProvider build()
            {
/* 232*/        if(scope == null)
/* 233*/            scope = javax/inject/Singleton;
/* 236*/        Map map = contracts.isEmpty() ? Collections.emptyMap() : Maps.transformEntries(contracts, new jersey.repackaged.com.google.common.collect.Maps.EntryTransformer() {

                    public Integer transformEntry(Class class1, Integer integer)
                    {
/* 241*/                return Integer.valueOf(integer.intValue() == -1 ? defaultPriority : integer.intValue());
                    }

                    public volatile Object transformEntry(Object obj, Object obj1)
                    {
/* 238*/                return transformEntry((Class)obj, (Integer)obj1);
                    }

                    final ContractProvider.Builder this$0;

                    
                    {
/* 238*/                this$0 = ContractProvider.Builder.this;
/* 238*/                super();
                    }
        });
/* 245*/        Set set = nameBindings.isEmpty() ? Collections.emptySet() : Collections.unmodifiableSet(nameBindings);
/* 248*/        if(scope == javax/inject/Singleton && map.isEmpty() && defaultPriority == -1 && set.isEmpty())
/* 249*/            return EMPTY_MODEL;
/* 252*/        else
/* 252*/            return new ContractProvider(scope, map, defaultPriority, set);
            }

            private static final ContractProvider EMPTY_MODEL = new ContractProvider(javax/inject/Singleton, Collections.emptyMap(), -1, Collections.emptySet());
            private Class scope;
            private Map contracts;
            private int defaultPriority;
            private Set nameBindings;



            private _cls1.this._cls0()
            {
/*  95*/        scope = null;
/*  96*/        contracts = Maps.newHashMap();
/*  97*/        defaultPriority = -1;
/*  98*/        nameBindings = Sets.newIdentityHashSet();
            }

            private IdentityHashSet(ContractProvider contractprovider)
            {
/*  95*/        scope = null;
/*  96*/        contracts = Maps.newHashMap();
/*  97*/        defaultPriority = -1;
/*  98*/        nameBindings = Sets.newIdentityHashSet();
/* 104*/        scope = ContractProvider.access$300(contractprovider);
/* 105*/        contracts.putAll(ContractProvider.access$400(contractprovider));
/* 106*/        defaultPriority = ContractProvider.access$500(contractprovider);
/* 107*/        nameBindings.addAll(ContractProvider.access$600(contractprovider));
            }


}
