// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LinkedTransferQueue.java

package org.glassfish.jersey.internal.util.collection;

import java.io.*;
import java.lang.reflect.Field;
import java.security.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import sun.misc.Unsafe;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            ThreadLocalRandom, TransferQueue

class LinkedTransferQueue extends AbstractQueue
    implements Serializable, TransferQueue
{
    final class Itr
        implements Iterator
    {

                private void advance(Node node)
                {
                    Node node1;
/* 855*/            if((node1 = lastRet) != null && !node1.isMatched())
/* 856*/                lastPred = node1;
/* 857*/            else
/* 857*/            if((node1 = lastPred) == null || node1.isMatched())
                    {
/* 858*/                lastPred = null;
                    } else
                    {
                        Node node2;
                        Node node4;
/* 861*/                for(; (node2 = node1.next) != null && node2 != node1 && node2.isMatched() && (node4 = node2.next) != null && node4 != node2; node1.casNext(node2, node4));
                    }
/* 867*/            lastRet = node;
/* 869*/            Node node3 = node;
/* 870*/            do
                    {
                        Node node5;
/* 870*/                if((node5 = node3 != null ? node3.next : head) == null)
/* 873*/                    break;
/* 873*/                if(node5 == node3)
                        {
/* 874*/                    node3 = null;
/* 875*/                    continue;
                        }
/* 877*/                node = ((Node) (node5.item));
/* 878*/                if(node5.isData)
                        {
/* 879*/                    if(node != null && node != node5)
                            {
/* 880*/                        nextItem = LinkedTransferQueue.cast(node);
/* 881*/                        nextNode = node5;
/* 882*/                        return;
                            }
                        } else
/* 884*/                if(node == null)
/* 887*/                    break;
/* 887*/                if(node3 == null)
                        {
/* 888*/                    node3 = node5;
/* 888*/                    continue;
                        }
/* 889*/                if((node = node5.next) == null)
/* 891*/                    break;
/* 891*/                if(node5 == node)
/* 892*/                    node3 = null;
/* 894*/                else
/* 894*/                    node3.casNext(node5, node);
                    } while(true);
/* 896*/            nextNode = null;
/* 897*/            nextItem = null;
                }

                public final boolean hasNext()
                {
/* 905*/            return nextNode != null;
                }

                public final Object next()
                {
                    Node node;
/* 909*/            if((node = nextNode) == null)
                    {
/* 910*/                throw new NoSuchElementException();
                    } else
                    {
/* 911*/                Object obj = nextItem;
/* 912*/                advance(node);
/* 913*/                return obj;
                    }
                }

                public final void remove()
                {
                    Node node;
/* 917*/            if((node = lastRet) == null)
/* 919*/                throw new IllegalStateException();
/* 920*/            lastRet = null;
/* 921*/            if(node.tryMatchData())
/* 922*/                unsplice(lastPred, node);
                }

                private Node nextNode;
                private Object nextItem;
                private Node lastRet;
                private Node lastPred;
                final LinkedTransferQueue this$0;

                Itr()
                {
/* 900*/            this$0 = LinkedTransferQueue.this;
/* 900*/            super();
/* 901*/            advance(null);
                }
    }

    static final class Node
    {

                final boolean casNext(Node node, Node node1)
                {
/* 468*/            return UNSAFE.compareAndSwapObject(this, nextOffset, node, node1);
                }

                final boolean casItem(Object obj, Object obj1)
                {
/* 473*/            return UNSAFE.compareAndSwapObject(this, itemOffset, obj, obj1);
                }

                final void forgetNext()
                {
/* 490*/            UNSAFE.putObject(this, nextOffset, this);
                }

                final void forgetContents()
                {
/* 503*/            UNSAFE.putObject(this, itemOffset, this);
/* 504*/            UNSAFE.putObject(this, waiterOffset, null);
                }

                final boolean isMatched()
                {
                    Object obj;
/* 512*/            return (obj = item) == this || (obj == null) == isData;
                }

                final boolean isUnmatchedRequest()
                {
/* 520*/            return !isData && item == null;
                }

                final boolean cannotPrecede(boolean flag)
                {
                    boolean flag1;
/* 529*/            return (flag1 = isData) != flag && (flag = ((boolean) (item))) != this && (flag != null) == flag1;
                }

                final boolean tryMatchData()
                {
                    Object obj;
/* 539*/            if((obj = item) != null && obj != this && casItem(obj, null))
                    {
/* 541*/                LockSupport.unpark(waiter);
/* 542*/                return true;
                    } else
                    {
/* 544*/                return false;
                    }
                }

                final boolean isData;
                volatile Object item;
                volatile Node next;
                volatile Thread waiter;
                private static final long serialVersionUID = 0xd1261c33b18e3356L;
                private static final Unsafe UNSAFE;
                private static final long itemOffset;
                private static final long nextOffset;
                private static final long waiterOffset;

                static 
                {
/* 557*/            try
                    {
/* 557*/                UNSAFE = LinkedTransferQueue.getUnsafe();
/* 558*/                /*<invalid signature>*/java.lang.Object local = org/glassfish/jersey/internal/util/collection/LinkedTransferQueue$Node;
/* 559*/                itemOffset = UNSAFE.objectFieldOffset(local.getDeclaredField("item"));
/* 561*/                nextOffset = UNSAFE.objectFieldOffset(local.getDeclaredField("next"));
/* 563*/                waiterOffset = UNSAFE.objectFieldOffset(local.getDeclaredField("waiter"));
                    }
/* 565*/            catch(Exception exception)
                    {
/* 566*/                throw new Error(exception);
                    }
                }

                Node(Object obj, boolean flag)
                {
/* 481*/            UNSAFE.putObject(this, itemOffset, obj);
/* 482*/            isData = flag;
                }
    }


            private boolean casTail(Node node, Node node1)
            {
/* 588*/        return UNSAFE.compareAndSwapObject(this, tailOffset, node, node1);
            }

            private boolean casHead(Node node, Node node1)
            {
/* 592*/        return UNSAFE.compareAndSwapObject(this, headOffset, node, node1);
            }

            private boolean casSweepVotes(int i, int j)
            {
/* 596*/        return UNSAFE.compareAndSwapInt(this, sweepVotesOffset, i, j);
            }

            static Object cast(Object obj)
            {
/* 610*/        return obj;
            }

            private Object xfer(Object obj, boolean flag, int i, long l)
            {
/* 624*/        if(flag && obj == null)
/* 625*/            throw new NullPointerException();
/* 626*/        Node node = null;
/* 631*/        do
                {
                    Node node1;
                    Node node5;
/* 631*/            for(Node node3 = node1 = head; node3 != null; node3 = node3 == node5 ? (node1 = head) : node5)
                    {
/* 632*/                boolean flag1 = node3.isData;
                        Object obj1;
/* 633*/                if((obj1 = node3.item) != node3 && (obj1 != null) == flag1)
                        {
/* 635*/                    if(flag1 == flag)
/* 637*/                        break;
/* 637*/                    if(node3.casItem(obj1, obj))
                            {
/* 638*/                        Node node4 = node3;
/* 638*/                        do
                                {
/* 638*/                            if(node4 == node1)
/* 639*/                                break;
/* 639*/                            obj = node4.next;
/* 640*/                            if(head != node1 || !casHead(node1, ((Node) (obj != null ? ((Node) (obj)) : node4))))
/* 641*/                                continue;
/* 641*/                            node1.forgetNext();
/* 642*/                            break;
                                } while((node1 = head) != null && (node4 = node1.next) != null && node4.isMatched());
/* 648*/                        LockSupport.unpark(node3.waiter);
/* 649*/                        return cast(obj1);
                            }
                        }
/* 652*/                node5 = node3.next;
                    }

/* 656*/            if(i == 0)
/* 657*/                break;
/* 657*/            if(node == null)
/* 658*/                node = new Node(obj, flag);
                    Node node2;
/* 659*/            if((node2 = tryAppend(node, flag)) == null)
/* 662*/                continue;
/* 662*/            if(i != 1)
/* 663*/                return awaitMatch(node, node2, obj, i == 3, l);
/* 665*/            break;
                } while(true);
/* 665*/        return obj;
            }

            private Node tryAppend(Node node, boolean flag)
            {
                Node node1;
/* 679*/        Node node2 = node1 = tail;
/* 681*/        do
                {
/* 681*/            if(node2 == null && (node2 = head) == null)
                    {
/* 682*/                if(casHead(null, node))
/* 683*/                    return node;
/* 684*/                continue;
                    }
/* 684*/            if(node2.cannotPrecede(flag))
/* 685*/                return null;
                    Node node3;
/* 686*/            if((node3 = node2.next) != null)
                    {
                        Node node4;
/* 687*/                node2 = node2 == node1 || node1 == (node4 = tail) ? node2 == node3 ? null : node3 : (node1 = node4);
/* 687*/                continue;
                    }
/* 689*/            if(node2.casNext(null, node))
/* 690*/                break;
/* 690*/            node2 = node2.next;
                } while(true);
/* 692*/        if(node2 != node1)
/* 693*/            while((tail != node1 || !casTail(node1, node)) && (node1 = tail) != null && (node = node1.next) != null && (node = node.next) != null && node != node1) ;
/* 698*/        return node2;
            }

            private Object awaitMatch(Node node, Node node1, Object obj, boolean flag, long l)
            {
/* 716*/        long l1 = flag ? System.nanoTime() : 0L;
/* 717*/        Thread thread = Thread.currentThread();
/* 718*/        int i = -1;
/* 719*/        ThreadLocalRandom threadlocalrandom = null;
/* 722*/        do
                {
                    Object obj1;
/* 722*/            if((obj1 = node.item) != obj)
                    {
/* 725*/                node.forgetContents();
/* 726*/                return cast(obj1);
                    }
/* 728*/            if((thread.isInterrupted() || flag && l <= 0L) && node.casItem(obj, node))
                    {
/* 730*/                unsplice(node1, node);
/* 731*/                return obj;
                    }
/* 734*/            if(i < 0)
                    {
/* 735*/                if((i = spinsFor(node1, node.isData)) > 0)
/* 736*/                    threadlocalrandom = ThreadLocalRandom.current();
                    } else
/* 737*/            if(i > 0)
                    {
/* 738*/                i--;
/* 739*/                if(threadlocalrandom.nextInt(64) == 0)
/* 740*/                    Thread.yield();
                    } else
/* 741*/            if(node.waiter == null)
/* 742*/                node.waiter = thread;
/* 743*/            else
/* 743*/            if(flag)
                    {
/* 744*/                long l2 = System.nanoTime();
/* 745*/                if((l -= l2 - l1) > 0L)
/* 746*/                    LockSupport.parkNanos(this, l);
/* 747*/                l1 = l2;
                    } else
                    {
/* 749*/                LockSupport.park(this);
                    }
                } while(true);
            }

            private static int spinsFor(Node node, boolean flag)
            {
/* 759*/        if(MP && node != null)
                {
/* 760*/            if(node.isData != flag)
/* 761*/                return 192;
/* 762*/            if(node.isMatched())
/* 763*/                return 128;
/* 764*/            if(node.waiter == null)
/* 765*/                return 64;
                }
/* 767*/        return 0;
            }

            final Node succ(Node node)
            {
/* 778*/        Node node1 = node.next;
/* 779*/        if(node == node1)
/* 779*/            return head;
/* 779*/        else
/* 779*/            return node1;
            }

            private Node firstOfMode(boolean flag)
            {
/* 787*/        for(Node node = head; node != null; node = succ(node))
/* 788*/            if(!node.isMatched())
/* 789*/                if(node.isData == flag)
/* 789*/                    return node;
/* 789*/                else
/* 789*/                    return null;

/* 791*/        return null;
            }

            private Object firstDataItem()
            {
/* 799*/        for(Node node = head; node != null; node = succ(node))
                {
/* 800*/            Object obj = node.item;
/* 801*/            if(node.isData)
                    {
/* 802*/                if(obj != null && obj != node)
/* 803*/                    return cast(obj);
/* 804*/                continue;
                    }
/* 804*/            if(obj == null)
/* 805*/                return null;
                }

/* 807*/        return null;
            }

            private int countOfMode(boolean flag)
            {
/* 815*/        int i = 0;
/* 816*/        Node node = head;
/* 816*/        do
                {
/* 816*/            if(node == null)
/* 817*/                break;
/* 817*/            if(!node.isMatched())
                    {
/* 818*/                if(node.isData != flag)
/* 819*/                    return 0;
/* 820*/                if(++i == 0x7fffffff)
/* 823*/                    break;
                    }
                    Node node1;
/* 823*/            if((node1 = node.next) != node)
                    {
/* 825*/                node = node1;
                    } else
                    {
/* 827*/                i = 0;
/* 828*/                node = head;
                    }
                } while(true);
/* 831*/        return i;
            }

            final void unsplice(Node node, Node node1)
            {
/* 937*/        node1.forgetContents();
                Node node2;
/* 945*/        if(node != null && node != node1 && node.next == node1 && ((node2 = node1.next) == null || node2 != node1 && node.casNext(node1, node2) && node.isMatched()))
                {
/* 950*/            do
                    {
                        Node node3;
/* 950*/                if((node3 = head) == node || node3 == node1 || node3 == null)
/* 952*/                    return;
/* 953*/                if(!node3.isMatched())
/* 955*/                    break;
                        Node node4;
/* 955*/                if((node4 = node3.next) == null)
/* 957*/                    return;
/* 958*/                if(node4 != node3 && casHead(node3, node4))
/* 959*/                    node3.forgetNext();
                    } while(true);
/* 961*/            if(node.next != node && node1.next != node1)
                    {
                        int i;
/* 963*/label0:
/* 963*/                do
                        {
/* 963*/                    do
/* 963*/                        if((i = sweepVotes) >= 32)
/* 965*/                            continue label0;
/* 965*/                    while(!casSweepVotes(i, i + 1));
/* 966*/                    return;
                        } while(!casSweepVotes(i, 0));
/* 968*/                sweep();
/* 969*/                return;
                    }
                }
            }

            private void sweep()
            {
/* 982*/        Node node = head;
/* 982*/        do
                {
                    Node node1;
/* 982*/            if(node == null || (node1 = node.next) == null)
/* 983*/                break;
/* 983*/            if(!node1.isMatched())
                    {
/* 985*/                node = node1;
/* 985*/                continue;
                    }
                    Node node2;
/* 986*/            if((node2 = node1.next) == null)
/* 988*/                break;
/* 988*/            if(node1 == node2)
/* 990*/                node = head;
/* 992*/            else
/* 992*/                node.casNext(node1, node2);
                } while(true);
            }

            private boolean findAndRemove(Object obj)
            {
/*1000*/        if(obj != null)
                {
/*1001*/            Node node = null;
/*1001*/            Node node1 = head;
/*1001*/            do
                    {
/*1001*/                if(node1 == null)
/*1002*/                    break;
/*1002*/                Object obj1 = node1.item;
/*1003*/                if(node1.isData)
                        {
/*1004*/                    if(obj1 != null && obj1 != node1 && obj.equals(obj1) && node1.tryMatchData())
                            {
/*1006*/                        unsplice(node, node1);
/*1007*/                        return true;
                            }
                        } else
/*1009*/                if(obj1 == null)
/*1011*/                    break;
/*1011*/                node = node1;
/*1012*/                if((node1 = node1.next) == node)
                        {
/*1013*/                    node = null;
/*1014*/                    node1 = head;
                        }
                    } while(true);
                }
/*1018*/        return false;
            }

            public LinkedTransferQueue()
            {
            }

            public LinkedTransferQueue(Collection collection)
            {
/*1038*/        this();
/*1039*/        addAll(collection);
            }

            public void put(Object obj)
            {
/*1049*/        xfer(obj, true, 1, 0L);
            }

            public boolean offer(Object obj, long l, TimeUnit timeunit)
            {
/*1063*/        xfer(obj, true, 1, 0L);
/*1064*/        return true;
            }

            public boolean offer(Object obj)
            {
/*1075*/        xfer(obj, true, 1, 0L);
/*1076*/        return true;
            }

            public boolean add(Object obj)
            {
/*1088*/        xfer(obj, true, 1, 0L);
/*1089*/        return true;
            }

            public boolean tryTransfer(Object obj)
            {
/*1103*/        return xfer(obj, true, 0, 0L) == null;
            }

            public void transfer(Object obj)
                throws InterruptedException
            {
/*1118*/        if(xfer(obj, true, 2, 0L) != null)
                {
/*1119*/            Thread.interrupted();
/*1120*/            throw new InterruptedException();
                } else
                {
/*1122*/            return;
                }
            }

            public boolean tryTransfer(Object obj, long l, TimeUnit timeunit)
                throws InterruptedException
            {
/*1140*/        if(xfer(obj, true, 3, timeunit.toNanos(l)) == null)
/*1141*/            return true;
/*1142*/        if(!Thread.interrupted())
/*1143*/            return false;
/*1144*/        else
/*1144*/            throw new InterruptedException();
            }

            public Object take()
                throws InterruptedException
            {
                Object obj;
/*1148*/        if((obj = xfer(null, false, 2, 0L)) != null)
                {
/*1150*/            return obj;
                } else
                {
/*1151*/            Thread.interrupted();
/*1152*/            throw new InterruptedException();
                }
            }

            public Object poll(long l, TimeUnit timeunit)
                throws InterruptedException
            {
/*1156*/        if((l = ((long) (xfer(null, false, 3, timeunit.toNanos(l))))) != null || !Thread.interrupted())
/*1158*/            return l;
/*1159*/        else
/*1159*/            throw new InterruptedException();
            }

            public Object poll()
            {
/*1163*/        return xfer(null, false, 0, 0L);
            }

            public int drainTo(Collection collection)
            {
/*1171*/        if(collection == null)
/*1172*/            throw new NullPointerException();
/*1173*/        if(collection == this)
/*1174*/            throw new IllegalArgumentException();
                int i;
                Object obj;
/*1175*/        for(i = 0; (obj = poll()) != null; i++)
/*1177*/            collection.add(obj);

/*1180*/        return i;
            }

            public int drainTo(Collection collection, int i)
            {
/*1188*/        if(collection == null)
/*1189*/            throw new NullPointerException();
/*1190*/        if(collection == this)
/*1191*/            throw new IllegalArgumentException();
                int j;
                Object obj;
/*1192*/        for(j = 0; j < i && (obj = poll()) != null; j++)
/*1194*/            collection.add(obj);

/*1197*/        return j;
            }

            public Iterator iterator()
            {
/*1214*/        return new Itr();
            }

            public Object peek()
            {
/*1218*/        return firstDataItem();
            }

            public boolean isEmpty()
            {
/*1227*/        for(Node node = head; node != null; node = succ(node))
/*1228*/            if(!node.isMatched())
/*1229*/                return !node.isData;

/*1231*/        return true;
            }

            public boolean hasWaitingConsumer()
            {
/*1235*/        return firstOfMode(false) != null;
            }

            public int size()
            {
/*1251*/        return countOfMode(true);
            }

            public int getWaitingConsumerCount()
            {
/*1255*/        return countOfMode(false);
            }

            public boolean remove(Object obj)
            {
/*1270*/        return findAndRemove(obj);
            }

            public boolean contains(Object obj)
            {
/*1282*/        if(obj == null)
/*1282*/            return false;
/*1283*/        for(Node node = head; node != null; node = succ(node))
                {
/*1284*/            Object obj1 = node.item;
/*1285*/            if(node.isData)
                    {
/*1286*/                if(obj1 != null && obj1 != node && obj.equals(obj1))
/*1287*/                    return true;
/*1288*/                continue;
                    }
/*1288*/            if(obj1 == null)
/*1283*/                break;
                }

/*1291*/        return false;
            }

            public int remainingCapacity()
            {
/*1303*/        return 0x7fffffff;
            }

            private void writeObject(ObjectOutputStream objectoutputstream)
                throws IOException
            {
/*1315*/        objectoutputstream.defaultWriteObject();
                Object obj;
/*1316*/        for(Iterator iterator1 = iterator(); iterator1.hasNext(); objectoutputstream.writeObject(obj))
/*1316*/            obj = iterator1.next();

/*1319*/        objectoutputstream.writeObject(null);
            }

            private void readObject(ObjectInputStream objectinputstream)
                throws IOException, ClassNotFoundException
            {
/*1330*/        objectinputstream.defaultReadObject();
                Object obj;
/*1333*/        while((obj = objectinputstream.readObject()) != null) 
/*1337*/            offer(obj);
            }

            static Unsafe getUnsafe()
            {
/*1372*/        return Unsafe.getUnsafe();
/*1373*/        JVM INSTR pop ;
/*1376*/        return (Unsafe)AccessController.doPrivileged(new PrivilegedExceptionAction() {

                    public final Unsafe run()
                        throws Exception
                    {
                        /*<invalid signature>*/java.lang.Object local;
                        Field afield[];
/*1379*/                int i = (afield = (local = sun/misc/Unsafe).getDeclaredFields()).length;
/*1380*/                for(int j = 0; j < i; j++)
                        {
                            Object obj;
/*1380*/                    ((Field) (obj = afield[j])).setAccessible(true);
/*1382*/                    obj = ((Field) (obj)).get(null);
/*1383*/                    if(local.isInstance(obj))
/*1384*/                        return (Unsafe)local.cast(obj);
                        }

/*1386*/                throw new NoSuchFieldError("the Unsafe");
                    }

                    public final volatile Object run()
                        throws Exception
                    {
/*1377*/                return run();
                    }

        });
                PrivilegedActionException privilegedactionexception;
/*1389*/        privilegedactionexception;
/*1390*/        throw new RuntimeException("Could not initialize intrinsics", privilegedactionexception.getCause());
            }

            private static final long serialVersionUID = 0xd345336e1f5c3e9aL;
            private static final boolean MP = Runtime.getRuntime().availableProcessors() > 1;
            private static final int FRONT_SPINS = 128;
            private static final int CHAINED_SPINS = 64;
            static final int SWEEP_THRESHOLD = 32;
            volatile transient Node head;
            private volatile transient Node tail;
            private volatile transient int sweepVotes;
            private static final int NOW = 0;
            private static final int ASYNC = 1;
            private static final int SYNC = 2;
            private static final int TIMED = 3;
            private static final Unsafe UNSAFE;
            private static final long headOffset;
            private static final long tailOffset;
            private static final long sweepVotesOffset;

            static 
            {
/*1350*/        try
                {
/*1350*/            UNSAFE = getUnsafe();
/*1351*/            /*<invalid signature>*/java.lang.Object local = org/glassfish/jersey/internal/util/collection/LinkedTransferQueue;
/*1352*/            headOffset = UNSAFE.objectFieldOffset(local.getDeclaredField("head"));
/*1354*/            tailOffset = UNSAFE.objectFieldOffset(local.getDeclaredField("tail"));
/*1356*/            sweepVotesOffset = UNSAFE.objectFieldOffset(local.getDeclaredField("sweepVotes"));
                }
/*1358*/        catch(Exception exception)
                {
/*1359*/            throw new Error(exception);
                }
            }
}
