// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multisets.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            CollectPreconditions, Multiset, Multisets

static final class entryIterator
    implements Iterator
{

            public final boolean hasNext()
            {
/*1032*/        return laterCount > 0 || entryIterator.hasNext();
            }

            public final Object next()
            {
/*1037*/        if(!hasNext())
/*1038*/            throw new NoSuchElementException();
/*1040*/        if(laterCount == 0)
                {
/*1041*/            currentEntry = (currentEntry)entryIterator.next();
/*1042*/            totalCount = laterCount = currentEntry.currentEntry();
                }
/*1044*/        laterCount--;
/*1045*/        canRemove = true;
/*1046*/        return currentEntry.currentEntry();
            }

            public final void remove()
            {
/*1051*/        CollectPreconditions.checkRemove(canRemove);
/*1052*/        if(totalCount == 1)
/*1053*/            entryIterator.remove();
/*1055*/        else
/*1055*/            multiset.remove(currentEntry.currentEntry());
/*1057*/        totalCount--;
/*1058*/        canRemove = false;
            }

            private final Multiset multiset;
            private final Iterator entryIterator;
            private canRemove currentEntry;
            private int laterCount;
            private int totalCount;
            private boolean canRemove;

            (Multiset multiset1, Iterator iterator)
            {
/*1026*/        multiset = multiset1;
/*1027*/        entryIterator = iterator;
            }
}
