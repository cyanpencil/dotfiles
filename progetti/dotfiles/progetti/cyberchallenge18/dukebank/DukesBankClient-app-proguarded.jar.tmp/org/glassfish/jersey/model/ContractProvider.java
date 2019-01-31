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
//            NameBound, Scoped

public final class ContractProvider
    implements NameBound, Scoped
{
    public static final class Builder
    {

                public final Builder scope(Class class1)
                {
/* 117*/            scope = class1;
/* 118*/            return this;
                }

                public final Builder addContract(Class class1)
                {
/* 128*/            return addContract(class1, defaultPriority);
                }

                public final Builder addContract(Class class1, int i)
                {
/* 139*/            contracts.put(class1, Integer.valueOf(i));
/* 140*/            return this;
                }

                public final Builder addContracts(Map map)
                {
/* 150*/            contracts.putAll(map);
/* 151*/            return this;
                }

                public final Builder addContracts(Collection collection)
                {
                    Class class1;
/* 161*/            for(collection = collection.iterator(); collection.hasNext(); addContract(class1, defaultPriority))
/* 161*/                class1 = (Class)collection.next();

/* 164*/            return this;
                }

                public final Builder defaultPriority(int i)
                {
/* 174*/            defaultPriority = i;
/* 175*/            return this;
                }

                public final Builder addNameBinding(Class class1)
                {
/* 185*/            nameBindings.add(class1);
/* 186*/            return this;
                }

                public final Class getScope()
                {
/* 196*/            return scope;
                }

                public final Map getContracts()
                {
/* 205*/            return contracts;
                }

                public final int getDefaultPriority()
                {
/* 214*/            return defaultPriority;
                }

                public final Set getNameBindings()
                {
/* 223*/            return nameBindings;
                }

                public final ContractProvider build()
                {
/* 232*/            if(scope == null)
/* 233*/                scope = javax/inject/Singleton;
/* 236*/            Map map = contracts.isEmpty() ? Collections.emptyMap() : Maps.transformEntries(contracts, new jersey.repackaged.com.google.common.collect.Maps.EntryTransformer() {

                        public Integer transformEntry(Class class1, Integer integer)
                        {
/* 241*/                    return Integer.valueOf(integer.intValue() == -1 ? defaultPriority : integer.intValue());
                        }

                        public volatile Object transformEntry(Object obj, Object obj1)
                        {
/* 238*/                    return transformEntry((Class)obj, (Integer)obj1);
                        }

                        final Builder this$0;

                        
                        {
/* 238*/                    this$0 = Builder.this;
/* 238*/                    super();
                        }
            });
/* 245*/            Set set = nameBindings.isEmpty() ? Collections.emptySet() : Collections.unmodifiableSet(nameBindings);
/* 248*/            if(scope == javax/inject/Singleton && map.isEmpty() && defaultPriority == -1 && set.isEmpty())
/* 249*/                return EMPTY_MODEL;
/* 252*/            else
/* 252*/                return new ContractProvider(scope, map, defaultPriority, set);
                }

                private static final ContractProvider EMPTY_MODEL = new ContractProvider(javax/inject/Singleton, Collections.emptyMap(), -1, Collections.emptySet());
                private Class scope;
                private Map contracts;
                private int defaultPriority;
                private Set nameBindings;



                private Builder()
                {
/*  95*/            scope = null;
/*  96*/            contracts = Maps.newHashMap();
/*  97*/            defaultPriority = -1;
/*  98*/            nameBindings = Sets.newIdentityHashSet();
                }

                private Builder(ContractProvider contractprovider)
                {
/*  95*/            scope = null;
/*  96*/            contracts = Maps.newHashMap();
/*  97*/            defaultPriority = -1;
/*  98*/            nameBindings = Sets.newIdentityHashSet();
/* 104*/            scope = contractprovider.scope;
/* 105*/            contracts.putAll(contractprovider.contracts);
/* 106*/            defaultPriority = contractprovider.defaultPriority;
/* 107*/            nameBindings.addAll(contractprovider.nameBindings);
                }


    }


            public static Builder builder()
            {
/*  71*/        return new Builder();
            }

            public static Builder builder(ContractProvider contractprovider)
            {
/*  81*/        return new Builder(contractprovider);
            }

            private ContractProvider(Class class1, Map map, int i, Set set)
            {
/* 267*/        scope = class1;
/* 268*/        contracts = map;
/* 269*/        defaultPriority = i;
/* 270*/        nameBindings = set;
            }

            public final Class getScope()
            {
/* 275*/        return scope;
            }

            public final Set getContracts()
            {
/* 286*/        return contracts.keySet();
            }

            public final Map getContractMap()
            {
/* 295*/        return contracts;
            }

            public final boolean isNameBound()
            {
/* 300*/        return !nameBindings.isEmpty();
            }

            public final int getPriority(Class class1)
            {
/* 312*/        if(contracts.containsKey(class1))
/* 313*/            return ((Integer)contracts.get(class1)).intValue();
/* 315*/        else
/* 315*/            return defaultPriority;
            }

            public final Set getNameBindings()
            {
/* 320*/        return nameBindings;
            }

            public final volatile Collection getNameBindings()
            {
/*  59*/        return getNameBindings();
            }


            public static final int NO_PRIORITY = -1;
            private final Map contracts;
            private final int defaultPriority;
            private final Set nameBindings;
            private final Class scope;




}
