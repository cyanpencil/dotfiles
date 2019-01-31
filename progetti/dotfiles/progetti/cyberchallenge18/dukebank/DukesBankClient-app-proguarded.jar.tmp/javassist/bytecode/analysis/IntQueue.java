// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   IntQueue.java

package javassist.bytecode.analysis;

import java.util.NoSuchElementException;

class IntQueue
{
    static class Entry
    {

                private Entry next;
                private int value;




                private Entry(int i)
                {
/*  25*/            value = i;
                }

    }


            IntQueue()
            {
            }

            void add(int i)
            {
/*  33*/        i = new Entry(i);
/*  34*/        if(tail != null)
/*  35*/            tail.next = i;
/*  36*/        tail = i;
/*  38*/        if(head == null)
/*  39*/            head = i;
            }

            boolean isEmpty()
            {
/*  43*/        return head == null;
            }

            int take()
            {
/*  47*/        if(head == null)
/*  48*/            throw new NoSuchElementException();
/*  50*/        int i = head.value;
/*  51*/        head = head.next;
/*  52*/        if(head == null)
/*  53*/            tail = null;
/*  55*/        return i;
            }

            private Entry head;
            private Entry tail;
}
