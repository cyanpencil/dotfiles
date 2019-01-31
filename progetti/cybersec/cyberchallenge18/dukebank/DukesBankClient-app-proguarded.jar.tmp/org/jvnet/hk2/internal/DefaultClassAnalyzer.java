// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultClassAnalyzer.java

package org.jvnet.hk2.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import org.glassfish.hk2.api.ClassAnalyzer;
import org.glassfish.hk2.api.MultiException;

// Referenced classes of package org.jvnet.hk2.internal:
//            Collector, Utilities, ServiceLocatorImpl

public class DefaultClassAnalyzer
    implements ClassAnalyzer
{

            public DefaultClassAnalyzer(ServiceLocatorImpl servicelocatorimpl)
            {
/*  74*/        locator = servicelocatorimpl;
            }

            public Constructor getConstructor(Class class1)
                throws MultiException, NoSuchMethodException
            {
                Object obj;
/*  81*/        obj = new Collector();
/*  83*/        class1 = Utilities.findProducerConstructor(class1, locator, ((Collector) (obj)));
/*  87*/        ((Collector) (obj)).throwIfErrors();
/*  97*/        break MISSING_BLOCK_LABEL_72;
/*  89*/        JVM INSTR dup ;
/*  90*/        class1;
/*  90*/        getErrors();
/*  90*/        iterator();
/*  90*/        obj;
                Throwable throwable;
/*  90*/        while(((Iterator) (obj)).hasNext()) 
/*  90*/            if((throwable = (Throwable)((Iterator) (obj)).next()) instanceof NoSuchMethodException)
/*  92*/                throw (NoSuchMethodException)throwable;
/*  96*/        throw class1;
/*  99*/        return class1;
            }

            public Set getInitializerMethods(Class class1)
                throws MultiException
            {
/* 105*/        Collector collector = new Collector();
/* 107*/        class1 = Utilities.findInitializerMethods(class1, locator, collector);
/* 109*/        collector.throwIfErrors();
/* 111*/        return class1;
            }

            public Set getFields(Class class1)
                throws MultiException
            {
/* 116*/        Collector collector = new Collector();
/* 118*/        class1 = Utilities.findInitializerFields(class1, locator, collector);
/* 120*/        collector.throwIfErrors();
/* 122*/        return class1;
            }

            public Method getPostConstructMethod(Class class1)
                throws MultiException
            {
/* 128*/        Collector collector = new Collector();
/* 130*/        class1 = Utilities.findPostConstruct(class1, locator, collector);
/* 132*/        collector.throwIfErrors();
/* 134*/        return class1;
            }

            public Method getPreDestroyMethod(Class class1)
                throws MultiException
            {
/* 139*/        Collector collector = new Collector();
/* 141*/        class1 = Utilities.findPreDestroy(class1, locator, collector);
/* 143*/        collector.throwIfErrors();
/* 145*/        return class1;
            }

            private final ServiceLocatorImpl locator;
}
