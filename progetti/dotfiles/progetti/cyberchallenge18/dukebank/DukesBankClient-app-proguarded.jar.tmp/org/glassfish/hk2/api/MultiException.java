// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MultiException.java

package org.glassfish.hk2.api;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;

// Referenced classes of package org.glassfish.hk2.api:
//            HK2RuntimeException

public class MultiException extends HK2RuntimeException
{

            public MultiException()
            {
/*  61*/        lock = new Object();
/*  62*/        throwables = new LinkedList();
/*  63*/        reportToErrorService = true;
            }

            public MultiException(List list)
            {
/*  81*/        super(((Throwable)list.get(0)).getMessage(), (Throwable)list.get(0));
/*  61*/        lock = new Object();
/*  62*/        throwables = new LinkedList();
/*  63*/        reportToErrorService = true;
                Object obj;
/*  83*/        for(list = list.iterator(); list.hasNext();)
/*  83*/            if((obj = (Throwable)list.next()) instanceof MultiException)
                    {
/*  85*/                obj = (MultiException)obj;
/*  87*/                throwables.addAll(((MultiException) (obj)).throwables);
                    } else
                    {
/*  90*/                throwables.add(obj);
                    }

            }

            public MultiException(Throwable throwable, boolean flag)
            {
/* 102*/        super(throwable.getMessage(), throwable);
/*  61*/        lock = new Object();
/*  62*/        throwables = new LinkedList();
/*  63*/        reportToErrorService = true;
/* 104*/        if(throwable instanceof MultiException)
                {
/* 105*/            throwable = (MultiException)throwable;
/* 107*/            throwables.addAll(((MultiException) (throwable)).throwables);
                } else
                {
/* 110*/            throwables.add(throwable);
                }
/* 113*/        reportToErrorService = flag;
            }

            public MultiException(Throwable throwable)
            {
/* 123*/        this(throwable, true);
            }

            public List getErrors()
            {
/* 133*/        Object obj = lock;
/* 133*/        JVM INSTR monitorenter ;
/* 134*/        return Collections.unmodifiableList(throwables);
                Exception exception;
/* 135*/        exception;
/* 135*/        throw exception;
            }

            public void addError(Throwable throwable)
            {
/* 144*/        synchronized(lock)
                {
/* 145*/            throwables.add(throwable);
                }
            }

            public String getMessage()
            {
/* 153*/        Object obj = getErrors();
/* 154*/        StringBuffer stringbuffer = new StringBuffer((new StringBuilder("A MultiException has ")).append(((List) (obj)).size()).append(" exceptions.  They are:\n").toString());
/* 156*/        int i = 1;
                Throwable throwable;
/* 157*/        for(obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext(); stringbuffer.append((new StringBuilder()).append(i++).append(". ").append(throwable.getClass().getName()).append(throwable.getMessage() == null ? "" : (new StringBuilder(": ")).append(throwable.getMessage()).toString()).append("\n").toString()))
/* 157*/            throwable = (Throwable)((Iterator) (obj)).next();

/* 161*/        return stringbuffer.toString();
            }

            public void printStackTrace(PrintStream printstream)
            {
                List list;
/* 168*/        if((list = getErrors()).size() <= 0)
                {
/* 171*/            super.printStackTrace(printstream);
/* 172*/            return;
                }
/* 175*/        int i = 1;
                Throwable throwable;
/* 176*/        for(Iterator iterator = list.iterator(); iterator.hasNext(); throwable.printStackTrace(printstream))
                {
/* 176*/            throwable = (Throwable)iterator.next();
/* 177*/            printstream.println((new StringBuilder("MultiException stack ")).append(i++).append(" of ").append(list.size()).toString());
                }

            }

            public void printStackTrace(PrintWriter printwriter)
            {
                List list;
/* 186*/        if((list = getErrors()).size() <= 0)
                {
/* 189*/            super.printStackTrace(printwriter);
/* 190*/            return;
                }
/* 193*/        int i = 1;
                Throwable throwable;
/* 194*/        for(Iterator iterator = list.iterator(); iterator.hasNext(); throwable.printStackTrace(printwriter))
                {
/* 194*/            throwable = (Throwable)iterator.next();
/* 195*/            printwriter.println((new StringBuilder("MultiException stack ")).append(i++).append(" of ").append(list.size()).toString());
                }

            }

            public boolean getReportToErrorService()
            {
/* 209*/        return reportToErrorService;
            }

            public void setReportToErrorService(boolean flag)
            {
/* 221*/        reportToErrorService = flag;
            }

            public String toString()
            {
/* 226*/        return getMessage();
            }

            private static final long serialVersionUID = 0x1d50de58b7c87e74L;
            private final Object lock;
            private final List throwables;
            private boolean reportToErrorService;
}
