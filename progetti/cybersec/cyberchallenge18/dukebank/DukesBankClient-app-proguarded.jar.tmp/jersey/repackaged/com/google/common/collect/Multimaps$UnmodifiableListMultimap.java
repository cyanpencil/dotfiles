// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multimaps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ListMultimap, Multimaps, Multimap

static class t> extends t>
    implements ListMultimap
{

            public ListMultimap _mthdelegate()
            {
/* 597*/        return (ListMultimap)super.gate();
            }

            public List get(Object obj)
            {
/* 600*/        return Collections.unmodifiableList(_mthdelegate().get(obj));
            }

            public List removeAll(Object obj)
            {
/* 603*/        throw new UnsupportedOperationException();
            }

            public List replaceValues(Object obj, Iterable iterable)
            {
/* 607*/        throw new UnsupportedOperationException();
            }

            public volatile Collection replaceValues(Object obj, Iterable iterable)
            {
/* 591*/        return replaceValues(obj, iterable);
            }

            public volatile Collection removeAll(Object obj)
            {
/* 591*/        return removeAll(obj);
            }

            public volatile Collection get(Object obj)
            {
/* 591*/        return get(obj);
            }

            public volatile Multimap _mthdelegate()
            {
/* 591*/        return _mthdelegate();
            }

            public volatile Object _mthdelegate()
            {
/* 591*/        return _mthdelegate();
            }

            (ListMultimap listmultimap)
            {
/* 594*/        super(listmultimap);
            }
}
