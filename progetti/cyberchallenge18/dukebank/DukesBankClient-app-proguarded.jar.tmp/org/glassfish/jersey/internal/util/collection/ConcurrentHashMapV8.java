// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConcurrentHashMapV8.java

package org.glassfish.jersey.internal.util.collection;

import java.io.*;
import java.lang.reflect.*;
import java.security.*;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import sun.misc.Unsafe;

class ConcurrentHashMapV8 extends AbstractMap
    implements Serializable, ConcurrentMap
{
    static final class CounterHashCode
    {

                int code;

                CounterHashCode()
                {
                }
    }

    static final class CounterCell
    {

                volatile long p0;
                volatile long p1;
                volatile long p2;
                volatile long p3;
                volatile long p4;
                volatile long p5;
                volatile long p6;
                volatile long value;
                volatile long q0;
                volatile long q1;
                volatile long q2;
                volatile long q3;
                volatile long q4;
                volatile long q5;
                volatile long q6;

                CounterCell(long l)
                {
/*3361*/            value = l;
                }
    }

    static final class EntrySetView extends CollectionView
        implements Serializable, Set
    {

                public final boolean contains(Object obj)
                {
                    Object obj1;
                    java.util.Map.Entry entry;
/*3287*/            return (obj instanceof java.util.Map.Entry) && (obj = (entry = (java.util.Map.Entry)obj).getKey()) != null && (obj1 = map.get(obj)) != null && (obj = entry.getValue()) != null && (obj == obj1 || obj.equals(obj1));
                }

                public final boolean remove(Object obj)
                {
                    Object obj1;
/*3298*/            return (obj instanceof java.util.Map.Entry) && (obj = ((java.util.Map.Entry) (obj1 = (java.util.Map.Entry)obj)).getKey()) != null && (obj1 = ((java.util.Map.Entry) (obj1)).getValue()) != null && map.remove(obj, obj1);
                }

                public final Iterator iterator()
                {
                    ConcurrentHashMapV8 concurrenthashmapv8;
                    Node anode[];
/*3308*/            int i = (anode = (concurrenthashmapv8 = map).table) != null ? anode.length : 0;
/*3311*/            return new EntryIterator(anode, i, 0, i, concurrenthashmapv8);
                }

                public final boolean add(java.util.Map.Entry entry)
                {
/*3315*/            return map.putVal(entry.getKey(), entry.getValue(), false) == null;
                }

                public final boolean addAll(Collection collection)
                {
/*3319*/            boolean flag = false;
/*3320*/            collection = collection.iterator();
/*3320*/            do
                    {
/*3320*/                if(!collection.hasNext())
/*3320*/                    break;
/*3320*/                java.util.Map.Entry entry = (java.util.Map.Entry)collection.next();
/*3321*/                if(add(entry))
/*3322*/                    flag = true;
                    } while(true);
/*3324*/            return flag;
                }

                public final int hashCode()
                {
/*3328*/            int i = 0;
                    Node anode[];
/*3330*/            if((anode = map.table) != null)
                    {
/*3331*/                Traverser traverser = new Traverser(anode, anode.length, 0, anode.length);
                        Node node;
/*3332*/                while((node = traverser.advance()) != null) 
/*3333*/                    i += node.hashCode();
                    }
/*3336*/            return i;
                }

                public final boolean equals(Object obj)
                {
/*3341*/            return (obj instanceof Set) && ((obj = (Set)obj) == this || containsAll(((Collection) (obj))) && ((Set) (obj)).containsAll(this));
                }

                public final volatile boolean add(Object obj)
                {
/*3274*/            return add((java.util.Map.Entry)obj);
                }

                private static final long serialVersionUID = 0x1f364c905893293dL;

                EntrySetView(ConcurrentHashMapV8 concurrenthashmapv8)
                {
/*3279*/            super(concurrenthashmapv8);
                }
    }

    static final class ValuesView extends CollectionView
        implements Serializable, Collection
    {

                public final boolean contains(Object obj)
                {
/*3238*/            return map.containsValue(obj);
                }

                public final boolean remove(Object obj)
                {
/*3242*/label0:
                    {
/*3242*/                if(obj == null)
/*3243*/                    break label0;
/*3243*/                Iterator iterator1 = iterator();
/*3243*/                do
/*3243*/                    if(!iterator1.hasNext())
/*3244*/                        break label0;
/*3244*/                while(!obj.equals(iterator1.next()));
/*3245*/                iterator1.remove();
/*3246*/                return true;
                    }
/*3250*/            return false;
                }

                public final Iterator iterator()
                {
                    ConcurrentHashMapV8 concurrenthashmapv8;
                    Node anode[];
/*3254*/            int i = (anode = (concurrenthashmapv8 = map).table) != null ? anode.length : 0;
/*3257*/            return new ValueIterator(anode, i, 0, i, concurrenthashmapv8);
                }

                public final boolean add(Object obj)
                {
/*3261*/            throw new UnsupportedOperationException();
                }

                public final boolean addAll(Collection collection)
                {
/*3265*/            throw new UnsupportedOperationException();
                }

                private static final long serialVersionUID = 0x1f364c905893293dL;

                ValuesView(ConcurrentHashMapV8 concurrenthashmapv8)
                {
/*3234*/            super(concurrenthashmapv8);
                }
    }

    public static class KeySetView extends CollectionView
        implements Serializable, Set
    {

                public Object getMappedValue()
                {
/*3134*/            return value;
                }

                public boolean contains(Object obj)
                {
/*3143*/            return map.containsKey(obj);
                }

                public boolean remove(Object obj)
                {
/*3156*/            return map.remove(obj) != null;
                }

                public Iterator iterator()
                {
                    Node anode[];
                    ConcurrentHashMapV8 concurrenthashmapv8;
/*3164*/            int i = (anode = (concurrenthashmapv8 = map).table) != null ? anode.length : 0;
/*3166*/            return new KeyIterator(anode, i, 0, i, concurrenthashmapv8);
                }

                public boolean add(Object obj)
                {
                    Object obj1;
/*3181*/            if((obj1 = value) == null)
/*3182*/                throw new UnsupportedOperationException();
/*3183*/            return map.putVal(obj, obj1, true) == null;
                }

                public boolean addAll(Collection collection)
                {
/*3198*/            boolean flag = false;
                    Object obj;
/*3200*/            if((obj = value) == null)
/*3201*/                throw new UnsupportedOperationException();
/*3202*/            collection = collection.iterator();
/*3202*/            do
                    {
/*3202*/                if(!collection.hasNext())
/*3202*/                    break;
/*3202*/                Object obj1 = collection.next();
/*3203*/                if(map.putVal(obj1, obj, true) == null)
/*3204*/                    flag = true;
                    } while(true);
/*3206*/            return flag;
                }

                public int hashCode()
                {
/*3210*/            int i = 0;
/*3211*/            for(Iterator iterator1 = iterator(); iterator1.hasNext();)
                    {
/*3211*/                Object obj = iterator1.next();
/*3212*/                i += obj.hashCode();
                    }

/*3213*/            return i;
                }

                public boolean equals(Object obj)
                {
/*3218*/            return (obj instanceof Set) && ((obj = (Set)obj) == this || containsAll(((Collection) (obj))) && ((Set) (obj)).containsAll(this));
                }

                public volatile ConcurrentHashMapV8 getMap()
                {
/*3116*/            return super.getMap();
                }

                private static final long serialVersionUID = 0x6499de129d87293dL;
                private final Object value;

                KeySetView(ConcurrentHashMapV8 concurrenthashmapv8, Object obj)
                {
/*3122*/            super(concurrenthashmapv8);
/*3123*/            value = obj;
                }
    }

    static abstract class CollectionView
        implements Serializable, Collection
    {

                public ConcurrentHashMapV8 getMap()
                {
/*2954*/            return map;
                }

                public final void clear()
                {
/*2962*/            map.clear();
                }

                public final int size()
                {
/*2966*/            return map.size();
                }

                public final boolean isEmpty()
                {
/*2970*/            return map.isEmpty();
                }

                public abstract Iterator iterator();

                public abstract boolean contains(Object obj);

                public abstract boolean remove(Object obj);

                public final Object[] toArray()
                {
                    long l;
/*2993*/            if((l = map.mappingCount()) > 0x7ffffff7L)
/*2995*/                throw new OutOfMemoryError("Required array size too large");
/*2996*/            Object aobj[] = new Object[l = (int)l];
/*2998*/            int i = 0;
                    Object obj;
/*2999*/            for(Iterator iterator1 = iterator(); iterator1.hasNext(); aobj[i++] = obj)
                    {
/*2999*/                obj = iterator1.next();
/*3000*/                if(i != l)
/*3001*/                    continue;
/*3001*/                if(l >= 0x7ffffff7)
/*3002*/                    throw new OutOfMemoryError("Required array size too large");
/*3003*/                if(l >= 0x3ffffffb)
/*3004*/                    l = 0x7ffffff7;
/*3006*/                else
/*3006*/                    l += (l >>> 1) + 1;
/*3007*/                aobj = Arrays.copyOf(aobj, l);
                    }

/*3011*/            if(i == l)
/*3011*/                return aobj;
/*3011*/            else
/*3011*/                return Arrays.copyOf(aobj, i);
                }

                public final Object[] toArray(Object aobj[])
                {
                    long l;
/*3016*/            if((l = map.mappingCount()) > 0x7ffffff7L)
/*3018*/                throw new OutOfMemoryError("Required array size too large");
/*3019*/            l = (int)l;
                    Object aobj1[];
/*3020*/            int i = (aobj1 = aobj.length < l ? (Object[])Array.newInstance(((Object) (aobj)).getClass().getComponentType(), l) : aobj).length;
/*3024*/            int j = 0;
                    Object obj;
/*3025*/            for(Iterator iterator1 = iterator(); iterator1.hasNext(); aobj1[j++] = obj)
                    {
/*3025*/                obj = iterator1.next();
/*3026*/                if(j != i)
/*3027*/                    continue;
/*3027*/                if(i >= 0x7ffffff7)
/*3028*/                    throw new OutOfMemoryError("Required array size too large");
/*3029*/                if(i >= 0x3ffffffb)
/*3030*/                    i = 0x7ffffff7;
/*3032*/                else
/*3032*/                    i += (i >>> 1) + 1;
/*3033*/                aobj1 = Arrays.copyOf(aobj1, i);
                    }

/*3037*/            if(aobj == aobj1 && j < i)
                    {
/*3038*/                aobj1[j] = null;
/*3039*/                return aobj1;
                    }
/*3041*/            if(j == i)
/*3041*/                return aobj1;
/*3041*/            else
/*3041*/                return Arrays.copyOf(aobj1, j);
                }

                public final String toString()
                {
                    StringBuilder stringbuilder;
/*3056*/            (stringbuilder = new StringBuilder()).append('[');
                    Iterator iterator1;
/*3058*/            if((iterator1 = iterator()).hasNext())
/*3061*/                do
                        {
/*3061*/                    Object obj = iterator1.next();
/*3062*/                    stringbuilder.append(obj != this ? obj : "(this Collection)");
/*3063*/                    if(!iterator1.hasNext())
/*3065*/                        break;
/*3065*/                    stringbuilder.append(',').append(' ');
                        } while(true);
/*3068*/            return stringbuilder.append(']').toString();
                }

                public final boolean containsAll(Collection collection)
                {
/*3072*/label0:
                    {
/*3072*/                if(collection == this)
/*3073*/                    break label0;
/*3073*/                collection = collection.iterator();
                        Object obj;
/*3073*/                do
/*3073*/                    if(!collection.hasNext())
/*3073*/                        break label0;
/*3074*/                while((obj = collection.next()) != null && contains(obj));
/*3075*/                return false;
                    }
/*3078*/            return true;
                }

                public final boolean removeAll(Collection collection)
                {
/*3082*/            boolean flag = false;
/*3083*/            Iterator iterator1 = iterator();
/*3083*/            do
                    {
/*3083*/                if(!iterator1.hasNext())
/*3084*/                    break;
/*3084*/                if(collection.contains(iterator1.next()))
                        {
/*3085*/                    iterator1.remove();
/*3086*/                    flag = true;
                        }
                    } while(true);
/*3089*/            return flag;
                }

                public final boolean retainAll(Collection collection)
                {
/*3093*/            boolean flag = false;
/*3094*/            Iterator iterator1 = iterator();
/*3094*/            do
                    {
/*3094*/                if(!iterator1.hasNext())
/*3095*/                    break;
/*3095*/                if(!collection.contains(iterator1.next()))
                        {
/*3096*/                    iterator1.remove();
/*3097*/                    flag = true;
                        }
                    } while(true);
/*3100*/            return flag;
                }

                private static final long serialVersionUID = 0x6499de129d87293dL;
                final ConcurrentHashMapV8 map;
                private static final String oomeMsg = "Required array size too large";

                CollectionView(ConcurrentHashMapV8 concurrenthashmapv8)
                {
/*2945*/            map = concurrenthashmapv8;
                }
    }

    static final class MapEntry
        implements java.util.Map.Entry
    {

                public final Object getKey()
                {
/*2891*/            return key;
                }

                public final Object getValue()
                {
/*2895*/            return val;
                }

                public final int hashCode()
                {
/*2899*/            return key.hashCode() ^ val.hashCode();
                }

                public final String toString()
                {
/*2903*/            return (new StringBuilder()).append(key).append("=").append(val).toString();
                }

                public final boolean equals(Object obj)
                {
                    Object obj1;
/*2910*/            return (obj instanceof java.util.Map.Entry) && (obj = ((java.util.Map.Entry) (obj1 = (java.util.Map.Entry)obj)).getKey()) != null && (obj1 = ((java.util.Map.Entry) (obj1)).getValue()) != null && (obj == key || obj.equals(key)) && (obj1 == val || obj1.equals(val));
                }

                public final Object setValue(Object obj)
                {
/*2926*/            if(obj == null)
                    {
/*2926*/                throw new NullPointerException();
                    } else
                    {
/*2927*/                Object obj1 = val;
/*2928*/                val = obj;
/*2929*/                map.put(key, obj);
/*2930*/                return obj1;
                    }
                }

                final Object key;
                Object val;
                final ConcurrentHashMapV8 map;

                MapEntry(Object obj, Object obj1, ConcurrentHashMapV8 concurrenthashmapv8)
                {
/*2885*/            key = obj;
/*2886*/            val = obj1;
/*2887*/            map = concurrenthashmapv8;
                }
    }

    static final class EntryIterator extends BaseIterator
        implements Iterator
    {

                public final java.util.Map.Entry next()
                {
                    Node node;
/*2866*/            if((node = next) == null)
                    {
/*2867*/                throw new NoSuchElementException();
                    } else
                    {
/*2868*/                Object obj = node.key;
/*2869*/                Object obj1 = node.val;
/*2870*/                lastReturned = node;
/*2871*/                advance();
/*2872*/                return new MapEntry(obj, obj1, map);
                    }
                }

                public final volatile Object next()
                {
/*2857*/            return next();
                }

                EntryIterator(Node anode[], int i, int j, int k, ConcurrentHashMapV8 concurrenthashmapv8)
                {
/*2861*/            super(anode, i, j, k, concurrenthashmapv8);
                }
    }

    static final class ValueIterator extends BaseIterator
        implements Enumeration, Iterator
    {

                public final Object next()
                {
                    Node node;
/*2844*/            if((node = next) == null)
                    {
/*2845*/                throw new NoSuchElementException();
                    } else
                    {
/*2846*/                Object obj = node.val;
/*2847*/                lastReturned = node;
/*2848*/                advance();
/*2849*/                return obj;
                    }
                }

                public final Object nextElement()
                {
/*2853*/            return next();
                }

                ValueIterator(Node anode[], int i, int j, int k, ConcurrentHashMapV8 concurrenthashmapv8)
                {
/*2839*/            super(anode, i, j, k, concurrenthashmapv8);
                }
    }

    static final class KeyIterator extends BaseIterator
        implements Enumeration, Iterator
    {

                public final Object next()
                {
                    Node node;
/*2822*/            if((node = next) == null)
                    {
/*2823*/                throw new NoSuchElementException();
                    } else
                    {
/*2824*/                Object obj = node.key;
/*2825*/                lastReturned = node;
/*2826*/                advance();
/*2827*/                return obj;
                    }
                }

                public final Object nextElement()
                {
/*2831*/            return next();
                }

                KeyIterator(Node anode[], int i, int j, int k, ConcurrentHashMapV8 concurrenthashmapv8)
                {
/*2817*/            super(anode, i, j, k, concurrenthashmapv8);
                }
    }

    static class BaseIterator extends Traverser
    {

                public final boolean hasNext()
                {
/*2797*/            return next != null;
                }

                public final boolean hasMoreElements()
                {
/*2801*/            return next != null;
                }

                public final void remove()
                {
                    Node node;
/*2806*/            if((node = lastReturned) == null)
                    {
/*2807*/                throw new IllegalStateException();
                    } else
                    {
/*2808*/                lastReturned = null;
/*2809*/                map.replaceNode(node.key, null, null);
/*2810*/                return;
                    }
                }

                final ConcurrentHashMapV8 map;
                Node lastReturned;

                BaseIterator(Node anode[], int i, int j, int k, ConcurrentHashMapV8 concurrenthashmapv8)
                {
/*2791*/            super(anode, i, j, k);
/*2792*/            map = concurrenthashmapv8;
/*2793*/            advance();
                }
    }

    static class Traverser
    {

                final Node advance()
                {
                    Object obj;
/*2753*/            if((obj = next) != null)
/*2754*/                obj = ((Node) (obj)).next;
/*2760*/            do
                    {
/*2760*/                if(obj != null)
/*2761*/                    return next = ((Node) (obj));
                        int i;
                        int j;
/*2762*/                if(baseIndex >= baseLimit || (obj = tab) == null || (j = obj.length) <= (i = index) || i < 0)
/*2764*/                    return next = null;
/*2765*/                if((obj = ConcurrentHashMapV8.tabAt(((Node []) (obj)), index)) != null && ((Node) (obj)).hash < 0)
                        {
/*2766*/                    if(obj instanceof ForwardingNode)
                            {
/*2767*/                        tab = ((ForwardingNode)obj).nextTable;
/*2768*/                        obj = null;
/*2769*/                        continue;
                            }
/*2770*/                    if(obj instanceof TreeBin)
/*2771*/                        obj = ((TreeBin)obj).first;
/*2773*/                    else
/*2773*/                        obj = null;
                        }
/*2775*/                if((index += baseSize) >= j)
/*2776*/                    index = ++baseIndex;
                    } while(true);
                }

                Node tab[];
                Node next;
                int index;
                int baseIndex;
                int baseLimit;
                final int baseSize;

                Traverser(Node anode[], int i, int j, int k)
                {
/*2741*/            tab = anode;
/*2742*/            baseSize = i;
/*2743*/            baseIndex = index = j;
/*2744*/            baseLimit = k;
/*2745*/            next = null;
                }
    }

    static final class TreeBin extends Node
    {

                static int tieBreakOrder(Object obj, Object obj1)
                {
                    int i;
/*2182*/            if(obj == null || obj1 == null || (i = obj.getClass().getName().compareTo(obj1.getClass().getName())) == 0)
/*2185*/                i = System.identityHashCode(obj) > System.identityHashCode(obj1) ? 1 : -1;
/*2187*/            return i;
                }

                private final void lockRoot()
                {
/*2241*/            if(!U.compareAndSwapInt(this, LOCKSTATE, 0, 1))
/*2242*/                contendedLock();
                }

                private final void unlockRoot()
                {
/*2249*/            lockState = 0;
                }

                private final void contendedLock()
                {
/*2256*/            boolean flag = false;
                    int i;
/*2258*/            do
/*2258*/                if(((i = lockState) & 1) == 0)
                        {
/*2259*/                    if(U.compareAndSwapInt(this, LOCKSTATE, i, 1))
                            {
/*2260*/                        if(flag)
/*2261*/                            waiter = null;
/*2262*/                        return;
                            }
                        } else
/*2264*/                if((i & 2) == 0)
                        {
/*2265*/                    if(U.compareAndSwapInt(this, LOCKSTATE, i, i | 2))
                            {
/*2266*/                        flag = true;
/*2267*/                        waiter = Thread.currentThread();
                            }
                        } else
/*2269*/                if(flag)
/*2270*/                    LockSupport.park(this);
/*2270*/            while(true);
                }

                final Node find(int i, Object obj)
                {
                    Object obj1;
/*2280*/            if(obj == null)
/*2281*/                break MISSING_BLOCK_LABEL_198;
/*2281*/            obj1 = first;
_L3:
/*2281*/            if(obj1 == null) goto _L2; else goto _L1
_L1:
                    int j;
/*2284*/            if(((j = lockState) & 3) != 0)
                    {
/*2285*/                if(((Node) (obj1)).hash == i && ((j = ((int) (((Node) (obj1)).key))) == obj || j != null && obj.equals(j)))
/*2287*/                    return ((Node) (obj1));
/*2288*/                continue; /* Loop/switch isn't completed */
                    }
/*2288*/            if(!U.compareAndSwapInt(this, LOCKSTATE, j, j + 4))
/*2293*/                continue; /* Loop/switch isn't completed */
/*2293*/            i = (obj1 = root) != null ? ((int) (((TreeNode) (obj1)).findTreeNode(i, obj, null))) : null;
/*2299*/            do
/*2299*/                obj = lockState;
/*2299*/            while(!U.compareAndSwapInt(this, LOCKSTATE, lockState, lockState - 4));
/*2302*/            if(obj == 6 && (obj = waiter) != null)
/*2303*/                LockSupport.unpark(((Thread) (obj)));
/*2304*/            break MISSING_BLOCK_LABEL_188;
/*2296*/            i;
/*2299*/            do
/*2299*/                obj = lockState;
/*2299*/            while(!U.compareAndSwapInt(this, LOCKSTATE, lockState, lockState - 4));
/*2302*/            if(obj == 6 && (obj = waiter) != null)
/*2303*/                LockSupport.unpark(((Thread) (obj)));
/*2304*/            throw i;
/*2305*/            return i;
/*2281*/            obj1 = ((Node) (obj1)).next;
                      goto _L3
_L2:
/*2309*/            return null;
                }

                final TreeNode putTreeVal(int i, Object obj, Object obj1)
                {
                    TreeNode treenode4;
/*2318*/            Object obj2 = null;
/*2319*/            boolean flag = false;
/*2320*/            TreeNode treenode = root;
                    int j;
                    TreeNode treenode2;
/*2324*/            do
                    {
/*2324*/                if(treenode == null)
                        {
/*2325*/                    first = root = new TreeNode(i, obj, obj1, null, null);
/*2326*/                    break MISSING_BLOCK_LABEL_340;
                        }
/*2327*/                if((j = treenode.hash) > i)
/*2328*/                    j = -1;
/*2329*/                else
/*2329*/                if(j < i)
                        {
/*2330*/                    j = 1;
                        } else
                        {
                            Object obj3;
/*2331*/                    if((obj3 = treenode.key) == obj || obj3 != null && obj.equals(obj3))
/*2332*/                        return treenode;
/*2333*/                    if(obj2 == null && (obj2 = ConcurrentHashMapV8.comparableClassFor(obj)) == null || (j = ConcurrentHashMapV8.compareComparables(((Class) (obj2)), obj, obj3)) == 0)
                            {
/*2336*/                        if(!flag)
                                {
/*2338*/                            flag = true;
                                    TreeNode treenode1;
                                    TreeNode treenode3;
/*2339*/                            if((treenode3 = treenode.left) != null && (treenode1 = treenode3.findTreeNode(i, obj, ((Class) (obj2)))) != null || (treenode3 = treenode.right) != null && (treenode1 = treenode3.findTreeNode(i, obj, ((Class) (obj2)))) != null)
/*2343*/                                return treenode1;
                                }
/*2345*/                        j = tieBreakOrder(obj, obj3);
                            }
                        }
/*2348*/                treenode2 = treenode;
                    } while((treenode = j > 0 ? treenode.right : treenode.left) != null);
/*2351*/            obj2 = first;
/*2352*/            first = treenode4 = new TreeNode(i, obj, obj1, ((Node) (obj2)), treenode2);
/*2353*/            if(obj2 != null)
/*2354*/                obj2.prev = treenode4;
/*2355*/            if(j <= 0)
/*2356*/                treenode2.left = treenode4;
/*2358*/            else
/*2358*/                treenode2.right = treenode4;
/*2359*/            if(!treenode2.red)
                    {
/*2360*/                treenode4.red = true;
/*2360*/                break MISSING_BLOCK_LABEL_340;
                    }
/*2362*/            lockRoot();
/*2364*/            root = balanceInsertion(root, treenode4);
/*2366*/            unlockRoot();
/*2367*/            break MISSING_BLOCK_LABEL_340;
/*2366*/            i;
/*2366*/            unlockRoot();
/*2366*/            throw i;
/*2372*/            if(!$assertionsDisabled && !checkInvariants(root))
/*2372*/                throw new AssertionError();
/*2373*/            else
/*2373*/                return null;
                }

                final boolean removeTreeNode(TreeNode treenode)
                {
                    TreeNode treenode1;
/*2387*/            treenode1 = (TreeNode)treenode.next;
                    TreeNode treenode2;
/*2388*/            if((treenode2 = treenode.prev) == null)
/*2392*/                first = treenode1;
/*2394*/            else
/*2394*/                treenode2.next = treenode1;
/*2395*/            if(treenode1 != null)
/*2396*/                treenode1.prev = treenode2;
/*2397*/            if(first == null)
                    {
/*2398*/                root = null;
/*2399*/                return true;
                    }
/*2401*/            if((treenode1 = root) == null || treenode1.right == null || (treenode2 = treenode1.left) == null || treenode2.left == null)
/*2403*/                return true;
/*2404*/            lockRoot();
/*2407*/            TreeNode treenode3 = treenode.left;
/*2408*/            TreeNode treenode4 = treenode.right;
/*2409*/            if(treenode3 != null && treenode4 != null)
                    {
                        TreeNode treenode5;
                        TreeNode treenode8;
/*2410*/                for(treenode5 = treenode4; (treenode8 = treenode5.left) != null; treenode5 = treenode8);
/*2413*/                boolean flag = treenode5.red;
/*2414*/                treenode5.red = treenode.red;
/*2415*/                treenode.red = flag;
/*2416*/                TreeNode treenode9 = treenode5.right;
/*2417*/                TreeNode treenode10 = treenode.parent;
/*2418*/                if(treenode5 == treenode4)
                        {
/*2419*/                    treenode.parent = treenode5;
/*2420*/                    treenode5.right = treenode;
                        } else
                        {
/*2422*/                    TreeNode treenode11 = treenode5.parent;
/*2423*/                    if((treenode.parent = treenode11) != null)
/*2424*/                        if(treenode5 == treenode11.left)
/*2425*/                            treenode11.left = treenode;
/*2427*/                        else
/*2427*/                            treenode11.right = treenode;
/*2429*/                    if((treenode5.right = treenode4) != null)
/*2430*/                        treenode4.parent = treenode5;
                        }
/*2432*/                treenode.left = null;
/*2433*/                if((treenode.right = treenode9) != null)
/*2434*/                    treenode9.parent = treenode;
/*2435*/                if((treenode5.left = treenode3) != null)
/*2436*/                    treenode3.parent = treenode5;
/*2437*/                if((treenode5.parent = treenode10) == null)
/*2438*/                    treenode1 = treenode5;
/*2439*/                else
/*2439*/                if(treenode == treenode10.left)
/*2440*/                    treenode10.left = treenode5;
/*2442*/                else
/*2442*/                    treenode10.right = treenode5;
/*2443*/                if(treenode9 != null)
/*2444*/                    treenode3 = treenode9;
/*2446*/                else
/*2446*/                    treenode3 = treenode;
                    } else
/*2447*/            if(treenode3 != null)
/*2448*/                treenode3 = treenode3;
/*2449*/            else
/*2449*/            if(treenode4 != null)
/*2450*/                treenode3 = treenode4;
/*2452*/            else
/*2452*/                treenode3 = treenode;
/*2453*/            if(treenode3 != treenode)
                    {
                        TreeNode treenode6;
/*2454*/                if((treenode6 = treenode3.parent = treenode.parent) == null)
/*2456*/                    treenode1 = treenode3;
/*2457*/                else
/*2457*/                if(treenode == treenode6.left)
/*2458*/                    treenode6.left = treenode3;
/*2460*/                else
/*2460*/                    treenode6.right = treenode3;
/*2461*/                treenode.left = treenode.right = treenode.parent = null;
                    }
/*2464*/            root = treenode.red ? treenode1 : balanceDeletion(treenode1, treenode3);
                    TreeNode treenode7;
/*2466*/            if(treenode == treenode3 && (treenode7 = treenode.parent) != null)
                    {
/*2469*/                if(treenode == treenode7.left)
/*2470*/                    treenode7.left = null;
/*2471*/                else
/*2471*/                if(treenode == treenode7.right)
/*2472*/                    treenode7.right = null;
/*2473*/                treenode.parent = null;
                    }
/*2477*/            unlockRoot();
/*2478*/            break MISSING_BLOCK_LABEL_519;
/*2477*/            treenode;
/*2477*/            unlockRoot();
/*2477*/            throw treenode;
/*2479*/            if(!$assertionsDisabled && !checkInvariants(root))
/*2479*/                throw new AssertionError();
/*2480*/            else
/*2480*/                return false;
                }

                static TreeNode rotateLeft(TreeNode treenode, TreeNode treenode1)
                {
                    TreeNode treenode2;
/*2491*/            if(treenode1 != null && (treenode2 = treenode1.right) != null)
                    {
                        TreeNode treenode3;
/*2492*/                if((treenode3 = treenode1.right = treenode2.left) != null)
/*2493*/                    treenode3.parent = treenode1;
/*2494*/                if((treenode3 = treenode2.parent = treenode1.parent) == null)
/*2495*/                    (treenode = treenode2).red = false;
/*2496*/                else
/*2496*/                if(treenode3.left == treenode1)
/*2497*/                    treenode3.left = treenode2;
/*2499*/                else
/*2499*/                    treenode3.right = treenode2;
/*2500*/                treenode2.left = treenode1;
/*2501*/                treenode1.parent = treenode2;
                    }
/*2503*/            return treenode;
                }

                static TreeNode rotateRight(TreeNode treenode, TreeNode treenode1)
                {
                    TreeNode treenode2;
/*2511*/            if(treenode1 != null && (treenode2 = treenode1.left) != null)
                    {
                        TreeNode treenode3;
/*2512*/                if((treenode3 = treenode1.left = treenode2.right) != null)
/*2513*/                    treenode3.parent = treenode1;
/*2514*/                if((treenode3 = treenode2.parent = treenode1.parent) == null)
/*2515*/                    (treenode = treenode2).red = false;
/*2516*/                else
/*2516*/                if(treenode3.right == treenode1)
/*2517*/                    treenode3.right = treenode2;
/*2519*/                else
/*2519*/                    treenode3.left = treenode2;
/*2520*/                treenode2.right = treenode1;
/*2521*/                treenode1.parent = treenode2;
                    }
/*2523*/            return treenode;
                }

                static TreeNode balanceInsertion(TreeNode treenode, TreeNode treenode1)
                {
/*2528*/            treenode1.red = true;
/*2530*/            do
                    {
                        TreeNode treenode2;
/*2530*/                if((treenode2 = treenode1.parent) == null)
                        {
/*2531*/                    treenode1.red = false;
/*2532*/                    return treenode1;
                        }
                        TreeNode treenode3;
/*2533*/                if(!treenode2.red || (treenode3 = treenode2.parent) == null)
/*2534*/                    return treenode;
                        TreeNode treenode4;
/*2535*/                if(treenode2 == (treenode4 = treenode3.left))
                        {
/*2536*/                    if((treenode4 = treenode3.right) != null && treenode4.red)
                            {
/*2537*/                        treenode4.red = false;
/*2538*/                        treenode2.red = false;
/*2539*/                        treenode3.red = true;
/*2540*/                        treenode1 = treenode3;
                            } else
                            {
/*2542*/                        if(treenode1 == treenode2.right)
                                {
/*2543*/                            treenode = rotateLeft(treenode, treenode1 = treenode2);
/*2544*/                            treenode3 = (treenode2 = treenode1.parent) != null ? treenode2.parent : null;
                                }
/*2546*/                        if(treenode2 != null)
                                {
/*2547*/                            treenode2.red = false;
/*2548*/                            if(treenode3 != null)
                                    {
/*2549*/                                treenode3.red = true;
/*2550*/                                treenode = rotateRight(treenode, treenode3);
                                    }
                                }
                            }
                        } else
/*2555*/                if(treenode4 != null && treenode4.red)
                        {
/*2556*/                    treenode4.red = false;
/*2557*/                    treenode2.red = false;
/*2558*/                    treenode3.red = true;
/*2559*/                    treenode1 = treenode3;
                        } else
                        {
/*2561*/                    if(treenode1 == treenode2.left)
                            {
/*2562*/                        treenode = rotateRight(treenode, treenode1 = treenode2);
/*2563*/                        treenode3 = (treenode2 = treenode1.parent) != null ? treenode2.parent : null;
                            }
/*2565*/                    if(treenode2 != null)
                            {
/*2566*/                        treenode2.red = false;
/*2567*/                        if(treenode3 != null)
                                {
/*2568*/                            treenode3.red = true;
/*2569*/                            treenode = rotateLeft(treenode, treenode3);
                                }
                            }
                        }
                    } while(true);
                }

                static TreeNode balanceDeletion(TreeNode treenode, TreeNode treenode1)
                {
/*2580*/            do
                    {
/*2580*/                if(treenode1 == null || treenode1 == treenode)
/*2581*/                    return treenode;
                        TreeNode treenode2;
/*2582*/                if((treenode2 = treenode1.parent) == null)
                        {
/*2583*/                    treenode1.red = false;
/*2584*/                    return treenode1;
                        }
/*2585*/                if(treenode1.red)
                        {
/*2586*/                    treenode1.red = false;
/*2587*/                    return treenode;
                        }
                        TreeNode treenode3;
/*2588*/                if((treenode3 = treenode2.left) == treenode1)
                        {
/*2589*/                    if((treenode3 = treenode2.right) != null && treenode3.red)
                            {
/*2590*/                        treenode3.red = false;
/*2591*/                        treenode2.red = true;
/*2592*/                        treenode = rotateLeft(treenode, treenode2);
/*2593*/                        treenode3 = (treenode2 = treenode1.parent) != null ? treenode2.right : null;
                            }
/*2595*/                    if(treenode3 == null)
                            {
/*2596*/                        treenode1 = treenode2;
                            } else
                            {
/*2598*/                        TreeNode treenode4 = treenode3.left;
                                TreeNode treenode7;
/*2599*/                        if(((treenode7 = treenode3.right) == null || !treenode7.red) && (treenode4 == null || !treenode4.red))
                                {
/*2602*/                            treenode3.red = true;
/*2603*/                            treenode1 = treenode2;
                                } else
                                {
/*2605*/                            if(treenode7 == null || !treenode7.red)
                                    {
/*2606*/                                if(treenode4 != null)
/*2607*/                                    treenode4.red = false;
/*2608*/                                treenode3.red = true;
/*2609*/                                treenode = rotateRight(treenode, treenode3);
/*2610*/                                treenode3 = (treenode2 = treenode1.parent) != null ? treenode2.right : null;
                                    }
/*2613*/                            if(treenode3 != null)
                                    {
/*2614*/                                treenode3.red = treenode2 != null ? treenode2.red : false;
                                        TreeNode treenode8;
/*2615*/                                if((treenode8 = treenode3.right) != null)
/*2616*/                                    treenode8.red = false;
                                    }
/*2618*/                            if(treenode2 != null)
                                    {
/*2619*/                                treenode2.red = false;
/*2620*/                                treenode = rotateLeft(treenode, treenode2);
                                    }
/*2622*/                            treenode1 = treenode;
                                }
                            }
                        } else
                        {
/*2626*/                    if(treenode3 != null && treenode3.red)
                            {
/*2627*/                        treenode3.red = false;
/*2628*/                        treenode2.red = true;
/*2629*/                        treenode = rotateRight(treenode, treenode2);
/*2630*/                        treenode3 = (treenode2 = treenode1.parent) != null ? treenode2.left : null;
                            }
/*2632*/                    if(treenode3 == null)
                            {
/*2633*/                        treenode1 = treenode2;
                            } else
                            {
/*2635*/                        TreeNode treenode5 = treenode3.left;
/*2636*/                        TreeNode treenode9 = treenode3.right;
/*2637*/                        if((treenode5 == null || !treenode5.red) && (treenode9 == null || !treenode9.red))
                                {
/*2639*/                            treenode3.red = true;
/*2640*/                            treenode1 = treenode2;
                                } else
                                {
/*2642*/                            if(treenode5 == null || !treenode5.red)
                                    {
/*2643*/                                if(treenode9 != null)
/*2644*/                                    treenode9.red = false;
/*2645*/                                treenode3.red = true;
/*2646*/                                treenode = rotateLeft(treenode, treenode3);
/*2647*/                                treenode3 = (treenode2 = treenode1.parent) != null ? treenode2.left : null;
                                    }
/*2650*/                            if(treenode3 != null)
                                    {
/*2651*/                                treenode3.red = treenode2 != null ? treenode2.red : false;
                                        TreeNode treenode6;
/*2652*/                                if((treenode6 = treenode3.left) != null)
/*2653*/                                    treenode6.red = false;
                                    }
/*2655*/                            if(treenode2 != null)
                                    {
/*2656*/                                treenode2.red = false;
/*2657*/                                treenode = rotateRight(treenode, treenode2);
                                    }
/*2659*/                            treenode1 = treenode;
                                }
                            }
                        }
                    } while(true);
                }

                static boolean checkInvariants(TreeNode treenode)
                {
/*2670*/            TreeNode treenode1 = treenode.parent;
/*2671*/            TreeNode treenode2 = treenode.left;
/*2672*/            TreeNode treenode3 = treenode.right;
/*2673*/            TreeNode treenode4 = treenode.prev;
/*2674*/            TreeNode treenode5 = (TreeNode)treenode.next;
/*2675*/            if(treenode4 != null && treenode4.next != treenode)
/*2676*/                return false;
/*2677*/            if(treenode5 != null && treenode5.prev != treenode)
/*2678*/                return false;
/*2679*/            if(treenode1 != null && treenode != treenode1.left && treenode != treenode1.right)
/*2680*/                return false;
/*2681*/            if(treenode2 != null && (treenode2.parent != treenode || treenode2.hash > treenode.hash))
/*2682*/                return false;
/*2683*/            if(treenode3 != null && (treenode3.parent != treenode || treenode3.hash < treenode.hash))
/*2684*/                return false;
/*2685*/            if(treenode.red && treenode2 != null && treenode2.red && treenode3 != null && treenode3.red)
/*2686*/                return false;
/*2687*/            if(treenode2 != null && !checkInvariants(treenode2))
/*2688*/                return false;
/*2689*/            return treenode3 == null || checkInvariants(treenode3);
                }

                TreeNode root;
                volatile TreeNode first;
                volatile Thread waiter;
                volatile int lockState;
                static final int WRITER = 1;
                static final int WAITER = 2;
                static final int READER = 4;
                private static final Unsafe U;
                private static final long LOCKSTATE;
                static final boolean $assertionsDisabled = !org/glassfish/jersey/internal/util/collection/ConcurrentHashMapV8.desiredAssertionStatus();

                static 
                {
/*2699*/            try
                    {
/*2699*/                U = ConcurrentHashMapV8.getUnsafe();
/*2700*/                /*<invalid signature>*/java.lang.Object local = org/glassfish/jersey/internal/util/collection/ConcurrentHashMapV8$TreeBin;
/*2701*/                LOCKSTATE = U.objectFieldOffset(local.getDeclaredField("lockState"));
                    }
/*2703*/            catch(Exception exception)
                    {
/*2704*/                throw new Error(exception);
                    }
                }

                TreeBin(TreeNode treenode)
                {
/*2194*/            super(-2, null, null, null);
/*2195*/            first = treenode;
/*2196*/            TreeNode treenode1 = null;
                    TreeNode treenode2;
/*2197*/            for(treenode = treenode; treenode != null; treenode = treenode2)
                    {
/*2198*/                treenode2 = (TreeNode)treenode.next;
/*2199*/                treenode.left = treenode.right = null;
/*2200*/                if(treenode1 == null)
                        {
/*2201*/                    treenode.parent = null;
/*2202*/                    treenode.red = false;
/*2203*/                    treenode1 = treenode;
/*2203*/                    continue;
                        }
/*2205*/                Object obj = treenode.key;
/*2206*/                int i = treenode.hash;
/*2207*/                Class class1 = null;
/*2208*/                TreeNode treenode3 = treenode1;
                        int j;
                        Object obj1;
/*2211*/                do
                        {
/*2211*/                    obj1 = treenode3.key;
/*2212*/                    if((j = treenode3.hash) > i)
/*2213*/                        j = -1;
/*2214*/                    else
/*2214*/                    if(j < i)
/*2215*/                        j = 1;
/*2216*/                    else
/*2216*/                    if(class1 == null && (class1 = ConcurrentHashMapV8.comparableClassFor(obj)) == null || (j = ConcurrentHashMapV8.compareComparables(class1, obj, obj1)) == 0)
/*2219*/                        j = tieBreakOrder(obj, obj1);
/*2220*/                    obj1 = treenode3;
                        } while((treenode3 = j > 0 ? treenode3.right : treenode3.left) != null);
/*2222*/                treenode.parent = ((TreeNode) (obj1));
/*2223*/                if(j <= 0)
/*2224*/                    obj1.left = treenode;
/*2226*/                else
/*2226*/                    obj1.right = treenode;
/*2227*/                treenode1 = balanceInsertion(treenode1, treenode);
                    }

/*2233*/            root = treenode1;
/*2234*/            if(!$assertionsDisabled && !checkInvariants(root))
/*2234*/                throw new AssertionError();
/*2235*/            else
/*2235*/                return;
                }
    }

    static final class TreeNode extends Node
    {

                final Node find(int i, Object obj)
                {
/*2113*/            return findTreeNode(i, obj, null);
                }

                final TreeNode findTreeNode(int i, Object obj, Class class1)
                {
/*2121*/            if(obj != null)
                    {
/*2122*/                Object obj1 = this;
/*2128*/                do
                        {
/*2128*/                    TreeNode treenode = ((TreeNode) (obj1)).left;
/*2129*/                    TreeNode treenode1 = ((TreeNode) (obj1)).right;
                            int j;
/*2130*/                    if((j = ((TreeNode) (obj1)).hash) <= i)
                            {
/*2132*/                        if(j < i)
                                {
/*2133*/                            obj1 = treenode1;
/*2133*/                            continue;
                                }
                                Object obj2;
/*2134*/                        if((obj2 = ((TreeNode) (obj1)).key) == obj || obj2 != null && obj.equals(obj2))
/*2135*/                            return ((TreeNode) (obj1));
/*2136*/                        if(treenode == null)
                                {
/*2137*/                            obj1 = treenode1;
/*2137*/                            continue;
                                }
/*2138*/                        if(treenode1 != null)
                                {
/*2140*/                            if((class1 != null || (class1 = ConcurrentHashMapV8.comparableClassFor(obj)) != null) && (obj1 = ConcurrentHashMapV8.compareComparables(class1, obj, obj2)) != 0)
                                    {
/*2143*/                                obj1 = obj1 >= 0 ? ((Object) (treenode1)) : ((Object) (treenode));
/*2143*/                                continue;
                                    }
/*2144*/                            if((obj1 = treenode1.findTreeNode(i, obj, class1)) != null)
/*2145*/                                return ((TreeNode) (obj1));
                                }
                            }
/*2147*/                    obj1 = treenode;
                        } while(obj1 != null);
                    }
/*2150*/            return null;
                }

                TreeNode parent;
                TreeNode left;
                TreeNode right;
                TreeNode prev;
                boolean red;

                TreeNode(int i, Object obj, Object obj1, Node node, TreeNode treenode)
                {
/*2108*/            super(i, obj, obj1, node);
/*2109*/            parent = treenode;
                }
    }

    static final class ReservationNode extends Node
    {

                final Node find(int i, Object obj)
                {
/*1754*/            return null;
                }

                ReservationNode()
                {
/*1750*/            super(-3, null, null, null);
                }
    }

    static final class ForwardingNode extends Node
    {

                final Node find(int i, Object obj)
                {
/*1719*/            Object obj1 = nextTable;
/*1722*/label0:
/*1722*/            do
                    {
                        int j;
/*1722*/                if(obj == null || obj1 == null || (j = obj1.length) == 0 || (obj1 = ConcurrentHashMapV8.tabAt(((Node []) (obj1)), j - 1 & i)) == null)
/*1724*/                    return null;
/*1728*/                do
                        {
                            int k;
                            Object obj2;
/*1728*/                    if((k = ((Node) (obj1)).hash) == i && ((obj2 = ((Node) (obj1)).key) == obj || obj2 != null && obj.equals(obj2)))
/*1730*/                        return ((Node) (obj1));
/*1731*/                    if(k >= 0)
/*1732*/                        continue;
/*1732*/                    if(obj1 instanceof ForwardingNode)
/*1733*/                        obj1 = ((ForwardingNode)obj1).nextTable;
/*1736*/                    else
/*1736*/                        return ((Node) (obj1)).find(i, obj);
/*1738*/                    continue label0;
                        } while((obj1 = ((Node) (obj1)).next) != null);
/*1739*/                return null;
                    } while(true);
                }

                final Node nextTable[];

                ForwardingNode(Node anode[])
                {
/*1712*/            super(-1, null, null, null);
/*1713*/            nextTable = anode;
                }
    }

    static class Segment extends ReentrantLock
        implements Serializable
    {

                private static final long serialVersionUID = 0x1f364c905893293dL;
                final float loadFactor;

                Segment(float f)
                {
/*1376*/            loadFactor = f;
                }
    }

    static class Node
        implements java.util.Map.Entry
    {

                public final Object getKey()
                {
/* 600*/            return key;
                }

                public final Object getValue()
                {
/* 604*/            return val;
                }

                public final int hashCode()
                {
/* 608*/            return key.hashCode() ^ val.hashCode();
                }

                public final String toString()
                {
/* 612*/            return (new StringBuilder()).append(key).append("=").append(val).toString();
                }

                public final Object setValue(Object obj)
                {
/* 616*/            throw new UnsupportedOperationException();
                }

                public final boolean equals(Object obj)
                {
                    Object obj1;
/* 624*/            return (obj instanceof java.util.Map.Entry) && (obj = ((java.util.Map.Entry) (obj1 = (java.util.Map.Entry)obj)).getKey()) != null && (obj1 = ((java.util.Map.Entry) (obj1)).getValue()) != null && (obj == key || obj.equals(key)) && (obj1 == (obj = val) || obj1.equals(obj));
                }

                Node find(int i, Object obj)
                {
/* 635*/            Node node = this;
                    Object obj1;
/* 636*/            if(obj != null)
/* 639*/                do
/* 639*/                    if(node.hash == i && ((obj1 = node.key) == obj || obj1 != null && obj.equals(obj1)))
/* 641*/                        return node;
/* 642*/                while((node = node.next) != null);
/* 644*/            return null;
                }

                final int hash;
                final Object key;
                volatile Object val;
                volatile Node next;

                Node(int i, Object obj, Object obj1, Node node)
                {
/* 593*/            hash = i;
/* 594*/            key = obj;
/* 595*/            val = obj1;
/* 596*/            next = node;
                }
    }


            static final int spread(int i)
            {
/* 667*/        return (i ^ i >>> 16) & 0x7fffffff;
            }

            private static final int tableSizeFor(int i)
            {
/* 675*/        if((i = (i = (i = (i = (i = --i | i >>> 1) | i >>> 2) | i >>> 4) | i >>> 8) | i >>> 16) < 0)
/* 681*/            return 1;
/* 681*/        if(i >= 0x40000000)
/* 681*/            return 0x40000000;
/* 681*/        else
/* 681*/            return i + 1;
            }

            static Class comparableClassFor(Object obj)
            {
/* 689*/        if(obj instanceof Comparable)
                {
/* 695*/            if((obj = obj.getClass()) == java/lang/String)
/* 696*/                return ((Class) (obj));
                    java.lang.reflect.Type atype[];
/* 697*/            if((atype = ((Class) (obj)).getGenericInterfaces()) != null)
                    {
/* 698*/                for(int i = 0; i < atype.length; i++)
                        {
                            Object obj1;
                            java.lang.reflect.Type atype1[];
/* 699*/                    if(((obj1 = atype[i]) instanceof ParameterizedType) && ((ParameterizedType) (obj1 = (ParameterizedType)obj1)).getRawType() == java/lang/Comparable && (atype1 = ((ParameterizedType) (obj1)).getActualTypeArguments()) != null && atype1.length == 1 && atype1[0] == obj)
/* 704*/                        return ((Class) (obj));
                        }

                    }
                }
/* 708*/        return null;
            }

            static int compareComparables(Class class1, Object obj, Object obj1)
            {
/* 717*/        if(obj1 == null || obj1.getClass() != class1)
/* 717*/            return 0;
/* 717*/        else
/* 717*/            return ((Comparable)obj).compareTo(obj1);
            }

            static final Node tabAt(Node anode[], int i)
            {
/* 741*/        return (Node)U.getObjectVolatile(anode, ((long)i << ASHIFT) + ABASE);
            }

            static final boolean casTabAt(Node anode[], int i, Node node, Node node1)
            {
/* 746*/        return U.compareAndSwapObject(anode, ((long)i << ASHIFT) + ABASE, node, node1);
            }

            static final void setTabAt(Node anode[], int i, Node node)
            {
/* 750*/        U.putObjectVolatile(anode, ((long)i << ASHIFT) + ABASE, node);
            }

            ConcurrentHashMapV8()
            {
            }

            ConcurrentHashMapV8(int i)
            {
/* 828*/        if(i < 0)
                {
/* 829*/            throw new IllegalArgumentException();
                } else
                {
/* 830*/            i = i < 0x20000000 ? tableSizeFor(i + (i >>> 1) + 1) : 0x40000000;
/* 833*/            sizeCtl = i;
/* 834*/            return;
                }
            }

            ConcurrentHashMapV8(Map map)
            {
/* 842*/        sizeCtl = 16;
/* 843*/        putAll(map);
            }

            ConcurrentHashMapV8(int i, float f)
            {
/* 861*/        this(i, f, 1);
            }

            ConcurrentHashMapV8(int i, float f, int j)
            {
/* 884*/        if(f <= 0.0F || i < 0 || j <= 0)
/* 885*/            throw new IllegalArgumentException();
/* 886*/        if(i < j)
/* 887*/            i = j;
                long l;
/* 888*/        i = (l = (long)(1.0D + (double)((float)(long)i / f))) < 0x40000000L ? tableSizeFor((int)l) : 0x40000000;
/* 891*/        sizeCtl = i;
            }

            public int size()
            {
                long l;
/* 900*/        if((l = sumCount()) < 0L)
/* 901*/            return 0;
/* 901*/        if(l > 0x7fffffffL)
/* 901*/            return 0x7fffffff;
/* 901*/        else
/* 901*/            return (int)l;
            }

            public boolean isEmpty()
            {
/* 910*/        return sumCount() <= 0L;
            }

            public Object get(Object obj)
            {
/* 931*/label0:
                {
/* 931*/            int j = spread(obj.hashCode());
                    Node anode[];
                    Node node;
                    int i;
/* 932*/            if((anode = table) == null || (i = anode.length) <= 0 || (node = tabAt(anode, i - 1 & j)) == null)
/* 934*/                break label0;
                    Object obj1;
/* 934*/            if((obj1 = node.hash) == j)
                    {
/* 935*/                if((obj1 = node.key) == obj || obj1 != null && obj.equals(obj1))
/* 936*/                    return node.val;
                    } else
/* 937*/            if(obj1 < 0)
/* 938*/                if((obj = node.find(j, obj)) != null)
/* 938*/                    return ((Node) (obj)).val;
/* 938*/                else
/* 938*/                    return null;
/* 939*/            do
/* 939*/                if((node = node.next) == null)
/* 940*/                    break label0;
/* 940*/            while(node.hash != j || (obj1 = node.key) != obj && (obj1 == null || !obj.equals(obj1)));
/* 942*/            return node.val;
                }
/* 945*/        return null;
            }

            public boolean containsKey(Object obj)
            {
/* 958*/        return get(obj) != null;
            }

            public boolean containsValue(Object obj)
            {
/* 972*/label0:
                {
/* 972*/            if(obj == null)
/* 973*/                throw new NullPointerException();
                    Node anode[];
/* 975*/            if((anode = table) == null)
/* 976*/                break label0;
/* 976*/            Traverser traverser = new Traverser(anode, anode.length, 0, anode.length);
                    Object obj1;
/* 977*/            do
/* 977*/                if((obj1 = traverser.advance()) == null)
/* 979*/                    break label0;
/* 979*/            while((obj1 = ((Node) (obj1)).val) != obj && (obj1 == null || !obj.equals(obj1)));
/* 980*/            return true;
                }
/* 983*/        return false;
            }

            public Object put(Object obj, Object obj1)
            {
/*1000*/        return putVal(obj, obj1, false);
            }

            final Object putVal(Object obj, Object obj1, boolean flag)
            {
/*1007*/        if(obj == null || obj1 == null)
/*1007*/            throw new NullPointerException();
/*1008*/        int i = spread(obj.hashCode());
/*1009*/        int j = 0;
/*1010*/        do
                {
                    Node anode[];
                    int k;
/*1010*/            for(anode = table; anode == null || (k = anode.length) == 0; anode = initTable());
                    Object obj2;
                    int l;
/*1017*/            if((obj2 = tabAt(anode, l = k - 1 & i)) == null)
                    {
/*1018*/                if(casTabAt(anode, l, null, new Node(i, obj, obj1, null)))
/*1020*/                    break;
/*1021*/                continue;
                    }
                    int i1;
/*1021*/            if((i1 = ((Node) (obj2)).hash) == -1)
                    {
/*1022*/                anode = helpTransfer(anode, ((Node) (obj2)));
/*1022*/                continue;
                    }
/*1024*/            Object obj4 = null;
/*1025*/            synchronized(obj2)
                    {
/*1026*/                if(tabAt(anode, l) == obj2)
/*1027*/                    if(i1 >= 0)
                            {
/*1028*/                        j = 1;
/*1029*/                        obj2 = obj2;
/*1031*/                        do
                                {
                                    Object obj3;
/*1031*/                            if(((Node) (obj2)).hash == i && ((obj3 = ((Node) (obj2)).key) == obj || obj3 != null && obj.equals(obj3)))
                                    {
/*1034*/                                obj4 = ((Node) (obj2)).val;
/*1035*/                                if(!flag)
/*1036*/                                    obj2.val = obj1;
/*1036*/                                break;
                                    }
/*1039*/                            obj3 = obj2;
/*1040*/                            if((obj2 = ((Node) (obj2)).next) == null)
                                    {
/*1041*/                                obj3.next = new Node(i, obj, obj1, null);
/*1043*/                                break;
                                    }
/*1029*/                            j++;
                                } while(true);
                            } else
/*1046*/                    if(obj2 instanceof TreeBin)
                            {
/*1048*/                        j = 2;
/*1049*/                        if((obj2 = ((TreeBin)obj2).putTreeVal(i, obj, obj1)) != null)
                                {
/*1051*/                            obj4 = ((Node) (obj2)).val;
/*1052*/                            if(!flag)
/*1053*/                                obj2.val = obj1;
                                }
                            }
                    }
/*1058*/            if(j == 0)
/*1059*/                continue;
/*1059*/            if(j >= 8)
/*1060*/                treeifyBin(anode, l);
/*1061*/            if(obj4 != null)
/*1062*/                return obj4;
/*1066*/            break;
                } while(true);
/*1067*/        addCount(1L, j);
/*1068*/        return null;
            }

            public void putAll(Map map)
            {
/*1079*/        tryPresize(map.size());
                java.util.Map.Entry entry;
/*1080*/        for(map = map.entrySet().iterator(); map.hasNext(); putVal(entry.getKey(), entry.getValue(), false))
/*1080*/            entry = (java.util.Map.Entry)map.next();

            }

            public Object remove(Object obj)
            {
/*1094*/        return replaceNode(obj, null, null);
            }

            final Object replaceNode(Object obj, Object obj1, Object obj2)
            {
/*1103*/        int i = spread(obj.hashCode());
/*1104*/        Node anode[] = table;
/*1109*/        do
                {
                    int j;
                    Object obj3;
                    int k;
/*1109*/            if(anode == null || (j = anode.length) == 0 || (obj3 = tabAt(anode, k = j - 1 & i)) == null)
/*1112*/                break;
                    int l;
/*1112*/            if((l = ((Node) (obj3)).hash) == -1)
                    {
/*1113*/                anode = helpTransfer(anode, ((Node) (obj3)));
/*1113*/                continue;
                    }
/*1115*/            Object obj4 = null;
/*1116*/            boolean flag = false;
/*1117*/            synchronized(obj3)
                    {
/*1118*/                if(tabAt(anode, k) == obj3)
/*1119*/                    if(l >= 0)
                            {
/*1120*/                        flag = true;
/*1121*/                        obj3 = obj3;
/*1121*/                        Node node = null;
/*1123*/                        do
                                {
                                    Object obj5;
/*1123*/                            if(((Node) (obj3)).hash == i && ((obj5 = ((Node) (obj3)).key) == obj || obj5 != null && obj.equals(obj5)))
                                    {
/*1126*/                                Object obj6 = ((Node) (obj3)).val;
/*1127*/                                if(obj2 == null || obj2 == obj6 || obj6 != null && obj2.equals(obj6))
                                        {
/*1129*/                                    obj4 = obj6;
/*1130*/                                    if(obj1 != null)
/*1131*/                                        obj3.val = obj1;
/*1132*/                                    else
/*1132*/                                    if(node != null)
/*1133*/                                        node.next = ((Node) (obj3)).next;
/*1135*/                                    else
/*1135*/                                        setTabAt(anode, k, ((Node) (obj3)).next);
                                        }
/*1135*/                                break;
                                    }
/*1139*/                            node = ((Node) (obj3));
                                } while((obj3 = ((Node) (obj3)).next) != null);
                            } else
/*1143*/                    if(obj3 instanceof TreeBin)
                            {
/*1144*/                        flag = true;
                                TreeNode treenode;
                                TreeNode treenode1;
/*1145*/                        if((treenode = ((TreeBin) (obj3 = (TreeBin)obj3)).root) != null && (treenode1 = treenode.findTreeNode(i, obj, null)) != null)
                                {
/*1150*/                            Object obj7 = treenode1.val;
/*1151*/                            if(obj2 == null || obj2 == obj7 || obj7 != null && obj2.equals(obj7))
                                    {
/*1153*/                                obj4 = obj7;
/*1154*/                                if(obj1 != null)
/*1155*/                                    treenode1.val = obj1;
/*1156*/                                else
/*1156*/                                if(((TreeBin) (obj3)).removeTreeNode(treenode1))
/*1157*/                                    setTabAt(anode, k, untreeify(((TreeBin) (obj3)).first));
                                    }
                                }
                            }
                    }
/*1163*/            if(!flag)
/*1164*/                continue;
/*1164*/            if(obj4 != null)
                    {
/*1165*/                if(obj1 == null)
/*1166*/                    addCount(-1L, -1);
/*1167*/                return obj4;
                    }
/*1172*/            break;
                } while(true);
/*1173*/        return null;
            }

            public void clear()
            {
                long l;
                int i;
                Node anode[];
/*1180*/        l = 0L;
/*1181*/        i = 0;
/*1182*/        anode = table;
_L5:
                int j;
                Node node;
/*1183*/        do
                {
/*1183*/            if(anode == null || i >= anode.length)
/*1185*/                break MISSING_BLOCK_LABEL_154;
/*1185*/            if((node = tabAt(anode, i)) == null)
                    {
/*1187*/                i++;
                    } else
                    {
/*1188*/label0:
                        {
/*1188*/                    if((j = node.hash) != -1)
/*1189*/                        break label0;
/*1189*/                    anode = helpTransfer(anode, node);
/*1190*/                    i = 0;
                        }
                    }
                } while(true);
/*1192*/        JVM INSTR monitorenter ;
/*1193*/        if(tabAt(anode, i) != node)
/*1194*/            break MISSING_BLOCK_LABEL_142;
/*1194*/        j < 0 ? (node instanceof TreeBin) ? ((TreeBin)node).first : null : node;
_L3:
/*1194*/        JVM INSTR dup ;
                Object obj;
/*1197*/        obj;
/*1197*/        JVM INSTR ifnull 132;
                   goto _L1 _L2
_L1:
/*1198*/        l--;
/*1199*/        ((Node) (obj)).next;
                  goto _L3
_L2:
/*1201*/        setTabAt(anode, i++, null);
/*1203*/        if(true) goto _L5; else goto _L4
_L4:
/*1203*/        l;
/*1203*/        throw l;
/*1206*/        if(l != 0L)
/*1207*/            addCount(l, -1);
/*1208*/        return;
            }

            public KeySetView keySet()
            {
                KeySetView keysetview;
/*1230*/        if((keysetview = keySet) != null)
/*1230*/            return keysetview;
/*1230*/        else
/*1230*/            return keySet = new KeySetView(this, null);
            }

            public Collection values()
            {
                ValuesView valuesview;
/*1253*/        if((valuesview = values) != null)
/*1253*/            return valuesview;
/*1253*/        else
/*1253*/            return values = new ValuesView(this);
            }

            public Set entrySet()
            {
                EntrySetView entrysetview;
/*1275*/        if((entrysetview = entrySet) != null)
/*1275*/            return entrysetview;
/*1275*/        else
/*1275*/            return entrySet = new EntrySetView(this);
            }

            public int hashCode()
            {
/*1286*/        int i = 0;
                Node anode[];
/*1288*/        if((anode = table) != null)
                {
/*1289*/            Traverser traverser = new Traverser(anode, anode.length, 0, anode.length);
                    Node node;
/*1290*/            while((node = traverser.advance()) != null) 
/*1291*/                i += node.key.hashCode() ^ node.val.hashCode();
                }
/*1293*/        return i;
            }

            public String toString()
            {
                Node anode[];
/*1309*/        int i = (anode = table) != null ? anode.length : 0;
/*1310*/        Traverser traverser = new Traverser(anode, i, 0, i);
                StringBuilder stringbuilder;
/*1311*/        (stringbuilder = new StringBuilder()).append('{');
                Object obj;
/*1314*/        if((obj = traverser.advance()) != null)
/*1316*/            do
                    {
/*1316*/                Object obj1 = ((Node) (obj)).key;
/*1317*/                obj = ((Node) (obj)).val;
/*1318*/                stringbuilder.append(obj1 != this ? obj1 : "(this Map)");
/*1319*/                stringbuilder.append('=');
/*1320*/                stringbuilder.append(obj != this ? obj : "(this Map)");
/*1321*/                if((obj = traverser.advance()) == null)
/*1323*/                    break;
/*1323*/                stringbuilder.append(',').append(' ');
                    } while(true);
/*1326*/        return stringbuilder.append('}').toString();
            }

            public boolean equals(Object obj)
            {
/*1340*/label0:
                {
/*1340*/            if(obj == this)
/*1341*/                break label0;
/*1341*/            if(!(obj instanceof Map))
/*1342*/                return false;
/*1343*/            obj = (Map)obj;
                    Node anode[];
/*1345*/            int i = (anode = table) != null ? anode.length : 0;
/*1346*/            Object obj1 = new Traverser(anode, i, 0, i);
                    Object obj2;
/*1347*/            while((obj2 = ((Traverser) (obj1)).advance()) != null) 
                    {
/*1348*/                Object obj3 = ((Node) (obj2)).val;
                        Object obj4;
/*1349*/                if((obj4 = ((Map) (obj)).get(((Node) (obj2)).key)) == null || obj4 != obj3 && !obj4.equals(obj3))
/*1351*/                    return false;
                    }
/*1353*/            obj2 = ((Map) (obj)).entrySet().iterator();
                    java.util.Map.Entry entry;
                    Object obj5;
/*1353*/            do
/*1353*/                if(!((Iterator) (obj2)).hasNext())
/*1353*/                    break label0;
/*1359*/            while((obj5 = (entry = (java.util.Map.Entry)((Iterator) (obj2)).next()).getKey()) != null && (obj = entry.getValue()) != null && (obj1 = get(obj5)) != null && (obj == obj1 || obj.equals(obj1)));
/*1361*/            return false;
                }
/*1364*/        return true;
            }

            private void writeObject(ObjectOutputStream objectoutputstream)
                throws IOException
            {
/*1402*/        Segment asegment[] = (Segment[])new Segment[16];
/*1404*/        for(int i = 0; i < asegment.length; i++)
/*1405*/            asegment[i] = new Segment(0.75F);

/*1406*/        objectoutputstream.putFields().put("segments", asegment);
/*1407*/        objectoutputstream.putFields().put("segmentShift", 28);
/*1408*/        objectoutputstream.putFields().put("segmentMask", 15);
/*1409*/        objectoutputstream.writeFields();
                Node anode[];
/*1412*/        if((anode = table) != null)
                {
/*1413*/            Traverser traverser = new Traverser(anode, anode.length, 0, anode.length);
                    Node node;
/*1414*/            while((node = traverser.advance()) != null) 
                    {
/*1415*/                objectoutputstream.writeObject(node.key);
/*1416*/                objectoutputstream.writeObject(node.val);
                    }
                }
/*1419*/        objectoutputstream.writeObject(null);
/*1420*/        objectoutputstream.writeObject(null);
            }

            private void readObject(ObjectInputStream objectinputstream)
                throws IOException, ClassNotFoundException
            {
/*1441*/        sizeCtl = -1;
/*1442*/        objectinputstream.defaultReadObject();
/*1443*/        long l = 0L;
/*1444*/        Node node1 = null;
/*1446*/        do
                {
/*1446*/            Object obj = objectinputstream.readObject();
/*1447*/            Object obj1 = objectinputstream.readObject();
/*1448*/            if(obj == null || obj1 == null)
/*1449*/                break;
/*1449*/            node1 = new Node(spread(obj.hashCode()), obj, obj1, node1);
/*1450*/            l++;
                } while(true);
/*1454*/        if(l == 0L)
                {
/*1455*/            sizeCtl = 0;
/*1455*/            return;
                }
                int i;
                int j;
/*1458*/        if(l >= 0x20000000L)
/*1459*/            i = 0x40000000;
/*1461*/        else
/*1461*/            i = tableSizeFor((j = (int)l) + (j >>> 1) + 1);
/*1465*/        Node anode[] = (Node[])new Node[i];
/*1466*/        objectinputstream = i - 1;
/*1467*/        long l1 = 0L;
                Node node;
/*1468*/        for(; node1 != null; node1 = node)
                {
/*1470*/            node = node1.next;
                    int k;
/*1472*/            int i1 = (k = node1.hash) & objectinputstream;
                    boolean flag;
                    Node node2;
/*1474*/            if((node2 = tabAt(anode, i1)) == null)
                    {
/*1475*/                flag = true;
                    } else
                    {
/*1477*/                Object obj2 = node1.key;
/*1478*/                if(node2.hash < 0)
                        {
                            TreeBin treebin;
/*1479*/                    if((treebin = (TreeBin)node2).putTreeVal(k, obj2, node1.val) == null)
/*1481*/                        l1++;
/*1482*/                    flag = false;
                        } else
                        {
/*1484*/                    int j1 = 0;
/*1485*/                    flag = true;
/*1488*/                    Node node3 = node2;
/*1488*/                    do
                            {
/*1488*/                        if(node3 == null)
/*1489*/                            break;
                                Object obj3;
/*1489*/                        if(node3.hash == k && ((obj3 = node3.key) == obj2 || obj3 != null && obj2.equals(obj3)))
                                {
/*1492*/                            flag = false;
/*1493*/                            break;
                                }
/*1495*/                        j1++;
/*1488*/                        node3 = node3.next;
                            } while(true);
/*1497*/                    if(flag && j1 >= 8)
                            {
/*1498*/                        flag = false;
/*1499*/                        l1++;
/*1500*/                        node1.next = node2;
/*1501*/                        TreeNode treenode = null;
/*1501*/                        TreeNode treenode1 = null;
/*1502*/                        for(Node node4 = node1; node4 != null; node4 = node4.next)
                                {
                                    TreeNode treenode2;
/*1503*/                            if(((treenode2 = new TreeNode(node4.hash, node4.key, node4.val, null, null)).prev = treenode1) == null)
/*1506*/                                treenode = treenode2;
/*1508*/                            else
/*1508*/                                treenode1.next = treenode2;
/*1509*/                            treenode1 = treenode2;
                                }

/*1511*/                        setTabAt(anode, i1, new TreeBin(treenode));
                            }
                        }
                    }
/*1515*/            if(flag)
                    {
/*1516*/                l1++;
/*1517*/                node1.next = node2;
/*1518*/                setTabAt(anode, i1, node1);
                    }
                }

/*1522*/        table = anode;
/*1523*/        sizeCtl = i - (i >>> 2);
/*1524*/        baseCount = l1;
            }

            public Object putIfAbsent(Object obj, Object obj1)
            {
/*1538*/        return putVal(obj, obj1, true);
            }

            public boolean remove(Object obj, Object obj1)
            {
/*1547*/        if(obj == null)
/*1548*/            throw new NullPointerException();
/*1549*/        return obj1 != null && replaceNode(obj, null, obj1) != null;
            }

            public boolean replace(Object obj, Object obj1, Object obj2)
            {
/*1558*/        if(obj == null || obj1 == null || obj2 == null)
/*1559*/            throw new NullPointerException();
/*1560*/        return replaceNode(obj, obj2, obj1) != null;
            }

            public Object replace(Object obj, Object obj1)
            {
/*1571*/        if(obj == null || obj1 == null)
/*1572*/            throw new NullPointerException();
/*1573*/        else
/*1573*/            return replaceNode(obj, obj1, null);
            }

            public Object getOrDefault(Object obj, Object obj1)
            {
/*1591*/        if((obj = get(obj)) == null)
/*1591*/            return obj1;
/*1591*/        else
/*1591*/            return obj;
            }

            /**
             * @deprecated Method contains is deprecated
             */

            public boolean contains(Object obj)
            {
/*1614*/        return containsValue(obj);
            }

            public Enumeration keys()
            {
                Node anode[];
/*1625*/        int i = (anode = table) != null ? anode.length : 0;
/*1626*/        return new KeyIterator(anode, i, 0, i, this);
            }

            public Enumeration elements()
            {
                Node anode[];
/*1637*/        int i = (anode = table) != null ? anode.length : 0;
/*1638*/        return new ValueIterator(anode, i, 0, i, this);
            }

            public long mappingCount()
            {
                long l;
/*1654*/        if((l = sumCount()) < 0L)
/*1655*/            return 0L;
/*1655*/        else
/*1655*/            return l;
            }

            public static KeySetView newKeySet()
            {
/*1666*/        return new KeySetView(new ConcurrentHashMapV8(), Boolean.TRUE);
            }

            public static KeySetView newKeySet(int i)
            {
/*1682*/        return new KeySetView(new ConcurrentHashMapV8(i), Boolean.TRUE);
            }

            public KeySetView keySet(Object obj)
            {
/*1698*/        if(obj == null)
/*1699*/            throw new NullPointerException();
/*1700*/        else
/*1700*/            return new KeySetView(this, obj);
            }

            private final Node[] initTable()
            {
                Object obj;
                int i;
/*1766*/label0:
/*1766*/        do
                {
/*1766*/            while((obj = table) == null || obj.length == 0) 
                    {
/*1767*/                if((i = sizeCtl) >= 0)
/*1768*/                    continue label0;
/*1768*/                Thread.yield();
                    }
/*1768*/            break MISSING_BLOCK_LABEL_106;
                } while(!U.compareAndSwapInt(this, SIZECTL, i, -1));
/*1771*/        if((obj = table) == null || obj.length == 0)
                {
                    int j;
/*1772*/            obj = (Node[])new Node[j = i <= 0 ? 16 : i];
/*1775*/            table = ((Node []) (obj = obj));
/*1776*/            i = j - (j >>> 2);
                }
/*1779*/        sizeCtl = i;
/*1780*/        break MISSING_BLOCK_LABEL_106;
/*1779*/        obj;
/*1779*/        sizeCtl = i;
/*1779*/        throw obj;
/*1784*/        return ((Node []) (obj));
            }

            private final void addCount(long l, int i)
            {
                boolean flag;
                long l3;
                boolean flag1;
/*1801*/label0:
                {
/*1801*/label1:
                    {
                        CounterCell acountercell[];
/*1801*/                if((acountercell = counterCells) == null)
                        {
/*1801*/                    long l1 = baseCount;
/*1801*/                    l3 = baseCount + l;
/*1801*/                    flag = l3;
/*1801*/                    if(U.compareAndSwapLong(this, BASECOUNT, baseCount, l3))
/*1807*/                        break label0;
                        }
/*1807*/                flag = true;
                        CounterCell countercell;
                        CounterHashCode counterhashcode;
                        int j;
/*1808*/                if((counterhashcode = (CounterHashCode)threadCounterHashCode.get()) != null && acountercell != null && (j = acountercell.length - 1) >= 0 && (countercell = acountercell[j & counterhashcode.code]) != null)
                        {
/*1808*/                    long l2 = countercell.value;
/*1808*/                    flag1 = U.compareAndSwapLong(countercell, CELLVALUE, countercell.value, countercell.value + l);
/*1812*/                    flag = flag1;
/*1812*/                    if(flag1)
/*1813*/                        break label1;
                        }
/*1813*/                fullAddCount(l, counterhashcode, flag);
/*1814*/                return;
                    }
/*1816*/            if(i <= 1)
/*1817*/                return;
/*1818*/            flag = sumCount();
                }
/*1820*/        if(i >= 0)
                {
                    Node anode1[];
                    int k;
/*1823*/            for(; flag >= (long)(k = sizeCtl) && (anode1 = table) != null && anode1.length < 0x40000000; flag = sumCount())
                    {
/*1825*/                if(k < 0)
                        {
                            Node anode[];
/*1826*/                    if(k == -1 || transferIndex <= transferOrigin || (anode = nextTable) == null)
/*1829*/                        break;
/*1829*/                    if(U.compareAndSwapInt(this, SIZECTL, k, k - 1))
/*1830*/                        transfer(anode1, anode);
/*1830*/                    continue;
                        }
/*1831*/                if(U.compareAndSwapInt(this, SIZECTL, k, -2))
/*1832*/                    transfer(anode1, null);
                    }

                }
            }

            final Node[] helpTransfer(Node anode[], Node node)
            {
/*1844*/        if((node instanceof ForwardingNode) && (node = ((ForwardingNode)node).nextTable) != null)
                {
                    int i;
/*1846*/            if(node == nextTable && anode == table && transferIndex > transferOrigin && (i = sizeCtl) < -1 && U.compareAndSwapInt(this, SIZECTL, i, i - 1))
/*1849*/                transfer(anode, node);
/*1850*/            return node;
                } else
                {
/*1852*/            return table;
                }
            }

            private final void tryPresize(int i)
            {
/*1861*/        i = i < 0x20000000 ? tableSizeFor(i + (i >>> 1) + 1) : 0x40000000;
_L2:
                int j;
                Node anode[];
                int k;
/*1864*/        if((j = sizeCtl) < 0)
/*1865*/            break; /* Loop/switch isn't completed */
/*1865*/        if((anode = table) != null && (k = anode.length) != 0)
/*1868*/            break MISSING_BLOCK_LABEL_121;
/*1868*/        k = j <= i ? i : j;
/*1869*/        if(!U.compareAndSwapInt(this, SIZECTL, j, -1))
/*1871*/            continue; /* Loop/switch isn't completed */
/*1871*/        if(table == anode)
                {
/*1873*/            anode = (Node[])new Node[k];
/*1874*/            table = anode;
/*1875*/            j = k - (k >>> 2);
                }
/*1878*/        sizeCtl = j;
/*1879*/        continue; /* Loop/switch isn't completed */
/*1878*/        i;
/*1878*/        sizeCtl = j;
/*1878*/        throw i;
/*1881*/        if(i <= j || k >= 0x40000000)
/*1883*/            break; /* Loop/switch isn't completed */
/*1883*/        if(anode == table && U.compareAndSwapInt(this, SIZECTL, j, -2))
/*1885*/            transfer(anode, null);
/*1886*/        if(true) goto _L2; else goto _L1
_L1:
            }

            private final void transfer(Node anode[], Node anode1[])
            {
/*1894*/        int i = anode.length;
                int j;
/*1896*/        if((j = NCPU <= 1 ? i : (i >>> 3) / NCPU) < 16)
/*1897*/            j = 16;
/*1898*/        if(anode1 == null)
                {
                    Node anode2[];
/*1901*/            try
                    {
/*1901*/                anode1 = anode2 = (Node[])new Node[i << 1];
                    }
/*1903*/            catch(Throwable _ex)
                    {
/*1904*/                sizeCtl = 0x7fffffff;
/*1905*/                return;
                    }
/*1907*/            nextTable = anode1;
/*1908*/            transferOrigin = i;
/*1909*/            transferIndex = i;
/*1910*/            ForwardingNode forwardingnode = new ForwardingNode(anode);
/*1911*/            for(int l = i; l > 0;)
                    {
                        int i1;
/*1912*/                for(int j1 = i1 = l <= j ? 0 : l - j; j1 < l; j1++)
/*1914*/                    anode1[j1] = forwardingnode;

/*1915*/                for(int k1 = i + i1; k1 < i + l; k1++)
/*1916*/                    anode1[k1] = forwardingnode;

/*1917*/                U.putOrderedInt(this, TRANSFERORIGIN, l = i1);
                    }

                }
/*1920*/        int k = anode1.length;
/*1921*/        ForwardingNode forwardingnode1 = new ForwardingNode(anode1);
/*1922*/        Object obj = 1;
/*1923*/        boolean flag = false;
/*1924*/        int l1 = 0;
/*1924*/        int i2 = 0;
                int j2;
                int k2;
                Object obj3;
/*1929*/        do
/*1929*/            if(obj != 0)
                    {
/*1930*/                if(--l1 >= i2 || flag)
/*1931*/                    obj = 0;
/*1932*/                else
/*1932*/                if((j2 = transferIndex) <= transferOrigin)
                        {
/*1933*/                    l1 = -1;
/*1934*/                    obj = 0;
                        } else
/*1935*/                if(U.compareAndSwapInt(this, TRANSFERINDEX, j2, k2 = j2 <= j ? 0 : j2 - j))
                        {
/*1939*/                    i2 = k2;
/*1940*/                    l1 = j2 - 1;
/*1941*/                    obj = 0;
                        }
                    } else
/*1944*/            if(l1 < 0 || l1 >= i || l1 + i >= k)
                    {
/*1945*/                if(flag)
                        {
/*1946*/                    nextTable = null;
/*1947*/                    table = anode1;
/*1948*/                    sizeCtl = (i << 1) - (i >>> 1);
/*1949*/                    return;
                        }
                        int l2;
/*1952*/                while(!U.compareAndSwapInt(this, SIZECTL, l2 = sizeCtl, ++l2)) ;
/*1953*/                if(l2 != -1)
/*1954*/                    return;
/*1955*/                flag = true; obj = 1;
/*1956*/                l1 = i;
                    } else
/*1960*/            if((obj3 = tabAt(anode, l1)) == null)
                    {
/*1961*/                if(casTabAt(anode, l1, null, forwardingnode1))
                        {
/*1962*/                    setTabAt(anode1, l1, null);
/*1963*/                    setTabAt(anode1, l1 + i, null);
/*1964*/                    obj = 1;
                        }
                    } else
/*1966*/            if((j2 = ((Node) (obj3)).hash) == -1)
/*1967*/                obj = 1;
/*1969*/            else
/*1969*/                synchronized(obj3)
                        {
/*1970*/                    if(tabAt(anode, l1) == obj3)
/*1972*/                        if(j2 >= 0)
                                {
/*1973*/                            j2 &= i;
/*1974*/                            Object obj5 = obj3;
/*1975*/                            for(Node node = ((Node) (obj3)).next; node != null; node = node.next)
                                    {
                                        int i3;
/*1976*/                                if((i3 = node.hash & i) != j2)
                                        {
/*1978*/                                    j2 = i3;
/*1979*/                                    obj5 = node;
                                        }
                                    }

                                    Object obj1;
/*1982*/                            if(j2 == 0)
                                    {
/*1983*/                                obj = obj5;
/*1984*/                                obj1 = null;
                                    } else
                                    {
/*1986*/                                obj1 = obj5;
/*1987*/                                obj = null;
                                    }
/*1989*/                            for(Object obj6 = obj3; obj6 != obj5; obj6 = ((Node) (obj6)).next)
                                    {
/*1990*/                                int j3 = ((Node) (obj6)).hash;
/*1991*/                                obj3 = ((Node) (obj6)).key;
/*1992*/                                Object obj7 = ((Node) (obj6)).val;
/*1993*/                                if((j3 & i) == 0)
/*1994*/                                    obj = new Node(j3, obj3, obj7, ((Node) (obj)));
/*1996*/                                else
/*1996*/                                    obj1 = new Node(j3, obj3, obj7, ((Node) (obj1)));
                                    }

/*1998*/                            setTabAt(anode1, l1, ((Node) (obj)));
/*1999*/                            setTabAt(anode1, l1 + i, ((Node) (obj1)));
/*2000*/                            setTabAt(anode, l1, forwardingnode1);
/*2001*/                            obj = 1;
                                } else
/*2002*/                        if(obj3 instanceof TreeBin)
                                {
/*2003*/                            Object obj2 = (TreeBin)obj3;
/*2004*/                            TreeNode treenode = null;
/*2004*/                            TreeNode treenode1 = null;
/*2005*/                            TreeNode treenode2 = null;
/*2005*/                            obj3 = null;
/*2006*/                            int k3 = 0;
/*2006*/                            int l3 = 0;
/*2007*/                            for(obj = ((TreeBin) (obj2)).first; obj != null; obj = ((Node) (obj)).next)
                                    {
/*2008*/                                int i4 = ((Node) (obj)).hash;
/*2009*/                                TreeNode treenode3 = new TreeNode(i4, ((Node) (obj)).key, ((Node) (obj)).val, null, null);
/*2011*/                                if((i4 & i) == 0)
                                        {
/*2012*/                                    if((treenode3.prev = treenode1) == null)
/*2013*/                                        treenode = treenode3;
/*2015*/                                    else
/*2015*/                                        treenode1.next = treenode3;
/*2016*/                                    treenode1 = treenode3;
/*2017*/                                    k3++;
/*2017*/                                    continue;
                                        }
/*2019*/                                if((treenode3.prev = ((TreeNode) (obj3))) == null)
/*2020*/                                    treenode2 = treenode3;
/*2022*/                                else
/*2022*/                                    obj3.next = treenode3;
/*2023*/                                obj3 = treenode3;
/*2024*/                                l3++;
                                    }

/*2027*/                            obj = k3 > 6 ? ((Object) (l3 == 0 ? obj2 : ((Object) (new TreeBin(treenode))))) : ((Object) (untreeify(treenode)));
/*2029*/                            obj2 = l3 > 6 ? ((Object) (k3 == 0 ? obj2 : ((Object) (new TreeBin(treenode2))))) : ((Object) (untreeify(treenode2)));
/*2031*/                            setTabAt(anode1, l1, ((Node) (obj)));
/*2032*/                            setTabAt(anode1, l1 + i, ((Node) (obj2)));
/*2033*/                            setTabAt(anode, l1, forwardingnode1);
/*2034*/                            obj = 1;
                                }
                        }
/*2037*/        while(true);
            }

            private final void treeifyBin(Node anode[], int i)
            {
                Node node;
/*2052*/        if(anode != null)
/*2053*/            if(anode.length < 64)
                    {
/*2054*/                if(anode == table && (i = sizeCtl) >= 0 && U.compareAndSwapInt(this, SIZECTL, i, -2))
                        {
/*2056*/                    transfer(anode, null);
/*2056*/                    return;
                        }
                    } else
/*2057*/            if((node = tabAt(anode, i)) != null && node.hash >= 0)
                    {
/*2058*/                synchronized(node)
                        {
/*2059*/                    if(tabAt(anode, i) == node)
                            {
/*2060*/                        TreeNode treenode = null;
/*2060*/                        TreeNode treenode1 = null;
/*2061*/                        for(node = node; node != null; node = node.next)
                                {
                                    TreeNode treenode2;
/*2062*/                            if(((treenode2 = new TreeNode(node.hash, node.key, node.val, null, null)).prev = treenode1) == null)
/*2066*/                                treenode = treenode2;
/*2068*/                            else
/*2068*/                                treenode1.next = treenode2;
/*2069*/                            treenode1 = treenode2;
                                }

/*2071*/                        setTabAt(anode, i, new TreeBin(treenode));
                            }
                        }
/*2073*/                return;
                    }
            }

            static Node untreeify(Node node)
            {
/*2082*/        Node node1 = null;
/*2082*/        Node node2 = null;
/*2083*/        for(node = node; node != null; node = node.next)
                {
/*2084*/            Node node3 = new Node(node.hash, node.key, node.val, null);
/*2085*/            if(node2 == null)
/*2086*/                node1 = node3;
/*2088*/            else
/*2088*/                node2.next = node3;
/*2089*/            node2 = node3;
                }

/*2091*/        return node1;
            }

            final long sumCount()
            {
/*3393*/        CounterCell acountercell[] = counterCells;
/*3395*/        long l = baseCount;
/*3396*/        if(acountercell != null)
                {
/*3397*/            for(int i = 0; i < acountercell.length; i++)
                    {
                        CounterCell countercell;
/*3398*/                if((countercell = acountercell[i]) != null)
/*3399*/                    l += countercell.value;
                    }

                }
/*3402*/        return l;
            }

            private final void fullAddCount(long l, CounterHashCode counterhashcode, boolean flag)
            {
                int i;
                boolean flag1;
/*3409*/        if(counterhashcode == null)
                {
/*3410*/            counterhashcode = new CounterHashCode();
/*3411*/            int j = counterHashCodeGenerator.addAndGet(0x61c88647);
/*3412*/            i = counterhashcode.code = j != 0 ? j : 1;
/*3413*/            threadCounterHashCode.set(counterhashcode);
                } else
                {
/*3415*/            i = counterhashcode.code;
                }
/*3416*/        flag1 = false;
_L2:
                Object obj;
                CounterCell countercell;
                int k;
/*3422*/        if((obj = counterCells) == null || (k = obj.length) <= 0)
/*3423*/            break; /* Loop/switch isn't completed */
/*3423*/        if((countercell = obj[k - 1 & i]) != null)
/*3424*/            break MISSING_BLOCK_LABEL_211;
/*3424*/        if(cellsBusy != 0)
/*3425*/            break MISSING_BLOCK_LABEL_205;
/*3425*/        countercell = new CounterCell(l);
/*3426*/        if(cellsBusy != 0 || !U.compareAndSwapInt(this, CELLSBUSY, 0, 1))
/*3428*/            break MISSING_BLOCK_LABEL_205;
/*3428*/        obj = 0;
                long l1;
/*3433*/        if((k = counterCells) != null && (l1 = k.length) > 0 && k[l1 = l1 - 1 & i] == null)
                {
/*3436*/            k[l1] = countercell;
/*3437*/            obj = 1;
                }
/*3440*/        cellsBusy = 0;
/*3441*/        break MISSING_BLOCK_LABEL_197;
/*3440*/        l;
/*3440*/        cellsBusy = 0;
/*3440*/        throw l;
/*3442*/        if(obj != 0)
/*3443*/            break; /* Loop/switch isn't completed */
/*3447*/        continue; /* Loop/switch isn't completed */
/*3447*/        flag1 = false;
/*3447*/        break MISSING_BLOCK_LABEL_360;
/*3448*/        if(!flag)
                {
/*3449*/            flag = true;
/*3449*/            break MISSING_BLOCK_LABEL_360;
                }
/*3450*/        l1 = countercell.value;
/*3450*/        if(U.compareAndSwapLong(countercell, CELLVALUE, countercell.value, countercell.value + l))
/*3452*/            break; /* Loop/switch isn't completed */
/*3452*/        if(counterCells != obj || k >= NCPU)
                {
/*3453*/            flag1 = false;
/*3453*/            break MISSING_BLOCK_LABEL_360;
                }
/*3454*/        if(!flag1)
                {
/*3455*/            flag1 = true;
/*3455*/            break MISSING_BLOCK_LABEL_360;
                }
/*3456*/        if(cellsBusy != 0 || !U.compareAndSwapInt(this, CELLSBUSY, 0, 1))
/*3459*/            break MISSING_BLOCK_LABEL_360;
/*3459*/        if(counterCells == obj)
                {
/*3460*/            CounterCell acountercell1[] = new CounterCell[k << 1];
/*3461*/            System.arraycopy(obj, 0, acountercell1, 0, k);
/*3462*/            counterCells = acountercell1;
                }
/*3465*/        cellsBusy = 0;
/*3466*/        break MISSING_BLOCK_LABEL_354;
/*3465*/        l;
/*3465*/        cellsBusy = 0;
/*3465*/        throw l;
/*3467*/        flag1 = false;
/*3468*/        continue; /* Loop/switch isn't completed */
/*3470*/        i = (i = (i ^= i << 13) ^ i >>> 17) ^ i << 5;
/*3472*/        if(true) goto _L2; else goto _L1
_L1:
                boolean flag2;
/*3473*/        if(cellsBusy != 0 || counterCells != obj || !U.compareAndSwapInt(this, CELLSBUSY, 0, 1))
/*3475*/            break MISSING_BLOCK_LABEL_498;
/*3475*/        flag2 = false;
/*3477*/        if(counterCells == obj)
                {
                    CounterCell acountercell[];
/*3478*/            (acountercell = new CounterCell[2])[i & 1] = new CounterCell(l);
/*3480*/            counterCells = acountercell;
/*3481*/            flag2 = true;
                }
/*3484*/        cellsBusy = 0;
/*3485*/        continue; /* Loop/switch isn't completed */
/*3484*/        l;
/*3484*/        cellsBusy = 0;
/*3484*/        throw l;
/*3486*/        if(flag2) goto _L3; else goto _L2
_L3:
/*3488*/        break; /* Loop/switch isn't completed */
/*3488*/        l1 = baseCount;
/*3488*/        if(U.compareAndSwapLong(this, BASECOUNT, baseCount, baseCount + l)) goto _L4; else goto _L2
_L4:
/*3491*/        counterhashcode.code = i;
/*3492*/        return;
            }

            private static Unsafe getUnsafe()
            {
/*3542*/        return Unsafe.getUnsafe();
/*3543*/        JVM INSTR pop ;
/*3546*/        return (Unsafe)AccessController.doPrivileged(new PrivilegedExceptionAction() {

                    public final Unsafe run()
                        throws Exception
                    {
                        /*<invalid signature>*/java.lang.Object local;
                        Field afield[];
/*3549*/                int i = (afield = (local = sun/misc/Unsafe).getDeclaredFields()).length;
/*3550*/                for(int j = 0; j < i; j++)
                        {
                            Object obj;
/*3550*/                    ((Field) (obj = afield[j])).setAccessible(true);
/*3552*/                    obj = ((Field) (obj)).get(null);
/*3553*/                    if(local.isInstance(obj))
/*3554*/                        return (Unsafe)local.cast(obj);
                        }

/*3556*/                throw new NoSuchFieldError("the Unsafe");
                    }

                    public final volatile Object run()
                        throws Exception
                    {
/*3547*/                return run();
                    }

        });
                PrivilegedActionException privilegedactionexception;
/*3559*/        privilegedactionexception;
/*3560*/        throw new RuntimeException("Could not initialize intrinsics", privilegedactionexception.getCause());
            }

            public volatile Set keySet()
            {
/* 254*/        return keySet();
            }

            private static final long serialVersionUID = 0x6499de129d87293dL;
            private static final int MAXIMUM_CAPACITY = 0x40000000;
            private static final int DEFAULT_CAPACITY = 16;
            static final int MAX_ARRAY_SIZE = 0x7ffffff7;
            private static final int DEFAULT_CONCURRENCY_LEVEL = 16;
            private static final float LOAD_FACTOR = 0.75F;
            static final int TREEIFY_THRESHOLD = 8;
            static final int UNTREEIFY_THRESHOLD = 6;
            static final int MIN_TREEIFY_CAPACITY = 64;
            private static final int MIN_TRANSFER_STRIDE = 16;
            static final int MOVED = -1;
            static final int TREEBIN = -2;
            static final int RESERVED = -3;
            static final int HASH_BITS = 0x7fffffff;
            static final int NCPU = Runtime.getRuntime().availableProcessors();
            private static final ObjectStreamField serialPersistentFields[];
            volatile transient Node table[];
            private volatile transient Node nextTable[];
            private volatile transient long baseCount;
            private volatile transient int sizeCtl;
            private volatile transient int transferIndex;
            private volatile transient int transferOrigin;
            private volatile transient int cellsBusy;
            private volatile transient CounterCell counterCells[];
            private transient KeySetView keySet;
            private transient ValuesView values;
            private transient EntrySetView entrySet;
            static final AtomicInteger counterHashCodeGenerator = new AtomicInteger();
            static final int SEED_INCREMENT = 0x61c88647;
            static final ThreadLocal threadCounterHashCode = new ThreadLocal();
            private static final Unsafe U;
            private static final long SIZECTL;
            private static final long TRANSFERINDEX;
            private static final long TRANSFERORIGIN;
            private static final long BASECOUNT;
            private static final long CELLSBUSY;
            private static final long CELLVALUE;
            private static final long ABASE;
            private static final int ASHIFT;

            static 
            {
/* 570*/        serialPersistentFields = (new ObjectStreamField[] {
/* 570*/            new ObjectStreamField("segments", [Lorg/glassfish/jersey/internal/util/collection/ConcurrentHashMapV8$Segment;), new ObjectStreamField("segmentMask", Integer.TYPE), new ObjectStreamField("segmentShift", Integer.TYPE)
                });
/*3507*/        try
                {
/*3507*/            U = getUnsafe();
/*3508*/            Object obj = org/glassfish/jersey/internal/util/collection/ConcurrentHashMapV8;
/*3509*/            SIZECTL = U.objectFieldOffset(((Class) (obj)).getDeclaredField("sizeCtl"));
/*3511*/            TRANSFERINDEX = U.objectFieldOffset(((Class) (obj)).getDeclaredField("transferIndex"));
/*3513*/            TRANSFERORIGIN = U.objectFieldOffset(((Class) (obj)).getDeclaredField("transferOrigin"));
/*3515*/            BASECOUNT = U.objectFieldOffset(((Class) (obj)).getDeclaredField("baseCount"));
/*3517*/            CELLSBUSY = U.objectFieldOffset(((Class) (obj)).getDeclaredField("cellsBusy"));
/*3519*/            obj = org/glassfish/jersey/internal/util/collection/ConcurrentHashMapV8$CounterCell;
/*3520*/            CELLVALUE = U.objectFieldOffset(((Class) (obj)).getDeclaredField("value"));
/*3522*/            obj = [Lorg/glassfish/jersey/internal/util/collection/ConcurrentHashMapV8$Node;;
/*3523*/            ABASE = U.arrayBaseOffset(((Class) (obj)));
/*3524*/            if(((obj = U.arrayIndexScale(((Class) (obj)))) & obj - 1) != 0)
                    {
/*3526*/                throw new Error("data type scale not a power of two");
                    } else
                    {
/*3527*/                ASHIFT = 31 - Integer.numberOfLeadingZeros(((int) (obj)));
/*3530*/                return;
                    }
                }
/*3528*/        catch(Exception exception)
                {
/*3529*/            throw new Error(exception);
                }
            }

}
