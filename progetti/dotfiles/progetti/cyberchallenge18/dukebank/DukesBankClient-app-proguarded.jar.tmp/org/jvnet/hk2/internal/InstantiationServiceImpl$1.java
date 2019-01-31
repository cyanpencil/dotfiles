// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InstantiationServiceImpl.java

package org.jvnet.hk2.internal;

import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.InstantiationData;

// Referenced classes of package org.jvnet.hk2.internal:
//            InstantiationServiceImpl

class val.head
    implements InstantiationData
{

            public Injectee getParentInjectee()
            {
/*  76*/        return val$head;
            }

            public String toString()
            {
/*  81*/        return (new StringBuilder("InstantiationData(")).append(val$head).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            final Injectee val$head;
            final InstantiationServiceImpl this$0;

            ()
            {
/*  72*/        this$0 = final_instantiationserviceimpl;
/*  72*/        val$head = Injectee.this;
/*  72*/        super();
            }
}
