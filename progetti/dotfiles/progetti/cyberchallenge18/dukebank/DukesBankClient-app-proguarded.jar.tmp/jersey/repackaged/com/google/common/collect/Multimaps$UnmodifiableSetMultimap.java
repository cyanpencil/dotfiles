// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multimaps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Maps, Multimaps, SetMultimap, Multimap

static class it> extends it>
    implements SetMultimap
{

            public SetMultimap _mthdelegate()
            {
/* 618*/        return (SetMultimap)super.egate();
            }

            public Set get(Object obj)
            {
/* 625*/        return Collections.unmodifiableSet(_mthdelegate().get(obj));
            }

            public Set entries()
            {
/* 628*/        return Maps.unmodifiableEntrySet(_mthdelegate().entries());
            }

            public Set removeAll(Object obj)
            {
/* 631*/        throw new UnsupportedOperationException();
            }

            public Set replaceValues(Object obj, Iterable iterable)
            {
/* 635*/        throw new UnsupportedOperationException();
            }

            public volatile Collection replaceValues(Object obj, Iterable iterable)
            {
/* 612*/        return replaceValues(obj, iterable);
            }

            public volatile Collection removeAll(Object obj)
            {
/* 612*/        return removeAll(obj);
            }

            public volatile Collection get(Object obj)
            {
/* 612*/        return get(obj);
            }

            public volatile Collection entries()
            {
/* 612*/        return entries();
            }

            public volatile Multimap _mthdelegate()
            {
/* 612*/        return _mthdelegate();
            }

            public volatile Object _mthdelegate()
            {
/* 612*/        return _mthdelegate();
            }

            (SetMultimap setmultimap)
            {
/* 615*/        super(setmultimap);
            }
}
