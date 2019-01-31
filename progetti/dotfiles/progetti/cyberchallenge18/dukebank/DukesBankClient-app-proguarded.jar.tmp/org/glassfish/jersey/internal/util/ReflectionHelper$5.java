// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReflectionHelper.java

package org.glassfish.jersey.internal.util;

import java.lang.reflect.Field;
import java.security.PrivilegedAction;
import java.util.*;

// Referenced classes of package org.glassfish.jersey.internal.util:
//            ReflectionHelper

static class val.clazz
    implements PrivilegedAction
{

            public final Field[] run()
            {
/* 329*/        ArrayList arraylist = new ArrayList();
/* 330*/        recurse(val$clazz, arraylist);
/* 331*/        return (Field[])arraylist.toArray(new Field[arraylist.size()]);
            }

            private void recurse(Class class1, List list)
            {
/* 335*/        do
                {
/* 335*/            list.addAll(Arrays.asList(class1.getDeclaredFields()));
/* 336*/            if(class1.getSuperclass() != null)
                    {
/* 337*/                class1 = class1.getSuperclass();
/* 337*/                this = this;
                    } else
                    {
/* 339*/                return;
                    }
                } while(true);
            }

            public final volatile Object run()
            {
/* 326*/        return run();
            }

            final Class val$clazz;

            (Class class1)
            {
/* 326*/        val$clazz = class1;
/* 326*/        super();
            }
}
