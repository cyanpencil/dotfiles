// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableMultiset.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import java.util.Set;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableSet, ImmutableList, ImmutableMultiset, Multiset, 
//            UnmodifiableIterator, ImmutableAsList, ImmutableCollection

final class this._cls0 extends ImmutableSet
{

            final boolean isPartialView()
            {
/* 359*/        return ImmutableMultiset.this.isPartialView();
            }

            public final UnmodifiableIterator iterator()
            {
/* 364*/        return asList().iterator();
            }

            final ImmutableList createAsList()
            {
/* 369*/        return new ImmutableAsList() {

                    public Multiset.Entry get(int i)
                    {
/* 372*/                return getEntry(i);
                    }

                    ImmutableCollection delegateCollection()
                    {
/* 377*/                return ImmutableMultiset.EntrySet.this;
                    }

                    public volatile Object get(int i)
                    {
/* 369*/                return get(i);
                    }

                    final ImmutableMultiset.EntrySet this$1;

                    
                    {
/* 369*/                this$1 = ImmutableMultiset.EntrySet.this;
/* 369*/                super();
                    }
        };
            }

            public final int size()
            {
/* 384*/        return elementSet().size();
            }

            public final boolean contains(Object obj)
            {
/* 389*/        if(obj instanceof t)
                {
/* 390*/            if(((t) (obj = (t)obj)).t() <= 0)
/* 392*/                return false;
                    int i;
/* 394*/            return (i = count(((t) (obj)).t())) == ((t) (obj)).t();
                } else
                {
/* 397*/            return false;
                }
            }

            public final int hashCode()
            {
/* 402*/        return ImmutableMultiset.this.hashCode();
            }

            final Object writeReplace()
            {
/* 409*/        return new erializedForm(ImmutableMultiset.this);
            }

            public final volatile Iterator iterator()
            {
/* 356*/        return iterator();
            }

            final ImmutableMultiset this$0;

            private ()
            {
/* 356*/        this$0 = ImmutableMultiset.this;
/* 356*/        super();
            }


            // Unreferenced inner class jersey/repackaged/com/google/common/collect/ImmutableMultiset$1

/* anonymous class */
    class ImmutableMultiset._cls1 extends UnmodifiableIterator
    {

                public boolean hasNext()
                {
/* 246*/            return remaining > 0 || entryIterator.hasNext();
                }

                public Object next()
                {
/* 251*/            if(remaining <= 0)
                    {
/* 252*/                Multiset.Entry entry = (Multiset.Entry)entryIterator.next();
/* 253*/                element = entry.getElement();
/* 254*/                remaining = entry.getCount();
                    }
/* 256*/            remaining--;
/* 257*/            return element;
                }

                int remaining;
                Object element;
                final Iterator val$entryIterator;
                final ImmutableMultiset this$0;

                    
                    {
/* 240*/                this$0 = final_immutablemultiset;
/* 240*/                entryIterator = Iterator.this;
/* 240*/                super();
                    }
    }

}
