// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InstantiationServiceImpl.java

package org.jvnet.hk2.internal;

import java.util.HashMap;
import java.util.LinkedList;
import org.glassfish.hk2.api.*;

public class InstantiationServiceImpl
    implements InstantiationService
{

            public InstantiationServiceImpl()
            {
            }

            public synchronized InstantiationData getInstantiationData()
            {
/*  64*/        long l = Thread.currentThread().getId();
                final Injectee head;
/*  66*/        if((head = (LinkedList)injecteeStack.get(Long.valueOf(l))) == null)
/*  67*/            return null;
/*  68*/        if(head.isEmpty())
                {
/*  68*/            return null;
                } else
                {
/*  70*/            head = (Injectee)head.getLast();
/*  72*/            return new InstantiationData() {

                        public Injectee getParentInjectee()
                        {
/*  76*/                    return head;
                        }

                        public String toString()
                        {
/*  81*/                    return (new StringBuilder("InstantiationData(")).append(head).append(",").append(System.identityHashCode(this)).append(")").toString();
                        }

                        final Injectee val$head;
                        final InstantiationServiceImpl this$0;

                    
                    {
/*  72*/                this$0 = InstantiationServiceImpl.this;
/*  72*/                head = injectee;
/*  72*/                super();
                    }
            };
                }
            }

            public synchronized void pushInjecteeParent(Injectee injectee)
            {
/*  89*/        long l = Thread.currentThread().getId();
                LinkedList linkedlist;
/*  91*/        if((linkedlist = (LinkedList)injecteeStack.get(Long.valueOf(l))) == null)
                {
/*  93*/            linkedlist = new LinkedList();
/*  94*/            injecteeStack.put(Long.valueOf(l), linkedlist);
                }
/*  97*/        linkedlist.addLast(injectee);
            }

            public synchronized void popInjecteeParent()
            {
/* 101*/        long l = Thread.currentThread().getId();
                LinkedList linkedlist;
/* 103*/        if((linkedlist = (LinkedList)injecteeStack.get(Long.valueOf(l))) == null)
/* 104*/            return;
/* 106*/        linkedlist.removeLast();
/* 108*/        if(linkedlist.isEmpty())
/* 110*/            injecteeStack.remove(Long.valueOf(l));
            }

            public String toString()
            {
/* 116*/        return (new StringBuilder("InstantiationServiceImpl(")).append(injecteeStack.keySet()).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private final HashMap injecteeStack = new HashMap();
}
