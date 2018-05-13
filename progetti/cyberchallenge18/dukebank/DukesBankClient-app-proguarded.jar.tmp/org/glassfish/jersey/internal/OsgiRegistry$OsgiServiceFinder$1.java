// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OsgiRegistry.java

package org.glassfish.jersey.internal;

import java.util.Iterator;
import java.util.List;

// Referenced classes of package org.glassfish.jersey.internal:
//            LocalizationMessages, OsgiRegistry, ServiceConfigurationError

class val.providerClasses
    implements Iterator
{

            public boolean hasNext()
            {
/* 149*/        return it.hasNext();
            }

            public Object next()
            {
/* 155*/        Object obj = (Class)it.next();
/* 157*/        return ((Class) (obj)).newInstance();
                Exception exception;
/* 158*/        exception;
/* 159*/        ((ServiceConfigurationError) (obj = new ServiceConfigurationError((new StringBuilder()).append(val$serviceName).append(": ").append(LocalizationMessages.PROVIDER_COULD_NOT_BE_CREATED(((Class) (obj)).getName(), val$serviceClass, exception.getLocalizedMessage())).toString()))).initCause(exception);
/* 163*/        throw obj;
            }

            public void remove()
            {
/* 169*/        throw new UnsupportedOperationException();
            }

            Iterator it;
            final List val$providerClasses;
            final String val$serviceName;
            final Class val$serviceClass;
            final se this$1;

            ()
            {
/* 143*/        this$1 = final_;
/* 143*/        val$providerClasses = list;
/* 143*/        val$serviceName = s;
/* 143*/        val$serviceClass = Class.this;
/* 143*/        super();
/* 145*/        it = val$providerClasses.iterator();
            }
}
