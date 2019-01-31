// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapEntry, Maps

static class tMapEntry extends AbstractMapEntry
{

            public final Object getKey()
            {
/*1237*/        return val$entry.getKey();
            }

            public final Object getValue()
            {
/*1241*/        return val$entry.getValue();
            }

            final java.util.ry val$entry;

            tMapEntry(java.util.ry ry)
            {
/*1235*/        val$entry = ry;
/*1235*/        super();
            }
}
