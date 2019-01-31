// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterables.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Collections2, Iterators, Lists, ObjectArrays, 
//            FluentIterable

public final class Iterables
{

            public static boolean removeIf(Iterable iterable, Predicate predicate)
            {
/* 176*/        if((iterable instanceof RandomAccess) && (iterable instanceof List))
/* 177*/            return removeIfFromRandomAccessList((List)iterable, (Predicate)Preconditions.checkNotNull(predicate));
/* 180*/        else
/* 180*/            return Iterators.removeIf(iterable.iterator(), predicate);
            }

            private static boolean removeIfFromRandomAccessList(List list, Predicate predicate)
            {
/* 187*/        int i = 0;
/* 188*/        int j = 0;
/* 190*/        for(; i < list.size(); i++)
                {
/* 191*/            Object obj = list.get(i);
/* 192*/            if(predicate.apply(obj))
/* 193*/                continue;
/* 193*/            if(i > j)
/* 195*/                try
                        {
/* 195*/                    list.set(j, obj);
                        }
/* 196*/                catch(UnsupportedOperationException _ex)
                        {
/* 197*/                    slowRemoveIfForRemainingElements(list, predicate, j, i);
/* 198*/                    return true;
                        }
/* 201*/            j++;
                }

/* 206*/        list.subList(j, list.size()).clear();
/* 207*/        return i != j;
            }

            private static void slowRemoveIfForRemainingElements(List list, Predicate predicate, int i, int j)
            {
/* 222*/        for(int k = list.size() - 1; k > j; k--)
/* 223*/            if(predicate.apply(list.get(k)))
/* 224*/                list.remove(k);

/* 228*/        for(int l = j - 1; l >= i; l--)
/* 229*/            list.remove(l);

            }

            static Object removeFirstMatching(Iterable iterable, Predicate predicate)
            {
/* 238*/        Preconditions.checkNotNull(predicate);
/* 239*/        for(iterable = iterable.iterator(); iterable.hasNext();)
                {
/* 241*/            Object obj = iterable.next();
/* 242*/            if(predicate.apply(obj))
                    {
/* 243*/                iterable.remove();
/* 244*/                return obj;
                    }
                }

/* 247*/        return null;
            }

            public static String toString(Iterable iterable)
            {
/* 278*/        return Iterators.toString(iterable.iterator());
            }

            public static Object getOnlyElement(Iterable iterable)
            {
/* 289*/        return Iterators.getOnlyElement(iterable.iterator());
            }

            public static Object[] toArray(Iterable iterable, Class class1)
            {
/* 315*/        iterable = toCollection(iterable);
/* 316*/        class1 = ((Class) (ObjectArrays.newArray(class1, iterable.size())));
/* 317*/        return iterable.toArray(class1);
            }

            static Object[] toArray(Iterable iterable)
            {
/* 328*/        return toCollection(iterable).toArray();
            }

            private static Collection toCollection(Iterable iterable)
            {
/* 337*/        if(iterable instanceof Collection)
/* 337*/            return (Collection)iterable;
/* 337*/        else
/* 337*/            return Lists.newArrayList(iterable.iterator());
            }

            public static boolean addAll(Collection collection, Iterable iterable)
            {
/* 350*/        if(iterable instanceof Collection)
                {
/* 351*/            iterable = Collections2.cast(iterable);
/* 352*/            return collection.addAll(iterable);
                } else
                {
/* 354*/            return Iterators.addAll(collection, ((Iterable)Preconditions.checkNotNull(iterable)).iterator());
                }
            }

            public static boolean any(Iterable iterable, Predicate predicate)
            {
/* 623*/        return Iterators.any(iterable.iterator(), predicate);
            }

            public static boolean all(Iterable iterable, Predicate predicate)
            {
/* 632*/        return Iterators.all(iterable.iterator(), predicate);
            }

            public static Iterable transform(Iterable iterable, Function function)
            {
/* 708*/        Preconditions.checkNotNull(iterable);
/* 709*/        Preconditions.checkNotNull(function);
/* 710*/        return new FluentIterable(iterable, function) {

                    public final Iterator iterator()
                    {
/* 713*/                return Iterators.transform(fromIterable.iterator(), function);
                    }

                    final Iterable val$fromIterable;
                    final Function val$function;

                    
                    {
/* 710*/                fromIterable = iterable;
/* 710*/                function = function1;
/* 710*/                super();
                    }
        };
            }

            public static Object getFirst(Iterable iterable, Object obj)
            {
/* 775*/        return Iterators.getNext(iterable.iterator(), obj);
            }
}
