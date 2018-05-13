// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Collections2, Maps

static class kNotNull extends stractMap
{

            Set backingSet()
            {
/* 760*/        return set;
            }

            public Set createKeySet()
            {
/* 770*/        return Maps.access$200(backingSet());
            }

            Collection createValues()
            {
/* 775*/        return Collections2.transform(set, function);
            }

            public int size()
            {
/* 780*/        return backingSet().size();
            }

            public boolean containsKey(Object obj)
            {
/* 785*/        return backingSet().contains(obj);
            }

            public Object get(Object obj)
            {
/* 790*/        if(Collections2.safeContains(backingSet(), obj))
                {
/* 792*/            obj = obj;
/* 793*/            return function.apply(obj);
                } else
                {
/* 795*/            return null;
                }
            }

            public Object remove(Object obj)
            {
/* 801*/        if(backingSet().remove(obj))
                {
/* 803*/            obj = obj;
/* 804*/            return function.apply(obj);
                } else
                {
/* 806*/            return null;
                }
            }

            public void clear()
            {
/* 812*/        backingSet().clear();
            }

            protected Set createEntrySet()
            {
/* 817*/        return new Maps.EntrySet() {

                    Map map()
                    {
/* 820*/                return Maps.AsMapView.this;
                    }

                    public Iterator iterator()
                    {
/* 825*/                return Maps.asMapEntryIterator(backingSet(), function);
                    }

                    final Maps.AsMapView this$0;

                    
                    {
/* 817*/                this$0 = Maps.AsMapView.this;
/* 817*/                super();
                    }
        };
            }

            private final Set set;
            final Function function;

            init>(Set set1, Function function1)
            {
/* 764*/        set = (Set)Preconditions.checkNotNull(set1);
/* 765*/        function = (Function)Preconditions.checkNotNull(function1);
            }
}
