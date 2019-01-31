// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RequestScope.java

package org.glassfish.jersey.process.internal;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import jersey.repackaged.com.google.common.base.MoreObjects;
import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.collect.Sets;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ScopedBindingBuilder;
import org.glassfish.jersey.internal.Errors;
import org.glassfish.jersey.internal.util.*;

// Referenced classes of package org.glassfish.jersey.process.internal:
//            RequestScoped

public class RequestScope
    implements Context
{
    public static final class Instance
    {

                private Instance getReference()
                {
/* 487*/            referenceCounter.incrementAndGet();
/* 488*/            return this;
                }

                final Object get(ActiveDescriptor activedescriptor)
                {
/* 500*/            return store.get(activedescriptor);
                }

                final Object put(ActiveDescriptor activedescriptor, Object obj)
                {
/* 514*/            Preconditions.checkState(!store.containsKey(activedescriptor), "An instance for the descriptor %s was already seeded in this scope. Old instance: %s New instance: %s", new Object[] {
/* 514*/                activedescriptor, store.get(activedescriptor), obj
                    });
/* 520*/            return store.put(activedescriptor, obj);
                }

                final void remove(ActiveDescriptor activedescriptor)
                {
                    Object obj;
/* 530*/            if((obj = store.remove(activedescriptor)) != null)
/* 532*/                activedescriptor.dispose(obj);
                }

                private boolean contains(ActiveDescriptor activedescriptor)
                {
/* 537*/            return store.containsKey(activedescriptor);
                }

                public final void release()
                {
/* 546*/            if(referenceCounter.decrementAndGet() > 0)
/* 548*/                break MISSING_BLOCK_LABEL_89;
                    ActiveDescriptor activedescriptor;
/* 548*/            for(Iterator iterator = Sets.newHashSet(store.keySet()).iterator(); iterator.hasNext(); remove(activedescriptor))
/* 548*/                activedescriptor = (ActiveDescriptor)iterator.next();

/* 552*/            RequestScope.logger.debugLog("Released scope instance {0}", new Object[] {
/* 552*/                this
                    });
/* 553*/            return;
                    Exception exception;
/* 552*/            exception;
/* 552*/            RequestScope.logger.debugLog("Released scope instance {0}", new Object[] {
/* 552*/                this
                    });
/* 552*/            throw exception;
                }

                public final String toString()
                {
/* 559*/            return MoreObjects.toStringHelper(this).add("id", id.value()).add("referenceCounter", referenceCounter.get()).add("store size", store.size()).toString();
                }

                private final LazyUid id;
                private final Map store;
                private final AtomicInteger referenceCounter;



                private Instance()
                {
/* 462*/            id = new LazyUid();
/* 473*/            store = new HashMap();
/* 474*/            referenceCounter = new AtomicInteger(1);
                }

    }

    public static class Binder extends AbstractBinder
    {

                protected void configure()
                {
/* 202*/            bind(new RequestScope()).to(org/glassfish/jersey/process/internal/RequestScope);
                }

                public Binder()
                {
                }
    }


            public RequestScope()
            {
/* 148*/        isActive = true;
            }

            public Class getScope()
            {
/* 152*/        return org/glassfish/jersey/process/internal/RequestScoped;
            }

            public Object findOrCreate(ActiveDescriptor activedescriptor, ServiceHandle servicehandle)
            {
                Instance instance;
                Object obj;
/* 158*/        if((obj = (instance = current()).get(activedescriptor)) == null)
                {
/* 162*/            obj = activedescriptor.create(servicehandle);
/* 163*/            instance.put(activedescriptor, obj);
                }
/* 165*/        return obj;
            }

            public boolean containsKey(ActiveDescriptor activedescriptor)
            {
                Instance instance;
/* 170*/        return (instance = current()).contains(activedescriptor);
            }

            public boolean supportsNullCreation()
            {
/* 176*/        return true;
            }

            public boolean isActive()
            {
/* 181*/        return isActive;
            }

            public void destroyOne(ActiveDescriptor activedescriptor)
            {
                Instance instance;
/* 186*/        (instance = current()).remove(activedescriptor);
            }

            public void shutdown()
            {
/* 192*/        isActive = false;
            }

            public Instance referenceCurrent()
                throws IllegalStateException
            {
/* 226*/        return current().getReference();
            }

            private Instance current()
            {
/* 230*/        Preconditions.checkState(isActive, "Request scope has been already shut down.");
                Instance instance;
/* 232*/        Preconditions.checkState((instance = (Instance)currentScopeInstance.get()) != null, "Not inside a request scope.");
/* 235*/        return instance;
            }

            private Instance retrieveCurrent()
            {
/* 239*/        Preconditions.checkState(isActive, "Request scope has been already shut down.");
/* 240*/        return (Instance)currentScopeInstance.get();
            }

            private void setCurrent(Instance instance)
            {
/* 244*/        Preconditions.checkState(isActive, "Request scope has been already shut down.");
/* 245*/        currentScopeInstance.set(instance);
            }

            private void resumeCurrent(Instance instance)
            {
/* 249*/        currentScopeInstance.set(instance);
            }

            public Instance suspendCurrent()
            {
                Instance instance;
/* 272*/        if((instance = retrieveCurrent()) == null)
/* 274*/            return null;
/* 277*/        Instance instance1 = instance.getReference();
/* 279*/        logger.debugLog("Returned a new reference of the request scope instance {0}", new Object[] {
/* 279*/            instance
                });
/* 279*/        return instance1;
                Exception exception;
/* 279*/        exception;
/* 279*/        logger.debugLog("Returned a new reference of the request scope instance {0}", new Object[] {
/* 279*/            instance
                });
/* 279*/        throw exception;
            }

            public Instance createInstance()
            {
/* 297*/        return new Instance();
            }

            public void runInScope(Instance instance, Runnable runnable)
            {
/* 314*/        Instance instance1 = retrieveCurrent();
/* 316*/        setCurrent(instance.getReference());
/* 317*/        Errors.process(runnable);
/* 319*/        instance.release();
/* 320*/        resumeCurrent(instance1);
/* 321*/        return;
/* 319*/        runnable;
/* 319*/        instance.release();
/* 320*/        resumeCurrent(instance1);
/* 320*/        throw runnable;
            }

            public void runInScope(Runnable runnable)
            {
                Instance instance;
                Instance instance1;
/* 336*/        instance = retrieveCurrent();
/* 337*/        instance1 = createInstance();
/* 339*/        setCurrent(instance1);
/* 340*/        Errors.process(runnable);
/* 342*/        instance1.release();
/* 343*/        resumeCurrent(instance);
/* 344*/        return;
/* 342*/        runnable;
/* 342*/        instance1.release();
/* 343*/        resumeCurrent(instance);
/* 343*/        throw runnable;
            }

            public Object runInScope(Instance instance, Callable callable)
                throws Exception
            {
/* 364*/        Instance instance1 = retrieveCurrent();
/* 366*/        setCurrent(instance.getReference());
/* 367*/        callable = ((Callable) (Errors.process(callable)));
/* 369*/        instance.release();
/* 370*/        resumeCurrent(instance1);
/* 370*/        return callable;
/* 369*/        callable;
/* 369*/        instance.release();
/* 370*/        resumeCurrent(instance1);
/* 370*/        throw callable;
            }

            public Object runInScope(Callable callable)
                throws Exception
            {
                Instance instance;
                Instance instance1;
/* 389*/        instance = retrieveCurrent();
/* 390*/        instance1 = createInstance();
/* 392*/        setCurrent(instance1);
/* 393*/        callable = ((Callable) (Errors.process(callable)));
/* 395*/        instance1.release();
/* 396*/        resumeCurrent(instance);
/* 396*/        return callable;
/* 395*/        callable;
/* 395*/        instance1.release();
/* 396*/        resumeCurrent(instance);
/* 396*/        throw callable;
            }

            public Object runInScope(Instance instance, Producer producer)
            {
/* 416*/        Instance instance1 = retrieveCurrent();
/* 418*/        setCurrent(instance.getReference());
/* 419*/        producer = ((Producer) (Errors.process(producer)));
/* 421*/        instance.release();
/* 422*/        resumeCurrent(instance1);
/* 422*/        return producer;
/* 421*/        producer;
/* 421*/        instance.release();
/* 422*/        resumeCurrent(instance1);
/* 422*/        throw producer;
            }

            public Object runInScope(Producer producer)
            {
                Instance instance;
                Instance instance1;
/* 440*/        instance = retrieveCurrent();
/* 441*/        instance1 = createInstance();
/* 443*/        setCurrent(instance1);
/* 444*/        producer = ((Producer) (Errors.process(producer)));
/* 446*/        instance1.release();
/* 447*/        resumeCurrent(instance);
/* 447*/        return producer;
/* 446*/        producer;
/* 446*/        instance1.release();
/* 447*/        resumeCurrent(instance);
/* 447*/        throw producer;
            }

            private static final ExtendedLogger logger;
            private final ThreadLocal currentScopeInstance = new ThreadLocal();
            private volatile boolean isActive;

            static 
            {
/* 142*/        logger = new ExtendedLogger(Logger.getLogger(org/glassfish/jersey/process/internal/RequestScope.getName()), Level.FINEST);
            }

}
