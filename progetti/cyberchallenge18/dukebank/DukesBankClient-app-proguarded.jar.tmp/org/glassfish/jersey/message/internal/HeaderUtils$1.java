// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HeaderUtils.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.ext.RuntimeDelegate;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HeaderUtils

static class val.delegate
    implements Function
{

            public final String apply(Object obj)
            {
/* 165*/        if(obj == null)
/* 165*/            return "[null]";
/* 165*/        else
/* 165*/            return HeaderUtils.asString(obj, val$delegate);
            }

            public final volatile Object apply(Object obj)
            {
/* 162*/        return apply(obj);
            }

            final RuntimeDelegate val$delegate;

            _cls9(RuntimeDelegate runtimedelegate)
            {
/* 162*/        val$delegate = runtimedelegate;
/* 162*/        super();
            }
}
