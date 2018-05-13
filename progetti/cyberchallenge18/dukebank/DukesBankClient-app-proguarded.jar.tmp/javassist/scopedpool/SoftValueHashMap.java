// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SoftValueHashMap.java

package javassist.scopedpool;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.*;

public class SoftValueHashMap extends AbstractMap
    implements Map
{
    static class SoftValueRef extends SoftReference
    {

                private static SoftValueRef create(Object obj, Object obj1, ReferenceQueue referencequeue)
                {
/*  44*/            if(obj1 == null)
/*  45*/                return null;
/*  47*/            else
/*  47*/                return new SoftValueRef(obj, obj1, referencequeue);
                }

                public Object key;


                private SoftValueRef(Object obj, Object obj1, ReferenceQueue referencequeue)
                {
/*  38*/            super(obj1, referencequeue);
/*  39*/            key = obj;
                }
    }


            public Set entrySet()
            {
/*  56*/        processQueue();
/*  57*/        return hash.entrySet();
            }

            private void processQueue()
            {
/*  72*/        do
                {
                    SoftValueRef softvalueref;
/*  72*/            if((softvalueref = (SoftValueRef)queue.poll()) == null)
/*  73*/                break;
/*  73*/            if(softvalueref == (SoftValueRef)hash.get(softvalueref.key))
/*  76*/                hash.remove(softvalueref.key);
                } while(true);
            }

            public SoftValueHashMap(int i, float f)
            {
/*  64*/        queue = new ReferenceQueue();
/*  98*/        hash = new HashMap(i, f);
            }

            public SoftValueHashMap(int i)
            {
/*  64*/        queue = new ReferenceQueue();
/* 112*/        hash = new HashMap(i);
            }

            public SoftValueHashMap()
            {
/*  64*/        queue = new ReferenceQueue();
/* 120*/        hash = new HashMap();
            }

            public SoftValueHashMap(Map map)
            {
/* 133*/        this(Math.max(2 * map.size(), 11), 0.75F);
/* 134*/        putAll(map);
            }

            public int size()
            {
/* 146*/        processQueue();
/* 147*/        return hash.size();
            }

            public boolean isEmpty()
            {
/* 154*/        processQueue();
/* 155*/        return hash.isEmpty();
            }

            public boolean containsKey(Object obj)
            {
/* 166*/        processQueue();
/* 167*/        return hash.containsKey(obj);
            }

            public Object get(Object obj)
            {
/* 181*/        processQueue();
/* 182*/        if((obj = (SoftReference)hash.get(obj)) != null)
/* 184*/            return ((SoftReference) (obj)).get();
/* 185*/        else
/* 185*/            return null;
            }

            public Object put(Object obj, Object obj1)
            {
/* 204*/        processQueue();
/* 205*/        if((obj = hash.put(obj, SoftValueRef.create(obj, obj1, queue))) != null)
/* 207*/            obj = ((SoftReference)obj).get();
/* 208*/        return obj;
            }

            public Object remove(Object obj)
            {
/* 222*/        processQueue();
/* 223*/        return hash.remove(obj);
            }

            public void clear()
            {
/* 230*/        processQueue();
/* 231*/        hash.clear();
            }

            private Map hash;
            private ReferenceQueue queue;
}
