// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterators.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Iterators, PeekingIterator

static class l
    implements PeekingIterator
{

            public boolean hasNext()
            {
/*1149*/        return hasPeeked || iterator.hasNext();
            }

            public Object next()
            {
/*1154*/        if(!hasPeeked)
                {
/*1155*/            return iterator.next();
                } else
                {
/*1157*/            Object obj = peekedElement;
/*1158*/            hasPeeked = false;
/*1159*/            peekedElement = null;
/*1160*/            return obj;
                }
            }

            public void remove()
            {
/*1165*/        Preconditions.checkState(!hasPeeked, "Can't remove after you've peeked at next");
/*1166*/        iterator.remove();
            }

            public Object peek()
            {
/*1171*/        if(!hasPeeked)
                {
/*1172*/            peekedElement = iterator.next();
/*1173*/            hasPeeked = true;
                }
/*1175*/        return peekedElement;
            }

            private final Iterator iterator;
            private boolean hasPeeked;
            private Object peekedElement;

            public (Iterator iterator1)
            {
/*1144*/        iterator = (Iterator)Preconditions.checkNotNull(iterator1);
            }
}
