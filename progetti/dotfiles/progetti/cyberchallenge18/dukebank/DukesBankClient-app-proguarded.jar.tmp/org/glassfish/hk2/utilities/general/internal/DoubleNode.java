// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DoubleNode.java

package org.glassfish.hk2.utilities.general.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class DoubleNode
{

            public DoubleNode(Object obj, Object obj1, ReferenceQueue referencequeue)
            {
/*  61*/        weakKey = new WeakReference(obj, referencequeue);
/*  62*/        value = obj1;
            }

            public DoubleNode getPrevious()
            {
/*  69*/        return previous;
            }

            public void setPrevious(DoubleNode doublenode)
            {
/*  76*/        previous = doublenode;
            }

            public DoubleNode getNext()
            {
/*  83*/        return next;
            }

            public void setNext(DoubleNode doublenode)
            {
/*  90*/        next = doublenode;
            }

            public WeakReference getWeakKey()
            {
/*  97*/        return weakKey;
            }

            public Object getValue()
            {
/* 104*/        return value;
            }

            public Object getHardenedKey()
            {
/* 111*/        return hardenedKey;
            }

            public void setHardenedKey(Object obj)
            {
/* 118*/        hardenedKey = obj;
            }

            private final WeakReference weakKey;
            private final Object value;
            private DoubleNode previous;
            private DoubleNode next;
            private Object hardenedKey;
}
