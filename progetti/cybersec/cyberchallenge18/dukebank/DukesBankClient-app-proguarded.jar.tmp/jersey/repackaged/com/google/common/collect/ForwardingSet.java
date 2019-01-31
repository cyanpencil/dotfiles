// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ForwardingSet.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import java.util.Set;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingCollection

public abstract class ForwardingSet extends ForwardingCollection
    implements Set
{

            protected ForwardingSet()
            {
            }

            protected abstract Set _mthdelegate();

            public boolean equals(Object obj)
            {
/*  59*/        return obj == this || _mthdelegate().equals(obj);
            }

            public int hashCode()
            {
/*  63*/        return _mthdelegate().hashCode();
            }

            protected volatile Collection _mthdelegate()
            {
/*  48*/        return _mthdelegate();
            }

            protected volatile Object _mthdelegate()
            {
/*  48*/        return _mthdelegate();
            }
}
