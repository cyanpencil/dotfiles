// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   IndexedListData.java

package org.jvnet.hk2.internal;

import java.util.*;

// Referenced classes of package org.jvnet.hk2.internal:
//            DescriptorComparator, ServiceLocatorImpl, SystemDescriptor

public class IndexedListData
{

            public IndexedListData()
            {
/*  59*/        sorted = true;
            }

            public Collection getSortedList()
            {
/*  62*/        if(sorted)
/*  62*/            return unsortedList;
/*  64*/        IndexedListData indexedlistdata = this;
/*  64*/        JVM INSTR monitorenter ;
/*  65*/        if(sorted)
/*  65*/            return unsortedList;
/*  67*/        if(unsortedList.size() > 1) goto _L2; else goto _L1
_L1:
/*  68*/        sorted = true;
/*  69*/        unsortedList;
/*  69*/        indexedlistdata;
/*  69*/        JVM INSTR monitorexit ;
/*  69*/        return;
_L2:
/*  72*/        Collections.sort(unsortedList, ServiceLocatorImpl.DESCRIPTOR_COMPARATOR);
/*  74*/        sorted = true;
/*  75*/        unsortedList;
/*  75*/        indexedlistdata;
/*  75*/        JVM INSTR monitorexit ;
/*  75*/        return;
                Exception exception;
/*  76*/        exception;
/*  76*/        throw exception;
            }

            public synchronized void addDescriptor(SystemDescriptor systemdescriptor)
            {
/*  80*/        unsortedList.add(systemdescriptor);
/*  82*/        if(unsortedList.size() > 1)
/*  83*/            sorted = false;
/*  86*/        else
/*  86*/            sorted = true;
/*  89*/        systemdescriptor.addList(this);
            }

            public synchronized void removeDescriptor(SystemDescriptor systemdescriptor)
            {
/*  93*/        ListIterator listiterator = unsortedList.listIterator();
/*  94*/        do
                {
/*  94*/            if(!listiterator.hasNext())
/*  95*/                break;
/*  95*/            SystemDescriptor systemdescriptor1 = (SystemDescriptor)listiterator.next();
/*  96*/            if(ServiceLocatorImpl.DESCRIPTOR_COMPARATOR.compare(systemdescriptor, systemdescriptor1) != 0)
/*  97*/                continue;
/*  97*/            listiterator.remove();
/*  98*/            break;
                } while(true);
/* 102*/        if(unsortedList.size() > 1)
/* 103*/            sorted = false;
/* 106*/        else
/* 106*/            sorted = true;
/* 109*/        systemdescriptor.removeList(this);
            }

            public synchronized boolean isEmpty()
            {
/* 113*/        return unsortedList.isEmpty();
            }

            public synchronized void unSort()
            {
/* 120*/        if(unsortedList.size() > 1)
/* 121*/            sorted = false;
            }

            public synchronized void clear()
            {
                SystemDescriptor systemdescriptor;
/* 126*/        for(Iterator iterator = unsortedList.iterator(); iterator.hasNext(); (systemdescriptor = (SystemDescriptor)iterator.next()).removeList(this));
/* 130*/        unsortedList.clear();
            }

            public synchronized int size()
            {
/* 134*/        return unsortedList.size();
            }

            private final ArrayList unsortedList = new ArrayList();
            private volatile boolean sorted;
}
