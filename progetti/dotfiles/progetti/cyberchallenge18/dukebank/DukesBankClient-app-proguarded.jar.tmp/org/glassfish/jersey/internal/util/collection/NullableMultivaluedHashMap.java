// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NullableMultivaluedHashMap.java

package org.glassfish.jersey.internal.util.collection;

import java.util.List;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

public class NullableMultivaluedHashMap extends MultivaluedHashMap
{

            public NullableMultivaluedHashMap()
            {
            }

            public NullableMultivaluedHashMap(int i)
            {
/*  62*/        super(i);
            }

            public NullableMultivaluedHashMap(int i, float f)
            {
/*  66*/        super(i, f);
            }

            public NullableMultivaluedHashMap(MultivaluedMap multivaluedmap)
            {
/*  70*/        super(multivaluedmap);
            }

            protected void addFirstNull(List list)
            {
/*  75*/        list.add(null);
            }

            protected void addNull(List list)
            {
/*  80*/        list.add(null);
            }
}
