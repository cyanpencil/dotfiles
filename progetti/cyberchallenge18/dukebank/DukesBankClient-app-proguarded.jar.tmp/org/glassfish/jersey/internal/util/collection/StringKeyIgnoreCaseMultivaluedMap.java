// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StringKeyIgnoreCaseMultivaluedMap.java

package org.glassfish.jersey.internal.util.collection;

import javax.ws.rs.core.AbstractMultivaluedMap;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            KeyComparatorLinkedHashMap, StringIgnoreCaseKeyComparator

public class StringKeyIgnoreCaseMultivaluedMap extends AbstractMultivaluedMap
{

            public StringKeyIgnoreCaseMultivaluedMap()
            {
/*  54*/        super(new KeyComparatorLinkedHashMap(StringIgnoreCaseKeyComparator.SINGLETON));
            }
}
