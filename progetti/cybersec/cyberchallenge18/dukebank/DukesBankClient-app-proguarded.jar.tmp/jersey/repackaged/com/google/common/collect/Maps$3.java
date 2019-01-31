// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import java.util.Set;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingSet, Maps

static class ingSet extends ForwardingSet
{

            protected final Set _mthdelegate()
            {
/* 973*/        return val$set;
            }

            public final boolean add(Object obj)
            {
/* 978*/        throw new UnsupportedOperationException();
            }

            public final boolean addAll(Collection collection)
            {
/* 983*/        throw new UnsupportedOperationException();
            }

            protected final volatile Collection _mthdelegate()
            {
/* 970*/        return _mthdelegate();
            }

            protected final volatile Object _mthdelegate()
            {
/* 970*/        return _mthdelegate();
            }

            final Set val$set;

            ingSet(Set set1)
            {
/* 970*/        val$set = set1;
/* 970*/        super();
            }
}
