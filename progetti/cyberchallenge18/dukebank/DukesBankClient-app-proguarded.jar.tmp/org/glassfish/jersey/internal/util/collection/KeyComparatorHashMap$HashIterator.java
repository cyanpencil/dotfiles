// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   KeyComparatorHashMap.java

package org.glassfish.jersey.internal.util.collection;

import java.util.*;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            KeyComparatorHashMap

abstract class index
    implements Iterator
{

            public boolean hasNext()
            {
/* 828*/        return next != null;
            }

            next nextEntry()
            {
/* 832*/        if(modCount != expectedModCount)
/* 833*/            throw new ConcurrentModificationException();
                next next1;
/* 835*/        if((next1 = next) == null)
/* 837*/            throw new NoSuchElementException();
/* 840*/        next next2 = next1.next;
/* 841*/        next anext[] = table;
                int i;
/* 842*/        for(i = index; next2 == null && i > 0; next2 = anext[--i]);
/* 846*/        index = i;
/* 847*/        next = next2;
/* 848*/        return current = next1;
            }

            public void remove()
            {
/* 853*/        if(current == null)
/* 854*/            throw new IllegalStateException();
/* 856*/        if(modCount != expectedModCount)
                {
/* 857*/            throw new ConcurrentModificationException();
                } else
                {
/* 859*/            Object obj = current.current;
/* 860*/            current = null;
/* 861*/            removeEntryForKey(obj);
/* 862*/            expectedModCount = modCount;
/* 863*/            return;
                }
            }

            expectedModCount next;
            int expectedModCount;
            int index;
            expectedModCount current;
            final KeyComparatorHashMap this$0;

            ()
            {
/* 812*/        this$0 = KeyComparatorHashMap.this;
/* 812*/        super();
/* 813*/        expectedModCount = modCount;
                 a[];
/* 814*/        int i = (a = table).length;
/* 816*/          = null;
/* 817*/        if(size != 0)
/* 819*/            while(i > 0 && ( = a[--i]) == null) ;
/* 822*/        next = ;
/* 823*/        index = i;
            }
}
