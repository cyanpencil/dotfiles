// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Tables.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collections;
import java.util.Map;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Tables

static class 
    implements Function
{

            public final Map apply(Map map)
            {
/* 595*/        return Collections.unmodifiableMap(map);
            }

            public final volatile Object apply(Object obj)
            {
/* 592*/        return apply((Map)obj);
            }

            ()
            {
            }
}
