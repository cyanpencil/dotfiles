// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConcurrentHashMapV8.java

package org.glassfish.jersey.internal.util.collection;

import java.util.concurrent.locks.LockSupport;
import sun.misc.Unsafe;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            ConcurrentHashMapV8

static final class root extends root
{

            static int tieBreakOrder(Object obj, Object obj1)
            {
                int i;
/*2182*/        if(obj == null || obj1 == null || (i = obj.getClass().getName().compareTo(obj1.getClass().getName())) == 0)
/*2185*/            i = System.identityHashCode(obj) > System.identityHashCode(obj1) ? 1 : -1;
/*2187*/        return i;
            }

            private final void lockRoot()
            {
/*2241*/        if(!U.compareAndSwapInt(this, LOCKSTATE, 0, 1))
/*2242*/            contendedLock();
            }

            private final void unlockRoot()
            {
/*2249*/        lockState = 0;
            }

            private final void contendedLock()
            {
/*2256*/        boolean flag = false;
                int i;
/*2258*/        do
/*2258*/            if(((i = lockState) & 1) == 0)
                    {
/*2259*/                if(U.compareAndSwapInt(this, LOCKSTATE, i, 1))
                        {
/*2260*/                    if(flag)
/*2261*/                        waiter = null;
/*2262*/                    return;
                        }
                    } else
/*2264*/            if((i & 2) == 0)
                    {
/*2265*/                if(U.compareAndSwapInt(this, LOCKSTATE, i, i | 2))
                        {
/*2266*/                    flag = true;
/*2267*/                    waiter = Thread.currentThread();
                        }
                    } else
/*2269*/            if(flag)
/*2270*/                LockSupport.park(this);
/*2270*/        while(true);
            }

            final waiter find(int i, Object obj)
            {
                Object obj1;
/*2280*/        if(obj == null)
/*2281*/            break MISSING_BLOCK_LABEL_198;
/*2281*/        obj1 = first;
_L3:
/*2281*/        if(obj1 == null) goto _L2; else goto _L1
_L1:
                int j;
/*2284*/        if(((j = lockState) & 3) != 0)
                {
/*2285*/            if(((lockState) (obj1)).h == i && ((j = ((int) (((h) (obj1)).))) == obj || j != null && obj.equals(j)))
/*2287*/                return (() (obj1));
/*2288*/            continue; /* Loop/switch isn't completed */
                }
/*2288*/        if(!U.compareAndSwapInt(this, LOCKSTATE, j, j + 4))
/*2293*/            continue; /* Loop/switch isn't completed */
/*2293*/        i = (obj1 = root) != null ? ((int) ((() (obj1)).findTreeNode(i, obj, null))) : null;
/*2299*/        do
/*2299*/            obj = lockState;
/*2299*/        while(!U.compareAndSwapInt(this, LOCKSTATE, lockState, lockState - 4));
/*2302*/        if(obj == 6 && (obj = waiter) != null)
/*2303*/            LockSupport.unpark(((Thread) (obj)));
/*2304*/        break MISSING_BLOCK_LABEL_188;
/*2296*/        i;
/*2299*/        do
/*2299*/            obj = lockState;
/*2299*/        while(!U.compareAndSwapInt(this, LOCKSTATE, lockState, lockState - 4));
/*2302*/        if(obj == 6 && (obj = waiter) != null)
/*2303*/            LockSupport.unpark(((Thread) (obj)));
/*2304*/        throw i;
/*2305*/        return i;
/*2281*/        obj1 = ((waiter) (obj1)).t;
                  goto _L3
_L2:
/*2309*/        return null;
            }

            final  putTreeVal(int i, Object obj, Object obj1)
            {
                 4;
/*2318*/        Object obj2 = null;
/*2319*/        boolean flag = false;
/*2320*/          = root;
                int j;
                 2;
/*2324*/        do
                {
/*2324*/            if( == null)
                    {
/*2325*/                first = root = new (i, obj, obj1, null, null);
/*2326*/                break MISSING_BLOCK_LABEL_340;
                    }
/*2327*/            if((j = .hash) > i)
/*2328*/                j = -1;
/*2329*/            else
/*2329*/            if(j < i)
                    {
/*2330*/                j = 1;
                    } else
                    {
                        Object obj3;
/*2331*/                if((obj3 = .key) == obj || obj3 != null && obj.equals(obj3))
/*2332*/                    return ;
/*2333*/                if(obj2 == null && (obj2 = ConcurrentHashMapV8.comparableClassFor(obj)) == null || (j = ConcurrentHashMapV8.compareComparables(((Class) (obj2)), obj, obj3)) == 0)
                        {
/*2336*/                    if(!flag)
                            {
/*2338*/                        flag = true;
                                 1;
                                 3;
/*2339*/                        if((3 = .left) != null && (1 = 3.findTreeNode(i, obj, ((Class) (obj2)))) != null || (3 = .right) != null && (1 = 3.findTreeNode(i, obj, ((Class) (obj2)))) != null)
/*2343*/                            return 1;
                            }
/*2345*/                    j = tieBreakOrder(obj, obj3);
                        }
                    }
/*2348*/            2 = ;
                } while(( = j > 0 ? .right : .left) != null);
/*2351*/        obj2 = first;
/*2352*/        first = 4 = new (i, obj, obj1, (() (obj2)), 2);
/*2353*/        if(obj2 != null)
/*2354*/            obj2.prev = 4;
/*2355*/        if(j <= 0)
/*2356*/            2.left = 4;
/*2358*/        else
/*2358*/            2.right = 4;
/*2359*/        if(!2.red)
                {
/*2360*/            4.red = true;
/*2360*/            break MISSING_BLOCK_LABEL_340;
                }
/*2362*/        lockRoot();
/*2364*/        root = balanceInsertion(root, 4);
/*2366*/        unlockRoot();
/*2367*/        break MISSING_BLOCK_LABEL_340;
/*2366*/        i;
/*2366*/        unlockRoot();
/*2366*/        throw i;
/*2372*/        if(!$assertionsDisabled && !checkInvariants(root))
/*2372*/            throw new AssertionError();
/*2373*/        else
/*2373*/            return null;
            }

            final boolean removeTreeNode( )
            {
                 1;
/*2387*/        1 = ().next;
                 2;
/*2388*/        if((2 = .prev) == null)
/*2392*/            first = 1;
/*2394*/        else
/*2394*/            2.next = 1;
/*2395*/        if(1 != null)
/*2396*/            1.prev = 2;
/*2397*/        if(first == null)
                {
/*2398*/            root = null;
/*2399*/            return true;
                }
/*2401*/        if((1 = root) == null || 1.right == null || (2 = 1.left) == null || 2.left == null)
/*2403*/            return true;
/*2404*/        lockRoot();
/*2407*/         3 = .left;
/*2408*/         4 = .right;
/*2409*/        if(3 != null && 4 != null)
                {
                     5;
                     8;
/*2410*/            for(5 = 4; (8 = 5.left) != null; 5 = 8);
/*2413*/            boolean flag = 5.red;
/*2414*/            5.red = .red;
/*2415*/            .red = flag;
/*2416*/             9 = 5.right;
/*2417*/             10 = .parent;
/*2418*/            if(5 == 4)
                    {
/*2419*/                .parent = 5;
/*2420*/                5.right = ;
                    } else
                    {
/*2422*/                 11 = 5.parent;
/*2423*/                if((.parent = 11) != null)
/*2424*/                    if(5 == 11.left)
/*2425*/                        11.left = ;
/*2427*/                    else
/*2427*/                        11.right = ;
/*2429*/                if((5.right = 4) != null)
/*2430*/                    4.parent = 5;
                    }
/*2432*/            .left = null;
/*2433*/            if((.right = 9) != null)
/*2434*/                9.parent = ;
/*2435*/            if((5.left = 3) != null)
/*2436*/                3.parent = 5;
/*2437*/            if((5.parent = 10) == null)
/*2438*/                1 = 5;
/*2439*/            else
/*2439*/            if( == 10.left)
/*2440*/                10.left = 5;
/*2442*/            else
/*2442*/                10.right = 5;
/*2443*/            if(9 != null)
/*2444*/                3 = 9;
/*2446*/            else
/*2446*/                3 = ;
                } else
/*2447*/        if(3 != null)
/*2448*/            3 = 3;
/*2449*/        else
/*2449*/        if(4 != null)
/*2450*/            3 = 4;
/*2452*/        else
/*2452*/            3 = ;
/*2453*/        if(3 != )
                {
                     6;
/*2454*/            if((6 = 3.parent = .parent) == null)
/*2456*/                1 = 3;
/*2457*/            else
/*2457*/            if( == 6.left)
/*2458*/                6.left = 3;
/*2460*/            else
/*2460*/                6.right = 3;
/*2461*/            .left = .right = .parent = null;
                }
/*2464*/        root = .red ? 1 : balanceDeletion(1, 3);
                 7;
/*2466*/        if( == 3 && (7 = .parent) != null)
                {
/*2469*/            if( == 7.left)
/*2470*/                7.left = null;
/*2471*/            else
/*2471*/            if( == 7.right)
/*2472*/                7.right = null;
/*2473*/            .parent = null;
                }
/*2477*/        unlockRoot();
/*2478*/        break MISSING_BLOCK_LABEL_519;
/*2477*/        ;
/*2477*/        unlockRoot();
/*2477*/        throw ;
/*2479*/        if(!$assertionsDisabled && !checkInvariants(root))
/*2479*/            throw new AssertionError();
/*2480*/        else
/*2480*/            return false;
            }

            static  rotateLeft( ,  1)
            {
                 2;
/*2491*/        if(1 != null && (2 = 1.right) != null)
                {
                     3;
/*2492*/            if((3 = 1.right = 2.left) != null)
/*2493*/                3.parent = 1;
/*2494*/            if((3 = 2.parent = 1.parent) == null)
/*2495*/                ( = 2).red = false;
/*2496*/            else
/*2496*/            if(3.left == 1)
/*2497*/                3.left = 2;
/*2499*/            else
/*2499*/                3.right = 2;
/*2500*/            2.left = 1;
/*2501*/            1.parent = 2;
                }
/*2503*/        return ;
            }

            static  rotateRight( ,  1)
            {
                 2;
/*2511*/        if(1 != null && (2 = 1.left) != null)
                {
                     3;
/*2512*/            if((3 = 1.left = 2.right) != null)
/*2513*/                3.parent = 1;
/*2514*/            if((3 = 2.parent = 1.parent) == null)
/*2515*/                ( = 2).red = false;
/*2516*/            else
/*2516*/            if(3.right == 1)
/*2517*/                3.right = 2;
/*2519*/            else
/*2519*/                3.left = 2;
/*2520*/            2.right = 1;
/*2521*/            1.parent = 2;
                }
/*2523*/        return ;
            }

            static  balanceInsertion( ,  1)
            {
/*2528*/        1.red = true;
/*2530*/        do
                {
                     2;
/*2530*/            if((2 = 1.parent) == null)
                    {
/*2531*/                1.red = false;
/*2532*/                return 1;
                    }
                     3;
/*2533*/            if(!2.red || (3 = 2.parent) == null)
/*2534*/                return ;
                     4;
/*2535*/            if(2 == (4 = 3.left))
                    {
/*2536*/                if((4 = 3.right) != null && 4.red)
                        {
/*2537*/                    4.red = false;
/*2538*/                    2.red = false;
/*2539*/                    3.red = true;
/*2540*/                    1 = 3;
                        } else
                        {
/*2542*/                    if(1 == 2.right)
                            {
/*2543*/                         = rotateLeft(, 1 = 2);
/*2544*/                        3 = (2 = 1.parent) != null ? 2.parent : null;
                            }
/*2546*/                    if(2 != null)
                            {
/*2547*/                        2.red = false;
/*2548*/                        if(3 != null)
                                {
/*2549*/                            3.red = true;
/*2550*/                             = rotateRight(, 3);
                                }
                            }
                        }
                    } else
/*2555*/            if(4 != null && 4.red)
                    {
/*2556*/                4.red = false;
/*2557*/                2.red = false;
/*2558*/                3.red = true;
/*2559*/                1 = 3;
                    } else
                    {
/*2561*/                if(1 == 2.left)
                        {
/*2562*/                     = rotateRight(, 1 = 2);
/*2563*/                    3 = (2 = 1.parent) != null ? 2.parent : null;
                        }
/*2565*/                if(2 != null)
                        {
/*2566*/                    2.red = false;
/*2567*/                    if(3 != null)
                            {
/*2568*/                        3.red = true;
/*2569*/                         = rotateLeft(, 3);
                            }
                        }
                    }
                } while(true);
            }

            static  balanceDeletion( ,  1)
            {
/*2580*/        do
                {
/*2580*/            if(1 == null || 1 == )
/*2581*/                return ;
                     2;
/*2582*/            if((2 = 1.parent) == null)
                    {
/*2583*/                1.red = false;
/*2584*/                return 1;
                    }
/*2585*/            if(1.red)
                    {
/*2586*/                1.red = false;
/*2587*/                return ;
                    }
                     3;
/*2588*/            if((3 = 2.left) == 1)
                    {
/*2589*/                if((3 = 2.right) != null && 3.red)
                        {
/*2590*/                    3.red = false;
/*2591*/                    2.red = true;
/*2592*/                     = rotateLeft(, 2);
/*2593*/                    3 = (2 = 1.parent) != null ? 2.right : null;
                        }
/*2595*/                if(3 == null)
                        {
/*2596*/                    1 = 2;
                        } else
                        {
/*2598*/                     4 = 3.left;
                             7;
/*2599*/                    if(((7 = 3.right) == null || !7.red) && (4 == null || !4.red))
                            {
/*2602*/                        3.red = true;
/*2603*/                        1 = 2;
                            } else
                            {
/*2605*/                        if(7 == null || !7.red)
                                {
/*2606*/                            if(4 != null)
/*2607*/                                4.red = false;
/*2608*/                            3.red = true;
/*2609*/                             = rotateRight(, 3);
/*2610*/                            3 = (2 = 1.parent) != null ? 2.right : null;
                                }
/*2613*/                        if(3 != null)
                                {
/*2614*/                            3.red = 2 != null ? 2.red : false;
                                     8;
/*2615*/                            if((8 = 3.right) != null)
/*2616*/                                8.red = false;
                                }
/*2618*/                        if(2 != null)
                                {
/*2619*/                            2.red = false;
/*2620*/                             = rotateLeft(, 2);
                                }
/*2622*/                        1 = ;
                            }
                        }
                    } else
                    {
/*2626*/                if(3 != null && 3.red)
                        {
/*2627*/                    3.red = false;
/*2628*/                    2.red = true;
/*2629*/                     = rotateRight(, 2);
/*2630*/                    3 = (2 = 1.parent) != null ? 2.left : null;
                        }
/*2632*/                if(3 == null)
                        {
/*2633*/                    1 = 2;
                        } else
                        {
/*2635*/                     5 = 3.left;
/*2636*/                     9 = 3.right;
/*2637*/                    if((5 == null || !5.red) && (9 == null || !9.red))
                            {
/*2639*/                        3.red = true;
/*2640*/                        1 = 2;
                            } else
                            {
/*2642*/                        if(5 == null || !5.red)
                                {
/*2643*/                            if(9 != null)
/*2644*/                                9.red = false;
/*2645*/                            3.red = true;
/*2646*/                             = rotateLeft(, 3);
/*2647*/                            3 = (2 = 1.parent) != null ? 2.left : null;
                                }
/*2650*/                        if(3 != null)
                                {
/*2651*/                            3.red = 2 != null ? 2.red : false;
                                     6;
/*2652*/                            if((6 = 3.left) != null)
/*2653*/                                6.red = false;
                                }
/*2655*/                        if(2 != null)
                                {
/*2656*/                            2.red = false;
/*2657*/                             = rotateRight(, 2);
                                }
/*2659*/                        1 = ;
                            }
                        }
                    }
                } while(true);
            }

            static boolean checkInvariants( )
            {
/*2670*/         1 = .parent;
/*2671*/         2 = .left;
/*2672*/         3 = .right;
/*2673*/         4 = .prev;
/*2674*/         5 = ().next;
/*2675*/        if(4 != null && 4.next != )
/*2676*/            return false;
/*2677*/        if(5 != null && 5.prev != )
/*2678*/            return false;
/*2679*/        if(1 != null &&  != 1.left &&  != 1.right)
/*2680*/            return false;
/*2681*/        if(2 != null && (2.parent !=  || 2.hash > .hash))
/*2682*/            return false;
/*2683*/        if(3 != null && (3.parent !=  || 3.hash < .hash))
/*2684*/            return false;
/*2685*/        if(.red && 2 != null && 2.red && 3 != null && 3.red)
/*2686*/            return false;
/*2687*/        if(2 != null && !checkInvariants(2))
/*2688*/            return false;
/*2689*/        return 3 == null || checkInvariants(3);
            }

             root;
            volatile  first;
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
/*2699*/        try
                {
/*2699*/            U = ConcurrentHashMapV8.access$000();
/*2700*/            /*<invalid signature>*/java.lang.Object local = org/glassfish/jersey/internal/util/collection/ConcurrentHashMapV8$TreeBin;
/*2701*/            LOCKSTATE = U.objectFieldOffset(local.getDeclaredField("lockState"));
                }
/*2703*/        catch(Exception exception)
                {
/*2704*/            throw new Error(exception);
                }
            }

            ( )
            {
/*2194*/        super(-2, null, null, null);
/*2195*/        first = ;
/*2196*/         1 = null;
                 2;
/*2197*/        for( = ;  != null;  = 2)
                {
/*2198*/            2 = ().next;
/*2199*/            .left = .right = null;
/*2200*/            if(1 == null)
                    {
/*2201*/                .parent = null;
/*2202*/                .red = false;
/*2203*/                1 = ;
/*2203*/                continue;
                    }
/*2205*/            Object obj = .key;
/*2206*/            int i = .hash;
/*2207*/            Class class1 = null;
/*2208*/             3 = 1;
                    int j;
                    Object obj1;
/*2211*/            do
                    {
/*2211*/                obj1 = 3.key;
/*2212*/                if((j = 3.hash) > i)
/*2213*/                    j = -1;
/*2214*/                else
/*2214*/                if(j < i)
/*2215*/                    j = 1;
/*2216*/                else
/*2216*/                if(class1 == null && (class1 = ConcurrentHashMapV8.comparableClassFor(obj)) == null || (j = ConcurrentHashMapV8.compareComparables(class1, obj, obj1)) == 0)
/*2219*/                    j = tieBreakOrder(obj, obj1);
/*2220*/                obj1 = 3;
                    } while((3 = j > 0 ? 3.right : 3.left) != null);
/*2222*/            .parent = (() (obj1));
/*2223*/            if(j <= 0)
/*2224*/                obj1.left = ;
/*2226*/            else
/*2226*/                obj1.right = ;
/*2227*/            1 = balanceInsertion(1, );
                }

/*2233*/        root = 1;
/*2234*/        if(!$assertionsDisabled && !checkInvariants(root))
/*2234*/            throw new AssertionError();
/*2235*/        else
/*2235*/            return;
            }
}
