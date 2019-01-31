// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Collector.java

package org.jvnet.hk2.internal;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import org.glassfish.hk2.api.MultiException;

public class Collector
{

            public Collector()
            {
            }

            public void addMultiException(MultiException multiexception)
            {
/*  58*/        if(multiexception == null)
/*  58*/            return;
/*  59*/        if(throwables == null)
/*  59*/            throwables = new LinkedHashSet();
/*  61*/        throwables.addAll(multiexception.getErrors());
            }

            public void addThrowable(Throwable throwable)
            {
/*  69*/        if(throwable == null)
/*  69*/            return;
/*  70*/        if(throwables == null)
/*  70*/            throwables = new LinkedHashSet();
/*  72*/        if(throwable instanceof MultiException)
                {
/*  73*/            throwables.addAll(((MultiException)throwable).getErrors());
/*  73*/            return;
                } else
                {
/*  76*/            throwables.add(throwable);
/*  78*/            return;
                }
            }

            public void throwIfErrors()
                throws MultiException
            {
/*  87*/        if(throwables == null || throwables.isEmpty())
/*  87*/            return;
/*  89*/        else
/*  89*/            throw new MultiException(new LinkedList(throwables));
            }

            public boolean hasErrors()
            {
/*  98*/        return throwables != null && !throwables.isEmpty();
            }

            private LinkedHashSet throwables;
}
