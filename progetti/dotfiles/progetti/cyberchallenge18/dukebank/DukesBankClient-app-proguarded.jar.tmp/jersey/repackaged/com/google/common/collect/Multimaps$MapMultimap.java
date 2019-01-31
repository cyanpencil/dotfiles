// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multimaps.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMultimap, Maps, Multimaps, SetMultimap, 
//            Multimap, Sets, CollectPreconditions

static class l extends AbstractMultimap
    implements Serializable, SetMultimap
{

            public int size()
            {
/* 937*/        return map.size();
            }

            public boolean containsKey(Object obj)
            {
/* 942*/        return map.containsKey(obj);
            }

            public boolean containsValue(Object obj)
            {
/* 947*/        return map.containsValue(obj);
            }

            public boolean containsEntry(Object obj, Object obj1)
            {
/* 952*/        return map.entrySet().contains(Maps.immutableEntry(obj, obj1));
            }

            public Set get(final Object key)
            {
/* 957*/        return new Sets.ImprovedAbstractSet() {

                    public Iterator iterator()
                    {
/* 959*/                return new Iterator() {

                            public boolean hasNext()
                            {
/* 964*/                        return i == 0 && map.containsKey(key);
                            }

                            public Object next()
                            {
/* 969*/                        if(!hasNext())
                                {
/* 970*/                            throw new NoSuchElementException();
                                } else
                                {
/* 972*/                            i++;
/* 973*/                            return map.get(key);
                                }
                            }

                            public void remove()
                            {
/* 978*/                        CollectPreconditions.checkRemove(i == 1);
/* 979*/                        i = -1;
/* 980*/                        map.remove(key);
                            }

                            int i;
                            final _cls1 this$1;

                            
                            {
/* 959*/                        this$1 = _cls1.this;
/* 959*/                        super();
                            }
                };
                    }

                    public int size()
                    {
/* 986*/                return !map.containsKey(key) ? 0 : 1;
                    }

                    final Object val$key;
                    final Multimaps.MapMultimap this$0;

                    
                    {
/* 957*/                this$0 = Multimaps.MapMultimap.this;
/* 957*/                key = obj;
/* 957*/                super();
                    }
        };
            }

            public boolean put(Object obj, Object obj1)
            {
/* 993*/        throw new UnsupportedOperationException();
            }

            public boolean putAll(Object obj, Iterable iterable)
            {
/* 998*/        throw new UnsupportedOperationException();
            }

            public boolean putAll(Multimap multimap)
            {
/*1003*/        throw new UnsupportedOperationException();
            }

            public Set replaceValues(Object obj, Iterable iterable)
            {
/*1008*/        throw new UnsupportedOperationException();
            }

            public boolean remove(Object obj, Object obj1)
            {
/*1013*/        return map.entrySet().remove(Maps.immutableEntry(obj, obj1));
            }

            public Set removeAll(Object obj)
            {
/*1018*/        HashSet hashset = new HashSet(2);
/*1019*/        if(!map.containsKey(obj))
                {
/*1020*/            return hashset;
                } else
                {
/*1022*/            hashset.add(map.remove(obj));
/*1023*/            return hashset;
                }
            }

            public void clear()
            {
/*1028*/        map.clear();
            }

            public Set keySet()
            {
/*1033*/        return map.keySet();
            }

            public Collection values()
            {
/*1038*/        return map.values();
            }

            public Set entries()
            {
/*1043*/        return map.entrySet();
            }

            Iterator entryIterator()
            {
/*1048*/        return map.entrySet().iterator();
            }

            Map createAsMap()
            {
/*1053*/        return new (this);
            }

            public int hashCode()
            {
/*1057*/        return map.hashCode();
            }

            public volatile Collection entries()
            {
/* 927*/        return entries();
            }

            public volatile Collection replaceValues(Object obj, Iterable iterable)
            {
/* 927*/        return replaceValues(obj, iterable);
            }

            public volatile Collection get(Object obj)
            {
/* 927*/        return get(obj);
            }

            public volatile Collection removeAll(Object obj)
            {
/* 927*/        return removeAll(obj);
            }

            final Map map;

            et(Map map1)
            {
/* 932*/        map = (Map)Preconditions.checkNotNull(map1);
            }
}
