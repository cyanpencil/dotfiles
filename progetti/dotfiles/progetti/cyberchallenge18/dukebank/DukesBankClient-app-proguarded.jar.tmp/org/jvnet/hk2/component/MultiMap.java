// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MultiMap.java

package org.jvnet.hk2.component;

import java.io.Serializable;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;

public class MultiMap
    implements Serializable, Cloneable
{

            public MultiMap()
            {
/*  68*/        store = new LinkedHashMap();
            }

            public MultiMap(MultiMap multimap)
            {
/*  82*/        this();
/*  83*/        multimap = multimap.entrySet().iterator();
/*  83*/        do
                {
/*  83*/            if(!multimap.hasNext())
/*  83*/                break;
/*  83*/            java.util.Map.Entry entry = (java.util.Map.Entry)multimap.next();
                    List list;
/*  84*/            if(!(list = newList((Collection)entry.getValue())).isEmpty())
/*  86*/                store.put(entry.getKey(), newList((Collection)entry.getValue()));
                } while(true);
            }

            private List newList(Collection collection)
            {
/*  98*/        if(collection == null)
/*  99*/            return new LinkedList();
/* 102*/        else
/* 102*/            return new LinkedList(collection);
            }

            public Set keySet()
            {
/* 112*/        return store.keySet();
            }

            public final void add(Object obj, Object obj1)
            {
                List list;
/* 122*/        if((list = (List)store.get(obj)) == null)
                {
/* 124*/            list = newList(null);
/* 125*/            store.put(obj, list);
                }
/* 127*/        list.add(obj1);
            }

            public void set(Object obj, Collection collection)
            {
                List list;
/* 139*/        if((list = newList(collection)).isEmpty())
                {
/* 141*/            store.remove(obj);
/* 141*/            return;
                } else
                {
/* 144*/            store.put(obj, newList(collection));
/* 146*/            return;
                }
            }

            public void set(Object obj, Object obj1)
            {
                List list;
/* 158*/        (list = newList(null)).add(obj1);
/* 160*/        store.put(obj, list);
            }

            public final List get(Object obj)
            {
/* 170*/        if((obj = (List)store.get(obj)) == null)
/* 172*/            return Collections.emptyList();
/* 174*/        else
/* 174*/            return Collections.unmodifiableList(((List) (obj)));
            }

            public void mergeAll(MultiMap multimap)
            {
/* 187*/        if(multimap == null)
/* 187*/            return;
/* 189*/        for(multimap = multimap.entrySet().iterator(); multimap.hasNext();)
                {
/* 189*/            Object obj = (java.util.Map.Entry)multimap.next();
/* 190*/            List list = (List)store.get(((java.util.Map.Entry) (obj)).getKey());
/* 191*/            if(list == null)
                    {
/* 192*/                if(!(list = newList((Collection)((java.util.Map.Entry) (obj)).getValue())).isEmpty())
/* 194*/                    store.put(((java.util.Map.Entry) (obj)).getKey(), list);
                    } else
                    {
/* 198*/                obj = ((List)((java.util.Map.Entry) (obj)).getValue()).iterator();
/* 198*/                while(((Iterator) (obj)).hasNext()) 
                        {
/* 198*/                    Object obj1 = ((Iterator) (obj)).next();
/* 199*/                    if(!list.contains(obj1))
/* 200*/                        list.add(obj1);
                        }
                    }
                }

            }

            private final List _get(Object obj)
            {
/* 214*/        if((obj = (List)store.get(obj)) == null)
/* 216*/            return Collections.emptyList();
/* 218*/        else
/* 218*/            return ((List) (obj));
            }

            public boolean containsKey(Object obj)
            {
/* 228*/        return !get(obj).isEmpty();
            }

            public boolean contains(Object obj, Object obj1)
            {
/* 240*/        return ((List) (obj = _get(obj))).contains(obj1);
            }

            public List remove(Object obj)
            {
/* 251*/        return (List)store.remove(obj);
            }

            public boolean remove(Object obj, Object obj1)
            {
                List list;
/* 264*/        if((list = (List)store.get(obj)) == null)
/* 265*/            return false;
/* 267*/        obj1 = list.remove(obj1);
/* 269*/        if(list.isEmpty())
/* 270*/            store.remove(obj);
/* 273*/        return ((boolean) (obj1));
            }

            public Object getOne(Object obj)
            {
/* 286*/        return getFirst(obj);
            }

            private Object getFirst(Object obj)
            {
/* 290*/        obj = (List)store.get(obj);
/* 291*/        if(obj == null)
/* 292*/            return null;
/* 295*/        if(((List) (obj)).isEmpty())
/* 296*/            return null;
/* 299*/        else
/* 299*/            return ((List) (obj)).get(0);
            }

            public Set entrySet()
            {
/* 308*/        return store.entrySet();
            }

            public String toCommaSeparatedString()
            {
/* 315*/        StringBuilder stringbuilder = new StringBuilder();
/* 316*/        for(Iterator iterator = entrySet().iterator(); iterator.hasNext();)
                {
                    java.util.Map.Entry entry;
/* 316*/            Iterator iterator1 = ((List)(entry = (java.util.Map.Entry)iterator.next()).getValue()).iterator();
/* 317*/            while(iterator1.hasNext()) 
                    {
/* 317*/                Object obj = iterator1.next();
/* 318*/                if(stringbuilder.length() > 0)
/* 319*/                    stringbuilder.append(',');
/* 321*/                stringbuilder.append(entry.getKey()).append('=').append(obj);
                    }
                }

/* 324*/        return stringbuilder.toString();
            }

            public MultiMap clone()
                throws CloneNotSupportedException
            {
/* 333*/        super.clone();
/* 335*/        return new MultiMap(this);
            }

            public int size()
            {
/* 345*/        return store.size();
            }

            public int hashCode()
            {
/* 350*/        return store.hashCode();
            }

            public boolean equals(Object obj)
            {
/* 356*/        if(obj == null || !(obj instanceof MultiMap))
                {
/* 357*/            return false;
                } else
                {
/* 359*/            obj = (MultiMap)obj;
/* 361*/            return store.equals(((MultiMap) (obj)).store);
                }
            }

            public String toString()
            {
                StringBuilder stringbuilder;
/* 373*/        (stringbuilder = new StringBuilder()).append("{");
/* 376*/        for(Iterator iterator = store.keySet().iterator(); iterator.hasNext(); stringbuilder.append(NEWLINE))
                {
/* 376*/            Object obj = iterator.next();
/* 377*/            stringbuilder.append(obj).append(": ");
/* 378*/            stringbuilder.append(store.get(obj));
                }

/* 381*/        stringbuilder.append("}");
/* 382*/        return stringbuilder.toString();
            }

            public volatile Object clone()
                throws CloneNotSupportedException
            {
/*  62*/        return clone();
            }

            private static final long serialVersionUID = 0xc66ad313c4af704L;
            private final Map store;
            private static final String NEWLINE = (String)AccessController.doPrivileged(new PrivilegedAction() {

                public final String run()
                {
/* 367*/            return System.getProperty("line.separator");
                }

                public final volatile Object run()
                {
/* 364*/            return run();
                }

    });

}
