// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorImpl.java

package org.jvnet.hk2.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.security.PrivilegedAction;
import java.util.List;

// Referenced classes of package org.jvnet.hk2.internal:
//            ServiceLocatorImpl

class val.qualifiers
    implements PrivilegedAction
{

            public List run()
            {
/* 781*/        return getAllServices(val$contractOrImpl, val$qualifiers);
            }

            public volatile Object run()
            {
/* 777*/        return run();
            }

            final Type val$contractOrImpl;
            final Annotation val$qualifiers[];
            final ServiceLocatorImpl this$0;

            ()
            {
/* 777*/        this$0 = final_servicelocatorimpl;
/* 777*/        val$contractOrImpl = type;
/* 777*/        val$qualifiers = _5B_Ljava.lang.annotation.Annotation_3B_.this;
/* 777*/        super();
            }
}
