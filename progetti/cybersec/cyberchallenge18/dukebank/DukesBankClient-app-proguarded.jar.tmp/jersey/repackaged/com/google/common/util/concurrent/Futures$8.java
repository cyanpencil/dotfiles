// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Futures.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.*;
import jersey.repackaged.com.google.common.base.Optional;
import jersey.repackaged.com.google.common.collect.Lists;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            Futures

static class tureCombiner
    implements tureCombiner
{

            public final List combine(List list)
            {
/*1782*/        ArrayList arraylist = Lists.newArrayList();
                Optional optional;
/*1783*/        for(list = list.iterator(); list.hasNext(); arraylist.add(optional == null ? null : optional.orNull()))
/*1783*/            optional = (Optional)list.next();

/*1786*/        return Collections.unmodifiableList(arraylist);
            }

            public final volatile Object combine(List list)
            {
/*1779*/        return combine(list);
            }

            tureCombiner()
            {
            }
}
