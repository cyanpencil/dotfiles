// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableMultiset.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            UnmodifiableIterator, ImmutableMultiset, Multiset

class  extends UnmodifiableIterator
{

            public boolean hasNext()
            {
/* 246*/        return remaining > 0 || val$entryIterator.hasNext();
            }

            public Object next()
            {
/* 251*/        if(remaining <= 0)
                {
/* 252*/            val.entryIterator entryiterator = (remaining)val$entryIterator.next();
/* 253*/            element = entryiterator.ement();
/* 254*/            remaining = entryiterator.unt();
                }
/* 256*/        remaining--;
/* 257*/        return element;
            }

            int remaining;
            Object element;
            final Iterator val$entryIterator;
            final ImmutableMultiset this$0;

            ()
            {
/* 240*/        this$0 = final_immutablemultiset;
/* 240*/        val$entryIterator = Iterator.this;
/* 240*/        super();
            }
}
