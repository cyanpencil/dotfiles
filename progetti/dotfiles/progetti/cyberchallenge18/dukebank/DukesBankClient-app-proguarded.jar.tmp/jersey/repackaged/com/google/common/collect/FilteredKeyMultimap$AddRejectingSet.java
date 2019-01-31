// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FilteredKeyMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingSet, FilteredKeyMultimap

static class key extends ForwardingSet
{

            public boolean add(Object obj)
            {
/* 120*/        obj = String.valueOf(String.valueOf(key));
/* 120*/        throw new IllegalArgumentException((new StringBuilder(32 + ((String) (obj)).length())).append("Key does not satisfy predicate: ").append(((String) (obj))).toString());
            }

            public boolean addAll(Collection collection)
            {
/* 125*/        Preconditions.checkNotNull(collection);
/* 126*/        collection = String.valueOf(String.valueOf(key));
/* 126*/        throw new IllegalArgumentException((new StringBuilder(32 + collection.length())).append("Key does not satisfy predicate: ").append(collection).toString());
            }

            protected Set _mthdelegate()
            {
/* 131*/        return Collections.emptySet();
            }

            protected volatile Collection _mthdelegate()
            {
/* 111*/        return _mthdelegate();
            }

            protected volatile Object _mthdelegate()
            {
/* 111*/        return _mthdelegate();
            }

            final Object key;

            (Object obj)
            {
/* 115*/        key = obj;
            }
}
