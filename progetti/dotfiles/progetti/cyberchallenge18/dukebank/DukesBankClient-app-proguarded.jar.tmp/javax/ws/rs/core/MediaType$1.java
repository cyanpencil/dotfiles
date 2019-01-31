// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MediaType.java

package javax.ws.rs.core;

import java.util.Comparator;

// Referenced classes of package javax.ws.rs.core:
//            MediaType

static class 
    implements Comparator
{

            public final int compare(String s, String s1)
            {
/* 187*/        return s.compareToIgnoreCase(s1);
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/* 183*/        return compare((String)obj, (String)obj1);
            }

            ()
            {
            }
}
