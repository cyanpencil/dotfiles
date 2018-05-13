// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExceptionMapperFactory.java

package org.glassfish.jersey.internal;

import java.util.Comparator;

// Referenced classes of package org.glassfish.jersey.internal:
//            ExceptionMapperFactory

class this._cls0
    implements Comparator
{

            public int compare(Class class1, Class class2)
            {
/* 189*/        return !class1.isAssignableFrom(class2) ? 1 : -1;
            }

            public volatile int compare(Object obj, Object obj1)
            {
/* 185*/        return compare((Class)obj, (Class)obj1);
            }

            final ExceptionMapperFactory this$0;

            ()
            {
/* 185*/        this$0 = ExceptionMapperFactory.this;
/* 185*/        super();
            }
}
