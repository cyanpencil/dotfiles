// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MediaTypes.java

package org.glassfish.jersey.message.internal;

import java.util.Comparator;
import java.util.List;
import javax.ws.rs.core.MediaType;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            MediaTypes

static class 
    implements Comparator
{

            public final int compare(List list, List list1)
            {
/* 115*/        return MediaTypes.PARTIAL_ORDER_COMPARATOR.compare(getLeastSpecific(list), getLeastSpecific(list1));
            }

            private MediaType getLeastSpecific(List list)
            {
/* 120*/        return (MediaType)list.get(list.size() - 1);
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/* 111*/        return compare((List)obj, (List)obj1);
            }

            ()
            {
            }
}
