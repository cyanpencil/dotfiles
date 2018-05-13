// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ThreadLocalHolder.java

package com.owlike.genson;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.owlike.genson:
//            Operations

public final class ThreadLocalHolder
{

            public ThreadLocalHolder()
            {
            }

            public static Object store(String s, Object obj)
            {
/*  23*/        Operations.checkNotNull(new Object[] {
/*  23*/            s
                });
/*  24*/        return getPutIfMissing().put(s, obj);
            }

            public static Object remove(String s, Class class1)
            {
/*  28*/        Operations.checkNotNull(new Object[] {
/*  28*/            s, class1
                });
/*  29*/        Map map = getPutIfMissing();
/*  30*/        class1 = ((Class) (class1.cast(map.get(s))));
/*  31*/        map.remove(s);
/*  32*/        return class1;
            }

            public static Object get(String s, Class class1)
            {
/*  36*/        Operations.checkNotNull(new Object[] {
/*  36*/            s, class1
                });
/*  37*/        return class1.cast(getPutIfMissing().get(s));
            }

            private static Map getPutIfMissing()
            {
                Object obj;
/*  41*/        if((obj = (Map)_data.get()) == null)
                {
/*  43*/            obj = new HashMap();
/*  44*/            _data.set(obj);
                }
/*  46*/        return ((Map) (obj));
            }

            private static final ThreadLocal _data = new ThreadLocal();

}
