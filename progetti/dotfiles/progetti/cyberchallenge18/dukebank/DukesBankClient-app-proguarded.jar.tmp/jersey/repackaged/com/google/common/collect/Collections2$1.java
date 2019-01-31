// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Collections2.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Collections2

static class val.collection
    implements Function
{

            public final Object apply(Object obj)
            {
/* 302*/        if(obj == val$collection)
/* 302*/            return "(this Collection)";
/* 302*/        else
/* 302*/            return obj;
            }

            final Collection val$collection;

            (Collection collection1)
            {
/* 300*/        val$collection = collection1;
/* 300*/        super();
            }
}
