// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MultivaluedHashMap.java

package javax.ws.rs.core;

import java.io.Serializable;
import java.util.*;

// Referenced classes of package javax.ws.rs.core:
//            AbstractMultivaluedMap, MultivaluedMap

public class MultivaluedHashMap extends AbstractMultivaluedMap
    implements Serializable
{

            public MultivaluedHashMap()
            {
/* 134*/        super(new HashMap());
            }

            public MultivaluedHashMap(int i)
            {
/* 145*/        super(new HashMap(i));
            }

            public MultivaluedHashMap(int i, float f)
            {
/* 158*/        super(new HashMap(i, f));
            }

            public MultivaluedHashMap(MultivaluedMap multivaluedmap)
            {
/* 171*/        this();
/* 172*/        putAll(multivaluedmap);
            }

            private void putAll(MultivaluedMap multivaluedmap)
            {
                java.util.Map.Entry entry;
/* 184*/        for(multivaluedmap = multivaluedmap.entrySet().iterator(); multivaluedmap.hasNext(); store.put(entry.getKey(), new ArrayList((Collection)entry.getValue())))
/* 184*/            entry = (java.util.Map.Entry)multivaluedmap.next();

            }

            public MultivaluedHashMap(Map map)
            {
/* 198*/        this();
                java.util.Map.Entry entry;
/* 199*/        for(map = map.entrySet().iterator(); map.hasNext(); putSingle(entry.getKey(), entry.getValue()))
/* 199*/            entry = (java.util.Map.Entry)map.next();

            }

            private static final long serialVersionUID = 0xac01d6a8caa8257aL;
}
