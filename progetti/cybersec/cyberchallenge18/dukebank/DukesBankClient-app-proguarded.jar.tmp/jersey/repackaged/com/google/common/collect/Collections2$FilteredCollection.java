// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Collections2.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Collections2, Iterables, Iterators, Lists

static class predicate extends AbstractCollection
{

            predicate createCombined(Predicate predicate1)
            {
/* 145*/        return new <init>(unfiltered, Predicates.and(predicate, predicate1));
            }

            public boolean add(Object obj)
            {
/* 152*/        Preconditions.checkArgument(predicate.apply(obj));
/* 153*/        return unfiltered.add(obj);
            }

            public boolean addAll(Collection collection)
            {
                Object obj;
/* 158*/        for(Iterator iterator1 = collection.iterator(); iterator1.hasNext(); Preconditions.checkArgument(predicate.apply(obj)))
/* 158*/            obj = iterator1.next();

/* 161*/        return unfiltered.addAll(collection);
            }

            public void clear()
            {
/* 166*/        Iterables.removeIf(unfiltered, predicate);
            }

            public boolean contains(Object obj)
            {
/* 171*/        if(Collections2.safeContains(unfiltered, obj))
                {
/* 173*/            obj = obj;
/* 174*/            return predicate.apply(obj);
                } else
                {
/* 176*/            return false;
                }
            }

            public boolean containsAll(Collection collection)
            {
/* 181*/        return Collections2.containsAllImpl(this, collection);
            }

            public boolean isEmpty()
            {
/* 186*/        return !Iterables.any(unfiltered, predicate);
            }

            public Iterator iterator()
            {
/* 191*/        return Iterators.filter(unfiltered.iterator(), predicate);
            }

            public boolean remove(Object obj)
            {
/* 196*/        return contains(obj) && unfiltered.remove(obj);
            }

            public boolean removeAll(Collection collection)
            {
/* 201*/        return Iterables.removeIf(unfiltered, Predicates.and(predicate, Predicates.in(collection)));
            }

            public boolean retainAll(Collection collection)
            {
/* 206*/        return Iterables.removeIf(unfiltered, Predicates.and(predicate, Predicates.not(Predicates.in(collection))));
            }

            public int size()
            {
/* 211*/        return Iterators.size(iterator());
            }

            public Object[] toArray()
            {
/* 217*/        return Lists.newArrayList(iterator()).toArray();
            }

            public Object[] toArray(Object aobj[])
            {
/* 222*/        return Lists.newArrayList(iterator()).toArray(aobj);
            }

            final Collection unfiltered;
            final Predicate predicate;

            Q(Collection collection, Predicate predicate1)
            {
/* 140*/        unfiltered = collection;
/* 141*/        predicate = predicate1;
            }
}
