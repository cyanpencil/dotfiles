// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConcurrentHashMapV8.java

package org.glassfish.jersey.internal.util.collection;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            ConcurrentHashMapV8

static abstract class map
    implements Serializable, Collection
{

            public ConcurrentHashMapV8 getMap()
            {
/*2954*/        return map;
            }

            public final void clear()
            {
/*2962*/        map.clear();
            }

            public final int size()
            {
/*2966*/        return map.size();
            }

            public final boolean isEmpty()
            {
/*2970*/        return map.isEmpty();
            }

            public abstract Iterator iterator();

            public abstract boolean contains(Object obj);

            public abstract boolean remove(Object obj);

            public final Object[] toArray()
            {
                long l;
/*2993*/        if((l = map.mappingCount()) > 0x7ffffff7L)
/*2995*/            throw new OutOfMemoryError("Required array size too large");
/*2996*/        Object aobj[] = new Object[l = (int)l];
/*2998*/        int i = 0;
                Object obj;
/*2999*/        for(Iterator iterator1 = iterator(); iterator1.hasNext(); aobj[i++] = obj)
                {
/*2999*/            obj = iterator1.next();
/*3000*/            if(i != l)
/*3001*/                continue;
/*3001*/            if(l >= 0x7ffffff7)
/*3002*/                throw new OutOfMemoryError("Required array size too large");
/*3003*/            if(l >= 0x3ffffffb)
/*3004*/                l = 0x7ffffff7;
/*3006*/            else
/*3006*/                l += (l >>> 1) + 1;
/*3007*/            aobj = Arrays.copyOf(aobj, l);
                }

/*3011*/        if(i == l)
/*3011*/            return aobj;
/*3011*/        else
/*3011*/            return Arrays.copyOf(aobj, i);
            }

            public final Object[] toArray(Object aobj[])
            {
                long l;
/*3016*/        if((l = map.mappingCount()) > 0x7ffffff7L)
/*3018*/            throw new OutOfMemoryError("Required array size too large");
/*3019*/        l = (int)l;
                Object aobj1[];
/*3020*/        int i = (aobj1 = aobj.length < l ? (Object[])Array.newInstance(((Object) (aobj)).getClass().getComponentType(), l) : aobj).length;
/*3024*/        int j = 0;
                Object obj;
/*3025*/        for(Iterator iterator1 = iterator(); iterator1.hasNext(); aobj1[j++] = obj)
                {
/*3025*/            obj = iterator1.next();
/*3026*/            if(j != i)
/*3027*/                continue;
/*3027*/            if(i >= 0x7ffffff7)
/*3028*/                throw new OutOfMemoryError("Required array size too large");
/*3029*/            if(i >= 0x3ffffffb)
/*3030*/                i = 0x7ffffff7;
/*3032*/            else
/*3032*/                i += (i >>> 1) + 1;
/*3033*/            aobj1 = Arrays.copyOf(aobj1, i);
                }

/*3037*/        if(aobj == aobj1 && j < i)
                {
/*3038*/            aobj1[j] = null;
/*3039*/            return aobj1;
                }
/*3041*/        if(j == i)
/*3041*/            return aobj1;
/*3041*/        else
/*3041*/            return Arrays.copyOf(aobj1, j);
            }

            public final String toString()
            {
                StringBuilder stringbuilder;
/*3056*/        (stringbuilder = new StringBuilder()).append('[');
                Iterator iterator1;
/*3058*/        if((iterator1 = iterator()).hasNext())
/*3061*/            do
                    {
/*3061*/                Object obj = iterator1.next();
/*3062*/                stringbuilder.append(obj != this ? obj : "(this Collection)");
/*3063*/                if(!iterator1.hasNext())
/*3065*/                    break;
/*3065*/                stringbuilder.append(',').append(' ');
                    } while(true);
/*3068*/        return stringbuilder.append(']').toString();
            }

            public final boolean containsAll(Collection collection)
            {
/*3072*/label0:
                {
/*3072*/            if(collection == this)
/*3073*/                break label0;
/*3073*/            collection = collection.iterator();
                    Object obj;
/*3073*/            do
/*3073*/                if(!collection.hasNext())
/*3073*/                    break label0;
/*3074*/            while((obj = collection.next()) != null && contains(obj));
/*3075*/            return false;
                }
/*3078*/        return true;
            }

            public final boolean removeAll(Collection collection)
            {
/*3082*/        boolean flag = false;
/*3083*/        Iterator iterator1 = iterator();
/*3083*/        do
                {
/*3083*/            if(!iterator1.hasNext())
/*3084*/                break;
/*3084*/            if(collection.contains(iterator1.next()))
                    {
/*3085*/                iterator1.remove();
/*3086*/                flag = true;
                    }
                } while(true);
/*3089*/        return flag;
            }

            public final boolean retainAll(Collection collection)
            {
/*3093*/        boolean flag = false;
/*3094*/        Iterator iterator1 = iterator();
/*3094*/        do
                {
/*3094*/            if(!iterator1.hasNext())
/*3095*/                break;
/*3095*/            if(!collection.contains(iterator1.next()))
                    {
/*3096*/                iterator1.remove();
/*3097*/                flag = true;
                    }
                } while(true);
/*3100*/        return flag;
            }

            private static final long serialVersionUID = 0x6499de129d87293dL;
            final ConcurrentHashMapV8 map;
            private static final String oomeMsg = "Required array size too large";

            (ConcurrentHashMapV8 concurrenthashmapv8)
            {
/*2945*/        map = concurrenthashmapv8;
            }
}
