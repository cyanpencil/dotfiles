// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multimaps.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingMultimap, Maps, Multimap, Multimaps, 
//            Multiset, Multisets

static class delegate extends ForwardingMultimap
    implements Serializable
{

            protected Multimap _mthdelegate()
            {
/* 505*/        return _flddelegate;
            }

            public void clear()
            {
/* 509*/        throw new UnsupportedOperationException();
            }

            public Map asMap()
            {
                Map map1;
/* 513*/        if((map1 = map) == null)
/* 515*/            map1 = map = Collections.unmodifiableMap(Maps.transformValues(_flddelegate.asMap(), new Function() {

                        public Collection apply(Collection collection)
                        {
/* 519*/                    return Multimaps.access$000(collection);
                        }

                        public volatile Object apply(Object obj)
                        {
/* 516*/                    return apply((Collection)obj);
                        }

                        final Multimaps.UnmodifiableMultimap this$0;

                    
                    {
/* 516*/                this$0 = Multimaps.UnmodifiableMultimap.this;
/* 516*/                super();
                    }
            }));
/* 523*/        return map1;
            }

            public Collection entries()
            {
                Collection collection;
/* 527*/        if((collection = entries) == null)
/* 529*/            entries = collection = Multimaps.access$100(_flddelegate.entries());
/* 531*/        return collection;
            }

            public Collection get(Object obj)
            {
/* 535*/        return Multimaps.access$000(_flddelegate.get(obj));
            }

            public Multiset keys()
            {
                Multiset multiset;
/* 539*/        if((multiset = keys) == null)
/* 541*/            keys = multiset = Multisets.unmodifiableMultiset(_flddelegate.keys());
/* 543*/        return multiset;
            }

            public Set keySet()
            {
                Set set;
/* 547*/        if((set = keySet) == null)
/* 549*/            keySet = set = Collections.unmodifiableSet(_flddelegate.keySet());
/* 551*/        return set;
            }

            public boolean put(Object obj, Object obj1)
            {
/* 555*/        throw new UnsupportedOperationException();
            }

            public boolean putAll(Object obj, Iterable iterable)
            {
/* 559*/        throw new UnsupportedOperationException();
            }

            public boolean putAll(Multimap multimap)
            {
/* 564*/        throw new UnsupportedOperationException();
            }

            public boolean remove(Object obj, Object obj1)
            {
/* 568*/        throw new UnsupportedOperationException();
            }

            public Collection removeAll(Object obj)
            {
/* 572*/        throw new UnsupportedOperationException();
            }

            public Collection replaceValues(Object obj, Iterable iterable)
            {
/* 577*/        throw new UnsupportedOperationException();
            }

            public Collection values()
            {
                Collection collection;
/* 581*/        if((collection = values) == null)
/* 583*/            values = collection = Collections.unmodifiableCollection(_flddelegate.values());
/* 585*/        return collection;
            }

            protected volatile Object _mthdelegate()
            {
/* 491*/        return _mthdelegate();
            }

            final Multimap _flddelegate;
            transient Collection entries;
            transient Multiset keys;
            transient Set keySet;
            transient Collection values;
            transient Map map;

            _cls1.this._cls0(Multimap multimap)
            {
/* 501*/        _flddelegate = (Multimap)Preconditions.checkNotNull(multimap);
            }
}
