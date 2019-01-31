// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceHandleImpl.java

package org.jvnet.hk2.internal;

import java.util.Iterator;
import java.util.LinkedList;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.reflection.ReflectionHelper;

// Referenced classes of package org.jvnet.hk2.internal:
//            Closeable, ServiceLocatorImpl, Utilities

public class ServiceHandleImpl
    implements ServiceHandle
{

            ServiceHandleImpl(ServiceLocatorImpl servicelocatorimpl, ActiveDescriptor activedescriptor, Injectee injectee)
            {
/*  68*/        serviceDestroyed = false;
/*  69*/        serviceSet = false;
/*  76*/        root = activedescriptor;
/*  77*/        locator = servicelocatorimpl;
/*  78*/        if(injectee != null)
/*  79*/            injectees.add(injectee);
            }

            public Object getService()
            {
/*  88*/        return getService(((ServiceHandle) (this)));
            }

            private Injectee getLastInjectee()
            {
/*  92*/        Object obj = lock;
/*  92*/        JVM INSTR monitorenter ;
/*  93*/        return injectees.isEmpty() ? null : (Injectee)injectees.getLast();
                Exception exception;
/*  94*/        exception;
/*  94*/        throw exception;
            }

            Object getService(ServiceHandle servicehandle)
            {
                Object obj;
/*  98*/        if((root instanceof Closeable) && ((Closeable) (obj = (Closeable)root)).isClosed())
/* 101*/            throw new IllegalStateException((new StringBuilder("This service has been unbound: ")).append(root).toString());
/* 105*/        obj = lock;
/* 105*/        JVM INSTR monitorenter ;
/* 106*/        if(serviceDestroyed)
/* 106*/            throw new IllegalStateException("Service has been disposed");
/* 108*/        if(serviceSet)
/* 108*/            return service;
                Injectee injectee;
/* 110*/        Class class1 = (injectee = getLastInjectee()) != null ? ReflectionHelper.getRawClass(injectee.getRequiredType()) : null;
/* 114*/        service = Utilities.createService(root, injectee, locator, servicehandle, class1);
/* 116*/        serviceSet = true;
/* 118*/        service;
/* 118*/        obj;
/* 118*/        JVM INSTR monitorexit ;
/* 118*/        return;
/* 119*/        servicehandle;
/* 119*/        throw servicehandle;
            }

            public ActiveDescriptor getActiveDescriptor()
            {
/* 128*/        return root;
            }

            public boolean isActive()
            {
/* 137*/        if(serviceDestroyed)
/* 137*/            return false;
/* 138*/        if(serviceSet)
/* 138*/            return true;
                Context context;
/* 141*/        return (context = locator.resolveContext(root.getScopeAnnotation())).containsKey(root);
/* 144*/        JVM INSTR pop ;
/* 145*/        return false;
            }

            public void destroy()
            {
                boolean flag;
/* 157*/label0:
                {
/* 157*/            if(!root.isReified())
/* 157*/                return;
/* 159*/            synchronized(lock)
                    {
/* 160*/                flag = isActive();
/* 162*/                if(!serviceDestroyed)
/* 162*/                    break label0;
                    }
/* 162*/            return;
                }
/* 163*/        serviceDestroyed = true;
/* 165*/        obj = serviceSet;
/* 166*/        obj1;
/* 166*/        JVM INSTR monitorexit ;
                  goto _L1
/* 166*/        obj;
/* 166*/        throw obj;
_L1:
/* 168*/        if(root.getScopeAnnotation().equals(org/glassfish/hk2/api/PerLookup))
                {
/* 169*/            if(obj != 0)
/* 171*/                root.dispose(service);
                } else
/* 174*/        if(flag)
                {
                    Context context;
/* 177*/            try
                    {
/* 177*/                context = locator.resolveContext(root.getScopeAnnotation());
                    }
/* 179*/            catch(Throwable _ex)
                    {
/* 180*/                return;
                    }
/* 183*/            context.destroyOne(root);
                }
                ServiceHandleImpl servicehandleimpl;
/* 186*/        for(Iterator iterator = subHandles.iterator(); iterator.hasNext(); (servicehandleimpl = (ServiceHandleImpl)iterator.next()).destroy());
/* 191*/        return;
            }

            public void setServiceData(Object obj)
            {
/* 195*/        synchronized(lock)
                {
/* 196*/            serviceData = obj;
                }
            }

            public Object getServiceData()
            {
/* 203*/        Object obj = lock;
/* 203*/        JVM INSTR monitorenter ;
/* 204*/        return serviceData;
                Exception exception;
/* 205*/        exception;
/* 205*/        throw exception;
            }

            public void pushInjectee(Injectee injectee)
            {
/* 209*/        synchronized(lock)
                {
/* 210*/            injectees.add(injectee);
                }
            }

            public void popInjectee()
            {
/* 215*/        synchronized(lock)
                {
/* 216*/            injectees.removeLast();
                }
            }

            public void addSubHandle(ServiceHandleImpl servicehandleimpl)
            {
/* 226*/        subHandles.add(servicehandleimpl);
            }

            public Injectee getOriginalRequest()
            {
                Injectee injectee;
/* 230*/        return injectee = getLastInjectee();
            }

            public String toString()
            {
/* 235*/        return (new StringBuilder("ServiceHandle(")).append(root).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private ActiveDescriptor root;
            private final ServiceLocatorImpl locator;
            private final LinkedList injectees = new LinkedList();
            private final Object lock = new Object();
            private boolean serviceDestroyed;
            private boolean serviceSet;
            private Object service;
            private Object serviceData;
            private final LinkedList subHandles = new LinkedList();
}
