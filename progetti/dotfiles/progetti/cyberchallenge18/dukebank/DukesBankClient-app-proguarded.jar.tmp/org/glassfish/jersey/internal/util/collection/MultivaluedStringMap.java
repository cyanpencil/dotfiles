// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MultivaluedStringMap.java

package org.glassfish.jersey.internal.util.collection;

import java.lang.reflect.Constructor;
import java.util.List;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

public class MultivaluedStringMap extends MultivaluedHashMap
{

            public MultivaluedStringMap(MultivaluedMap multivaluedmap)
            {
/*  63*/        super(multivaluedmap);
            }

            public MultivaluedStringMap(int i, float f)
            {
/*  67*/        super(i, f);
            }

            public MultivaluedStringMap(int i)
            {
/*  71*/        super(i);
            }

            public MultivaluedStringMap()
            {
            }

            protected void addFirstNull(List list)
            {
/*  80*/        list.add("");
            }

            protected void addNull(List list)
            {
/*  85*/        list.add(0, "");
            }

            public final Object getFirst(String s, Class class1)
            {
/*  89*/        if((s = (String)getFirst(s)) == null)
/*  91*/            return null;
                Constructor constructor;
/*  95*/        try
                {
/*  95*/            constructor = class1.getConstructor(new Class[] {
/*  95*/                java/lang/String
                    });
                }
/*  96*/        catch(Exception exception)
                {
/*  97*/            throw new IllegalArgumentException((new StringBuilder()).append(class1.getName()).append(" has no String constructor").toString(), exception);
                }
/*  99*/        Object obj = null;
/* 101*/        try
                {
/* 101*/            obj = constructor.newInstance(new Object[] {
/* 101*/                s
                    });
                }
/* 102*/        catch(Exception _ex) { }
/* 104*/        return obj;
            }

            public final Object getFirst(String s, Object obj)
            {
/* 109*/        if((s = (String)getFirst(s)) == null)
/* 111*/            return obj;
/* 114*/        Class class1 = obj.getClass();
                Constructor constructor;
/* 118*/        try
                {
/* 118*/            constructor = class1.getConstructor(new Class[] {
/* 118*/                java/lang/String
                    });
                }
                // Misplaced declaration of an exception variable
/* 119*/        catch(Object obj)
                {
/* 120*/            throw new IllegalArgumentException((new StringBuilder()).append(class1.getName()).append(" has no String constructor").toString(), ((Throwable) (obj)));
                }
/* 122*/        obj = obj;
/* 124*/        try
                {
/* 124*/            obj = constructor.newInstance(new Object[] {
/* 124*/                s
                    });
                }
/* 125*/        catch(Exception _ex) { }
/* 127*/        return obj;
            }

            static final long serialVersionUID = 0xac01d6a8caa8257aL;
}
