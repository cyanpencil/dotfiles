// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceFinder.java

package org.glassfish.jersey.internal;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.util.ReflectionHelper;

// Referenced classes of package org.glassfish.jersey.internal:
//            LocalizationMessages, ServiceFinder

static final class or extends or
    implements Iterator
{

            public final Class next()
            {
                String s;
/* 640*/        if(!hasNext())
/* 641*/            throw new NoSuchElementException();
/* 643*/        s = nextName;
/* 644*/        nextName = null;
                Object obj;
/* 647*/        obj = (Class)AccessController.doPrivileged(ReflectionHelper.classForNameWithExceptionPEA(s, loader));
/* 650*/        if(ServiceFinder.access$500().isLoggable(Level.FINEST))
/* 651*/            ServiceFinder.access$500().log(Level.FINEST, (new StringBuilder("Loading next class: ")).append(((Class) (obj)).getName()).toString());
/* 654*/        return ((Class) (obj));
/* 656*/        JVM INSTR pop ;
/* 657*/        ServiceFinder.access$300(serviceName, LocalizationMessages.PROVIDER_NOT_FOUND(s, service));
/* 679*/        break MISSING_BLOCK_LABEL_210;
/* 659*/        JVM INSTR dup ;
/* 661*/        obj;
/* 661*/        getCause();
/* 661*/        JVM INSTR dup ;
/* 663*/        obj;
/* 663*/        JVM INSTR instanceof #4   <Class ClassNotFoundException>;
/* 663*/        JVM INSTR ifeq 132;
                   goto _L1 _L2
_L1:
/* 664*/        break MISSING_BLOCK_LABEL_114;
_L2:
/* 664*/        break MISSING_BLOCK_LABEL_132;
/* 664*/        ServiceFinder.access$300(serviceName, LocalizationMessages.PROVIDER_NOT_FOUND(s, service));
/* 664*/        break MISSING_BLOCK_LABEL_210;
/* 666*/        if(obj instanceof NoClassDefFoundError)
/* 667*/            ServiceFinder.access$300(serviceName, LocalizationMessages.DEPENDENT_CLASS_OF_PROVIDER_NOT_FOUND(((Throwable) (obj)).getLocalizedMessage(), s, service));
/* 670*/        else
/* 670*/        if(obj instanceof ClassFormatError)
/* 671*/            ServiceFinder.access$300(serviceName, LocalizationMessages.DEPENDENT_CLASS_OF_PROVIDER_FORMAT_ERROR(((Throwable) (obj)).getLocalizedMessage(), s, service));
/* 675*/        else
/* 675*/            ServiceFinder.access$700(serviceName, LocalizationMessages.PROVIDER_CLASS_COULD_NOT_BE_LOADED(s, service, ((Throwable) (obj)).getLocalizedMessage()), ((Throwable) (obj)));
/* 681*/        return null;
            }

            public final volatile Object next()
            {
/* 627*/        return next();
            }

            private or(Class class1, String s, ClassLoader classloader, boolean flag)
            {
/* 635*/        super(class1, s, classloader, flag);
            }

}
