// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FilteredKeyMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingList, FilteredKeyMultimap

static class key extends ForwardingList
{

            public boolean add(Object obj)
            {
/* 144*/        add(0, obj);
/* 145*/        return true;
            }

            public boolean addAll(Collection collection)
            {
/* 150*/        addAll(0, collection);
/* 151*/        return true;
            }

            public void add(int i, Object obj)
            {
/* 156*/        Preconditions.checkPositionIndex(i, 0);
/* 157*/        i = String.valueOf(String.valueOf(key));
/* 157*/        throw new IllegalArgumentException((new StringBuilder(32 + i.length())).append("Key does not satisfy predicate: ").append(i).toString());
            }

            public boolean addAll(int i, Collection collection)
            {
/* 162*/        Preconditions.checkNotNull(collection);
/* 163*/        Preconditions.checkPositionIndex(i, 0);
/* 164*/        i = String.valueOf(String.valueOf(key));
/* 164*/        throw new IllegalArgumentException((new StringBuilder(32 + i.length())).append("Key does not satisfy predicate: ").append(i).toString());
            }

            protected List _mthdelegate()
            {
/* 169*/        return Collections.emptyList();
            }

            protected volatile Collection _mthdelegate()
            {
/* 135*/        return _mthdelegate();
            }

            protected volatile Object _mthdelegate()
            {
/* 135*/        return _mthdelegate();
            }

            final Object key;

            (Object obj)
            {
/* 139*/        key = obj;
            }
}
