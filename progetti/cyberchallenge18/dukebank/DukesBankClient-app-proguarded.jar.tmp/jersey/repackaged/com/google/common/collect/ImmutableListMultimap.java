// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableListMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableMultimap, EmptyImmutableListMultimap, ImmutableList, ImmutableMap, 
//            ListMultimap, Multimap, ImmutableCollection

public class ImmutableListMultimap extends ImmutableMultimap
    implements ListMultimap
{
    public static final class Builder extends ImmutableMultimap.Builder
    {

                public final Builder put(Object obj, Object obj1)
                {
/* 167*/            super.put(obj, obj1);
/* 168*/            return this;
                }

                public final ImmutableListMultimap build()
                {
/* 224*/            return (ImmutableListMultimap)super.build();
                }

                public final volatile ImmutableMultimap build()
                {
/* 158*/            return build();
                }

                public final volatile ImmutableMultimap.Builder put(Object obj, Object obj1)
                {
/* 158*/            return put(obj, obj1);
                }

                public Builder()
                {
                }
    }


            public static ImmutableListMultimap of()
            {
/*  64*/        return EmptyImmutableListMultimap.INSTANCE;
            }

            public static Builder builder()
            {
/* 137*/        return new Builder();
            }

            public static ImmutableListMultimap copyOf(Multimap multimap)
            {
/* 242*/        if(multimap.isEmpty())
/* 243*/            return of();
                Object obj;
/* 247*/        if((multimap instanceof ImmutableListMultimap) && !((ImmutableListMultimap) (obj = (ImmutableListMultimap)multimap)).isPartialView())
/* 252*/            return ((ImmutableListMultimap) (obj));
/* 256*/        obj = ImmutableMap.builder();
/* 257*/        int i = 0;
/* 260*/        multimap = multimap.asMap().entrySet().iterator();
/* 260*/        do
                {
/* 260*/            if(!multimap.hasNext())
/* 260*/                break;
                    java.util.Map.Entry entry;
                    ImmutableList immutablelist;
/* 260*/            if(!(immutablelist = ImmutableList.copyOf((Collection)(entry = (java.util.Map.Entry)multimap.next()).getValue())).isEmpty())
                    {
/* 263*/                ((ImmutableMap.Builder) (obj)).put(entry.getKey(), immutablelist);
/* 264*/                i += immutablelist.size();
                    }
                } while(true);
/* 268*/        return new ImmutableListMultimap(((ImmutableMap.Builder) (obj)).build(), i);
            }

            ImmutableListMultimap(ImmutableMap immutablemap, int i)
            {
/* 272*/        super(immutablemap, i);
            }

            public ImmutableList get(Object obj)
            {
/* 285*/        if((obj = (ImmutableList)map.get(obj)) == null)
/* 286*/            return ImmutableList.of();
/* 286*/        else
/* 286*/            return ((ImmutableList) (obj));
            }

            /**
             * @deprecated Method removeAll is deprecated
             */

            public ImmutableList removeAll(Object obj)
            {
/* 324*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method replaceValues is deprecated
             */

            public ImmutableList replaceValues(Object obj, Iterable iterable)
            {
/* 335*/        throw new UnsupportedOperationException();
            }

            public volatile ImmutableCollection get(Object obj)
            {
/*  55*/        return get(obj);
            }

            public volatile ImmutableCollection replaceValues(Object obj, Iterable iterable)
            {
/*  55*/        return replaceValues(obj, iterable);
            }

            public volatile ImmutableCollection removeAll(Object obj)
            {
/*  55*/        return removeAll(obj);
            }

            public volatile Collection replaceValues(Object obj, Iterable iterable)
            {
/*  55*/        return replaceValues(obj, iterable);
            }

            public volatile Collection get(Object obj)
            {
/*  55*/        return get(obj);
            }

            public volatile Collection removeAll(Object obj)
            {
/*  55*/        return removeAll(obj);
            }

            public volatile List replaceValues(Object obj, Iterable iterable)
            {
/*  55*/        return replaceValues(obj, iterable);
            }

            public volatile List removeAll(Object obj)
            {
/*  55*/        return removeAll(obj);
            }

            public volatile List get(Object obj)
            {
/*  55*/        return get(obj);
            }
}
