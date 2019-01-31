// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HeaderUtils.java

package org.glassfish.jersey.message.internal;

import java.util.List;
import javax.ws.rs.ext.RuntimeDelegate;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HeaderUtils

static class val.rd
    implements Function
{

            public final List apply(List list)
            {
/* 188*/        return HeaderUtils.asStringList(list, val$rd);
            }

            public final volatile Object apply(Object obj)
            {
/* 185*/        return apply((List)obj);
            }

            final RuntimeDelegate val$rd;

            _cls9(RuntimeDelegate runtimedelegate)
            {
/* 185*/        val$rd = runtimedelegate;
/* 185*/        super();
            }
}
