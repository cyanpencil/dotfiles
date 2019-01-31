// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMultivaluedMap.java

package javax.ws.rs.core;

import java.util.*;

// Referenced classes of package javax.ws.rs.core:
//            MultivaluedMap

public abstract class AbstractMultivaluedMap
    implements MultivaluedMap
{

            public AbstractMultivaluedMap(Map map)
            {
/*  73*/        if(map == null)
                {
/*  74*/            throw new NullPointerException("Underlying store must not be 'null'.");
                } else
                {
/*  76*/            store = map;
/*  77*/            return;
                }
            }

            public final void putSingle(Object obj, Object obj1)
            {
/*  95*/        ((List) (obj = getValues(obj))).clear();
/*  98*/        if(obj1 != null)
                {
/*  99*/            ((List) (obj)).add(obj1);
/*  99*/            return;
                } else
                {
/* 101*/            addNull(((List) (obj)));
/* 103*/            return;
                }
            }

            protected void addNull(List list)
            {
            }

            protected void addFirstNull(List list)
            {
            }

            public final void add(Object obj, Object obj1)
            {
/* 149*/        obj = getValues(obj);
/* 151*/        if(obj1 != null)
                {
/* 152*/            ((List) (obj)).add(obj1);
/* 152*/            return;
                } else
                {
/* 154*/            addNull(((List) (obj)));
/* 156*/            return;
                }
            }

            public final transient void addAll(Object obj, Object aobj[])
            {
/* 175*/        if(aobj == null)
/* 176*/            throw new NullPointerException("Supplied array of values must not be null.");
/* 178*/        if(aobj.length == 0)
/* 179*/            return;
/* 182*/        obj = getValues(obj);
/* 184*/        int i = (aobj = aobj).length;
/* 184*/        for(int j = 0; j < i; j++)
                {
                    Object obj1;
/* 184*/            if((obj1 = aobj[j]) != null)
/* 186*/                ((List) (obj)).add(obj1);
/* 188*/            else
/* 188*/                addNull(((List) (obj)));
                }

            }

            public final void addAll(Object obj, List list)
            {
/* 210*/        if(list == null)
/* 211*/            throw new NullPointerException("Supplied list of values must not be null.");
/* 213*/        if(list.isEmpty())
/* 214*/            return;
/* 217*/        obj = getValues(obj);
                Object obj1;
/* 219*/        for(list = list.iterator(); list.hasNext();)
/* 219*/            if((obj1 = list.next()) != null)
/* 221*/                ((List) (obj)).add(obj1);
/* 223*/            else
/* 223*/                addNull(((List) (obj)));

            }

            public final Object getFirst(Object obj)
            {
/* 230*/        if((obj = (List)store.get(obj)) != null && ((List) (obj)).size() > 0)
/* 232*/            return ((List) (obj)).get(0);
/* 234*/        else
/* 234*/            return null;
            }

            public final void addFirst(Object obj, Object obj1)
            {
/* 252*/        obj = getValues(obj);
/* 254*/        if(obj1 != null)
                {
/* 255*/            ((List) (obj)).add(0, obj1);
/* 255*/            return;
                } else
                {
/* 257*/            addFirstNull(((List) (obj)));
/* 259*/            return;
                }
            }

            protected final List getValues(Object obj)
            {
                Object obj1;
/* 274*/        if((obj1 = (List)store.get(obj)) == null)
                {
/* 276*/            obj1 = new LinkedList();
/* 277*/            store.put(obj, obj1);
                }
/* 279*/        return ((List) (obj1));
            }

            public String toString()
            {
/* 284*/        return store.toString();
            }

            public int hashCode()
            {
/* 297*/        return store.hashCode();
            }

            public boolean equals(Object obj)
            {
/* 312*/        return store.equals(obj);
            }

            public Collection values()
            {
/* 317*/        return store.values();
            }

            public int size()
            {
/* 322*/        return store.size();
            }

            public List remove(Object obj)
            {
/* 327*/        return (List)store.remove(obj);
            }

            public void putAll(Map map)
            {
/* 332*/        store.putAll(map);
            }

            public List put(Object obj, List list)
            {
/* 337*/        return (List)store.put(obj, list);
            }

            public Set keySet()
            {
/* 342*/        return store.keySet();
            }

            public boolean isEmpty()
            {
/* 347*/        return store.isEmpty();
            }

            public List get(Object obj)
            {
/* 352*/        return (List)store.get(obj);
            }

            public Set entrySet()
            {
/* 357*/        return store.entrySet();
            }

            public boolean containsValue(Object obj)
            {
/* 362*/        return store.containsValue(obj);
            }

            public boolean containsKey(Object obj)
            {
/* 367*/        return store.containsKey(obj);
            }

            public void clear()
            {
/* 372*/        store.clear();
            }

            public boolean equalsIgnoreValueOrder(MultivaluedMap multivaluedmap)
            {
/* 377*/        if(this == multivaluedmap)
/* 378*/            return true;
/* 380*/        if(!keySet().equals(multivaluedmap.keySet()))
/* 381*/            return false;
/* 383*/        Iterator iterator = entrySet().iterator();
/* 383*/label0:
/* 383*/        do
/* 383*/            if(iterator.hasNext())
                    {
/* 383*/                Object obj = (java.util.Map.Entry)iterator.next();
/* 384*/                List list = (List)multivaluedmap.get(((java.util.Map.Entry) (obj)).getKey());
/* 385*/                if(((List)((java.util.Map.Entry) (obj)).getValue()).size() != list.size())
/* 386*/                    return false;
/* 388*/                obj = ((List)((java.util.Map.Entry) (obj)).getValue()).iterator();
                        Object obj1;
/* 388*/                do
                        {
/* 388*/                    if(!((Iterator) (obj)).hasNext())
/* 388*/                        continue label0;
/* 388*/                    obj1 = ((Iterator) (obj)).next();
                        } while(list.contains(obj1));
/* 390*/                break;
                    } else
                    {
/* 394*/                return true;
                    }
/* 389*/        while(true);
/* 390*/        return false;
            }

            public volatile Object remove(Object obj)
            {
/*  56*/        return remove(obj);
            }

            public volatile Object put(Object obj, Object obj1)
            {
/*  56*/        return put(obj, (List)obj1);
            }

            public volatile Object get(Object obj)
            {
/*  56*/        return get(obj);
            }

            protected final Map store;
}
