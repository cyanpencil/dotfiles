// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Context.java

package com.owlike.genson;

import java.util.*;

// Referenced classes of package com.owlike.genson:
//            Operations, Genson

public class Context
{

            public Context(Genson genson1)
            {
/*  41*/        this(genson1, null);
            }

            public Context(Genson genson1, List list)
            {
/*  38*/        _ctxData = new HashMap();
/*  45*/        Operations.checkNotNull(new Object[] {
/*  45*/            genson1
                });
/*  46*/        genson = genson1;
/*  47*/        views = list;
            }

            public boolean hasViews()
            {
/*  51*/        return views != null && !views.isEmpty();
            }

            public Context withView(Class class1)
            {
/*  55*/        if(views == null)
/*  55*/            views = new ArrayList();
/*  56*/        views.add(class1);
/*  57*/        return this;
            }

            public List views()
            {
/*  61*/        return views;
            }

            public Object store(String s, Object obj)
            {
/*  72*/        Operations.checkNotNull(new Object[] {
/*  72*/            s
                });
/*  73*/        Object obj1 = _ctxData.get(s);
/*  74*/        _ctxData.put(s, obj);
/*  75*/        return obj1;
            }

            public Object get(String s, Class class1)
            {
/*  88*/        Operations.checkNotNull(new Object[] {
/*  88*/            s, class1
                });
/*  89*/        return class1.cast(_ctxData.get(s));
            }

            public Object remove(String s, Class class1)
            {
/* 104*/        Operations.checkNotNull(new Object[] {
/* 104*/            s, class1
                });
/* 105*/        class1 = ((Class) (class1.cast(_ctxData.get(s))));
/* 106*/        _ctxData.remove(s);
/* 107*/        return class1;
            }

            public final Genson genson;
            private List views;
            private Map _ctxData;
}
