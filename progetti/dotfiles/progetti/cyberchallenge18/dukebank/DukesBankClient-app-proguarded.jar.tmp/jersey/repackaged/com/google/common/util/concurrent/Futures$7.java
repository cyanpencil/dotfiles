// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Futures.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            Futures

static class 
    implements Function
{

            public final Boolean apply(Constructor constructor)
            {
/*1573*/        return Boolean.valueOf(Arrays.asList(constructor.getParameterTypes()).contains(java/lang/String));
            }

            public final volatile Object apply(Object obj)
            {
/*1571*/        return apply((Constructor)obj);
            }

            ()
            {
            }
}
