// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceFinder.java

package org.glassfish.jersey.internal;

import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.util.ReflectionHelper;

// Referenced classes of package org.glassfish.jersey.internal:
//            LocalizationMessages, ServiceConfigurationError, ServiceFinder

static final class r extends r
    implements Iterator
{

            public final boolean hasNext()
                throws ServiceConfigurationError
            {
/* 699*/        if(nextName != null)
/* 700*/            return true;
/* 702*/        setConfigs();
_L4:
/* 704*/        if(nextName != null)
/* 705*/            break; /* Loop/switch isn't completed */
/* 705*/        for(; pending == null || !pending.hasNext(); pending = ServiceFinder.access$400(serviceName, (URL)configs.nextElement(), returned))
/* 706*/            if(!configs.hasMoreElements())
/* 707*/                return false;

/* 711*/        nextName = (String)pending.next();
/* 713*/        t = service.cast(((Class)AccessController.doPrivileged(ReflectionHelper.classForNameWithExceptionPEA(nextName, loader))).newInstance());
/* 780*/        continue; /* Loop/switch isn't completed */
                Object obj;
/* 716*/        obj;
/* 717*/        if(ignoreOnClassNotFound)
                {
/* 718*/            if(ServiceFinder.access$500().isLoggable(Level.CONFIG))
/* 719*/                ServiceFinder.access$500().log(Level.CONFIG, LocalizationMessages.PROVIDER_COULD_NOT_BE_CREATED(nextName, service, ((InstantiationException) (obj)).getLocalizedMessage()));
/* 723*/            nextName = null;
                } else
                {
/* 725*/            ServiceFinder.access$700(serviceName, LocalizationMessages.PROVIDER_COULD_NOT_BE_CREATED(nextName, service, ((InstantiationException) (obj)).getLocalizedMessage()), ((Throwable) (obj)));
                }
/* 780*/        continue; /* Loop/switch isn't completed */
/* 729*/        obj;
/* 730*/        ServiceFinder.access$700(serviceName, LocalizationMessages.PROVIDER_COULD_NOT_BE_CREATED(nextName, service, ((IllegalAccessException) (obj)).getLocalizedMessage()), ((Throwable) (obj)));
/* 780*/        continue; /* Loop/switch isn't completed */
/* 734*/        JVM INSTR pop ;
/* 735*/        handleClassNotFoundException();
/* 780*/        continue; /* Loop/switch isn't completed */
/* 736*/        obj;
/* 738*/        if(ignoreOnClassNotFound)
                {
/* 739*/            if(ServiceFinder.access$500().isLoggable(Level.CONFIG))
/* 742*/                ServiceFinder.access$500().log(Level.CONFIG, LocalizationMessages.DEPENDENT_CLASS_OF_PROVIDER_NOT_FOUND(((NoClassDefFoundError) (obj)).getLocalizedMessage(), nextName, service));
/* 746*/            nextName = null;
                } else
                {
/* 748*/            ServiceFinder.access$700(serviceName, LocalizationMessages.DEPENDENT_CLASS_OF_PROVIDER_NOT_FOUND(((NoClassDefFoundError) (obj)).getLocalizedMessage(), nextName, service), ((Throwable) (obj)));
                }
/* 780*/        continue; /* Loop/switch isn't completed */
/* 754*/        JVM INSTR dup ;
                Object obj1;
/* 755*/        obj1;
/* 755*/        getCause();
/* 755*/        JVM INSTR dup ;
/* 756*/        obj1;
/* 756*/        JVM INSTR instanceof #4   <Class ClassNotFoundException>;
/* 756*/        JVM INSTR ifeq 344;
                   goto _L1 _L2
_L1:
/* 757*/        break MISSING_BLOCK_LABEL_337;
_L2:
/* 757*/        break MISSING_BLOCK_LABEL_344;
/* 757*/        handleClassNotFoundException();
/* 757*/        continue; /* Loop/switch isn't completed */
/* 758*/        if(obj1 instanceof ClassFormatError)
                {
/* 760*/            if(ignoreOnClassNotFound)
                    {
/* 761*/                if(ServiceFinder.access$500().isLoggable(Level.CONFIG))
/* 762*/                    ServiceFinder.access$500().log(Level.CONFIG, LocalizationMessages.DEPENDENT_CLASS_OF_PROVIDER_FORMAT_ERROR(((Throwable) (obj1)).getLocalizedMessage(), nextName, service));
/* 766*/                nextName = null;
                    } else
                    {
/* 768*/                ServiceFinder.access$700(serviceName, LocalizationMessages.DEPENDENT_CLASS_OF_PROVIDER_FORMAT_ERROR(((Throwable) (obj1)).getLocalizedMessage(), nextName, service), ((Throwable) (obj1)));
                    }
                } else
                {
/* 775*/            ServiceFinder.access$700(serviceName, LocalizationMessages.PROVIDER_COULD_NOT_BE_CREATED(nextName, service, ((Throwable) (obj1)).getLocalizedMessage()), ((Throwable) (obj1)));
                }
/* 780*/        if(true) goto _L4; else goto _L3
_L3:
/* 782*/        return true;
            }

            public final Object next()
            {
/* 787*/        if(!hasNext())
/* 788*/            throw new NoSuchElementException();
/* 790*/        nextName = null;
/* 791*/        if(ServiceFinder.access$500().isLoggable(Level.FINEST))
/* 792*/            ServiceFinder.access$500().log(Level.FINEST, (new StringBuilder("Loading next object: ")).append(t.getClass().getName()).toString());
/* 794*/        return t;
            }

            private void handleClassNotFoundException()
                throws ServiceConfigurationError
            {
/* 798*/        if(ignoreOnClassNotFound)
                {
/* 800*/            if(ServiceFinder.access$500().isLoggable(Level.CONFIG))
/* 801*/                ServiceFinder.access$500().log(Level.CONFIG, LocalizationMessages.PROVIDER_NOT_FOUND(nextName, service));
/* 804*/            nextName = null;
/* 804*/            return;
                } else
                {
/* 806*/            ServiceFinder.access$300(serviceName, LocalizationMessages.PROVIDER_NOT_FOUND(nextName, service));
/* 809*/            return;
                }
            }

            private Object t;

            private r(Class class1, String s, ClassLoader classloader, boolean flag)
            {
/* 694*/        super(class1, s, classloader, flag);
            }

}
