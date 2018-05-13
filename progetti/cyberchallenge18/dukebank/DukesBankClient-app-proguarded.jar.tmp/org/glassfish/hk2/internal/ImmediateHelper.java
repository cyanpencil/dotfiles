// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmediateHelper.java

package org.glassfish.hk2.internal;

import java.util.*;
import java.util.concurrent.*;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.ImmediateContext;

public class ImmediateHelper
    implements Runnable, DynamicConfigurationListener, ErrorService, ImmediateController, ValidationService, Validator
{
    static class ImmediateThreadFactory
        implements ThreadFactory
    {

                public Thread newThread(Runnable runnable)
                {
/* 365*/            return runnable = new ImmediateThread(runnable);
                }

                private ImmediateThreadFactory()
                {
                }

    }

    static class ImmediateThread extends Thread
    {

                private ImmediateThread(Runnable runnable)
                {
/* 355*/            super(runnable);
/* 356*/            setDaemon(true);
/* 357*/            setName((new StringBuilder()).append(getClass().getSimpleName()).append("-").append(System.currentTimeMillis()).toString());
                }

    }


            private ImmediateHelper(ServiceLocator servicelocator, ImmediateContext immediatecontext)
            {
/* 115*/        firstTime = true;
/* 116*/        currentState = org.glassfish.hk2.api.ImmediateController.ImmediateServiceState.SUSPENDED;
/* 117*/        currentExecutor = DEFAULT_EXECUTOR;
/* 118*/        decayTime = 20000L;
/* 122*/        locator = servicelocator;
/* 123*/        immediateContext = immediatecontext;
            }

            private boolean hasWork()
            {
/* 131*/        long l = Thread.currentThread().getId();
/* 133*/        boolean flag = firstTime;
/* 134*/        firstTime = false;
/* 136*/        boolean flag1 = tidsWithWork.contains(Long.valueOf(l));
/* 137*/        tidsWithWork.remove(Long.valueOf(l));
/* 139*/        if(flag1 || !flag)
/* 139*/            return flag1;
                List list;
/* 146*/        return !(list = getImmediateServices()).isEmpty();
            }

            private void doWorkIfWeHaveSome()
            {
/* 154*/        if(!hasWork())
/* 155*/            return;
/* 158*/        outstandingJob = true;
/* 160*/        if(!threadAvailable)
                {
/* 161*/            threadAvailable = true;
/* 163*/            currentExecutor.execute(this);
/* 163*/            return;
                }
/* 165*/        if(waitingForWork)
/* 166*/            queueLock.notify();
            }

            public void configurationChanged()
            {
/* 172*/label0:
                {
/* 172*/            synchronized(queueLock)
                    {
/* 173*/                if(!currentState.equals(org.glassfish.hk2.api.ImmediateController.ImmediateServiceState.SUSPENDED))
/* 173*/                    break label0;
                    }
/* 173*/            return;
                }
/* 175*/        doWorkIfWeHaveSome();
/* 176*/        obj;
/* 176*/        JVM INSTR monitorexit ;
            }

            public Filter getLookupFilter()
            {
/* 181*/        return immediateContext.getValidationFilter();
            }

            public Validator getValidator()
            {
/* 186*/        return this;
            }

            public void onFailure(ErrorInformation errorinformation)
                throws MultiException
            {
/* 192*/        if(!ErrorType.DYNAMIC_CONFIGURATION_FAILURE.equals(errorinformation.getErrorType()))
                {
/* 194*/            long l = Thread.currentThread().getId();
/* 196*/            synchronized(queueLock)
                    {
/* 197*/                tidsWithWork.remove(Long.valueOf(l));
                    }
/* 198*/            return;
                } else
                {
/* 203*/            return;
                }
            }

            public boolean validate(ValidationInformation validationinformation)
            {
/* 207*/        if(validationinformation.getOperation().equals(Operation.BIND) || validationinformation.getOperation().equals(Operation.UNBIND))
                {
/* 209*/            long l = Thread.currentThread().getId();
/* 211*/            synchronized(queueLock)
                    {
/* 212*/                tidsWithWork.add(Long.valueOf(l));
                    }
                }
/* 216*/        return true;
            }

            public void run()
            {
_L4:
/* 226*/        Object obj = queueLock;
/* 226*/        JVM INSTR monitorenter ;
/* 227*/        long l = decayTime;
_L2:
                long l1;
/* 230*/        if(!currentState.equals(org.glassfish.hk2.api.ImmediateController.ImmediateServiceState.RUNNING) || outstandingJob || l <= 0L)
/* 232*/            break MISSING_BLOCK_LABEL_89;
/* 232*/        waitingForWork = true;
/* 233*/        l1 = System.currentTimeMillis();
/* 235*/        try
                {
/* 235*/            queueLock.wait(l);
/* 241*/            break MISSING_BLOCK_LABEL_73;
                }
/* 237*/        catch(InterruptedException _ex)
                {
/* 238*/            threadAvailable = false;
/* 239*/            waitingForWork = false;
                }
/* 240*/        return;
/* 243*/        long l2 = System.currentTimeMillis() - l1;
/* 244*/        l -= l2;
/* 245*/        if(true) goto _L2; else goto _L1
_L1:
/* 246*/        waitingForWork = false;
/* 248*/        if(outstandingJob && !currentState.equals(org.glassfish.hk2.api.ImmediateController.ImmediateServiceState.SUSPENDED))
/* 249*/            break MISSING_BLOCK_LABEL_122;
/* 249*/        threadAvailable = false;
/* 250*/        return;
/* 253*/        outstandingJob = false;
/* 254*/        break MISSING_BLOCK_LABEL_137;
                Exception exception;
/* 254*/        exception;
/* 254*/        throw exception;
/* 256*/        immediateContext.doWork();
/* 256*/        if(true) goto _L4; else goto _L3
_L3:
            }

            public Executor getExecutor()
            {
/* 266*/        Object obj = queueLock;
/* 266*/        JVM INSTR monitorenter ;
/* 267*/        return currentExecutor;
                Exception exception;
/* 268*/        exception;
/* 268*/        throw exception;
            }

            public void setExecutor(Executor executor)
                throws IllegalStateException
            {
/* 276*/        synchronized(queueLock)
                {
/* 277*/            if(currentState.equals(org.glassfish.hk2.api.ImmediateController.ImmediateServiceState.RUNNING))
/* 278*/                throw new IllegalStateException("ImmediateSerivce attempt made to change executor while in RUNNING state");
/* 281*/            currentExecutor = executor != null ? executor : DEFAULT_EXECUTOR;
                }
            }

            public long getThreadInactivityTimeout()
            {
/* 291*/        Object obj = queueLock;
/* 291*/        JVM INSTR monitorenter ;
/* 292*/        return decayTime;
                Exception exception;
/* 293*/        exception;
/* 293*/        throw exception;
            }

            public void setThreadInactivityTimeout(long l)
                throws IllegalStateException
            {
/* 302*/        synchronized(queueLock)
                {
/* 303*/            if(l < 0L)
/* 304*/                throw new IllegalArgumentException();
/* 307*/            decayTime = l;
                }
            }

            public org.glassfish.hk2.api.ImmediateController.ImmediateServiceState getImmediateState()
            {
/* 317*/        Object obj = queueLock;
/* 317*/        JVM INSTR monitorenter ;
/* 318*/        return currentState;
                Exception exception;
/* 319*/        exception;
/* 319*/        throw exception;
            }

            public void setImmediateState(org.glassfish.hk2.api.ImmediateController.ImmediateServiceState immediateservicestate)
            {
/* 327*/label0:
                {
/* 327*/            synchronized(queueLock)
                    {
/* 328*/                if(immediateservicestate == null)
/* 328*/                    throw new IllegalArgumentException();
/* 330*/                if(immediateservicestate != currentState)
/* 330*/                    break label0;
                    }
/* 330*/            return;
                }
/* 331*/        currentState = immediateservicestate;
/* 333*/        if(currentState.equals(org.glassfish.hk2.api.ImmediateController.ImmediateServiceState.RUNNING))
/* 334*/            doWorkIfWeHaveSome();
/* 336*/        obj;
/* 336*/        JVM INSTR monitorexit ;
            }

            private List getImmediateServices()
            {
                List list;
/* 343*/        try
                {
/* 343*/            list = locator.getDescriptors(immediateContext.getValidationFilter());
                }
/* 345*/        catch(IllegalStateException _ex)
                {
/* 347*/            list = Collections.emptyList();
                }
/* 350*/        return list;
            }

            private static final ThreadFactory THREAD_FACTORY;
            private static final Executor DEFAULT_EXECUTOR;
            private final ServiceLocator locator;
            private final ImmediateContext immediateContext;
            private final HashSet tidsWithWork = new HashSet();
            private final Object queueLock = new Object();
            private boolean threadAvailable;
            private boolean outstandingJob;
            private boolean waitingForWork;
            private boolean firstTime;
            private org.glassfish.hk2.api.ImmediateController.ImmediateServiceState currentState;
            private Executor currentExecutor;
            private long decayTime;

            static 
            {
/*  99*/        THREAD_FACTORY = new ImmediateThreadFactory();
/* 101*/        DEFAULT_EXECUTOR = new ThreadPoolExecutor(0, 0x7fffffff, 60L, TimeUnit.SECONDS, new SynchronousQueue(true), THREAD_FACTORY);
            }
}
