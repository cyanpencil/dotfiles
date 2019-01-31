// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapEntry, Maps

static class MapEntry extends AbstractMapEntry
{

            public final Object getKey()
            {
/*1852*/        return val$entry.getKey();
            }

            public final Object getValue()
            {
/*1857*/        return val$transformer.transformEntry(val$entry.getKey(), val$entry.getValue());
            }

            final java.util.y val$entry;
            final ryTransformer val$transformer;

            ryTransformer(java.util.y y, ryTransformer rytransformer)
            {
/*1849*/        val$entry = y;
/*1849*/        val$transformer = rytransformer;
/*1849*/        super();
            }
}
