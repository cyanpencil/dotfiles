// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingCollection, Maps, UnmodifiableIterator

static class entries extends ForwardingCollection
{

            protected Collection _mthdelegate()
            {
/*1256*/        return entries;
            }

            public Iterator iterator()
            {
/*1260*/        final Iterator delegate = super.iterator();
/*1261*/        return new UnmodifiableIterator() {

                    public boolean hasNext()
                    {
/*1264*/                return delegate.hasNext();
                    }

                    public java.util.Map.Entry next()
                    {
/*1268*/                return Maps.unmodifiableEntry((java.util.Map.Entry)delegate.next());
                    }

                    public volatile Object next()
                    {
/*1261*/                return next();
                    }

                    final Iterator val$delegate;
                    final Maps.UnmodifiableEntries this$0;

                    
                    {
/*1261*/                this$0 = Maps.UnmodifiableEntries.this;
/*1261*/                delegate = iterator1;
/*1261*/                super();
                    }
        };
            }

            public Object[] toArray()
            {
/*1276*/        return standardToArray();
            }

            public Object[] toArray(Object aobj[])
            {
/*1280*/        return standardToArray(aobj);
            }

            protected volatile Object _mthdelegate()
            {
/*1247*/        return _mthdelegate();
            }

            private final Collection entries;

            t>(Collection collection)
            {
/*1252*/        entries = collection;
            }
}
