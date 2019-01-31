// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapPropertiesDelegate.java

package org.glassfish.jersey.internal;

import java.util.*;

// Referenced classes of package org.glassfish.jersey.internal:
//            PropertiesDelegate

public final class MapPropertiesDelegate
    implements PropertiesDelegate
{

            public MapPropertiesDelegate()
            {
/*  60*/        store = new HashMap();
            }

            public MapPropertiesDelegate(Map map)
            {
/*  69*/        store = map;
            }

            public MapPropertiesDelegate(PropertiesDelegate propertiesdelegate)
            {
/*  79*/        if(propertiesdelegate instanceof MapPropertiesDelegate)
                {
/*  80*/            store = new HashMap(((MapPropertiesDelegate)propertiesdelegate).store);
/*  80*/            return;
                }
/*  82*/        store = new HashMap();
                String s;
/*  83*/        for(Iterator iterator = propertiesdelegate.getPropertyNames().iterator(); iterator.hasNext(); store.put(s, propertiesdelegate.getProperty(s)))
/*  83*/            s = (String)iterator.next();

            }

            public final Object getProperty(String s)
            {
/*  91*/        return store.get(s);
            }

            public final Collection getPropertyNames()
            {
/*  96*/        return Collections.unmodifiableCollection(store.keySet());
            }

            public final void setProperty(String s, Object obj)
            {
/* 101*/        store.put(s, obj);
            }

            public final void removeProperty(String s)
            {
/* 106*/        store.remove(s);
            }

            private final Map store;
}
