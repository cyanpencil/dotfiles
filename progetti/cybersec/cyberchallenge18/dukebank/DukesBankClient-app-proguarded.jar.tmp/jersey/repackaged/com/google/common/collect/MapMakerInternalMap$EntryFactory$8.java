// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;

import java.lang.ref.ReferenceQueue;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap

static class nit> extends nit>
{

            final nit> newEntry(nit> nit>, Object obj, int i, nit> nit>1)
            {
/* 447*/        return new ictableEntry(nit>.renceQueue, obj, i, nit>1);
            }

            final renceQueue copyEntry(renceQueue rencequeue, renceQueue rencequeue1, renceQueue rencequeue2)
            {
/* 453*/        rencequeue = super.pyEntry(rencequeue, rencequeue1, rencequeue2);
/* 454*/        copyExpirableEntry(rencequeue1, rencequeue);
/* 455*/        copyEvictableEntry(rencequeue1, rencequeue);
/* 456*/        return rencequeue;
            }

            ictableEntry(String s, int i)
            {
/* 443*/        super(s, i);
            }

            // Unreferenced inner class jersey/repackaged/com/google/common/collect/MapMakerInternalMap$1

/* anonymous class */
    static class MapMakerInternalMap._cls1
        implements MapMakerInternalMap.ValueReference
    {

                public final Object get()
                {
/* 581*/            return null;
                }

                public final MapMakerInternalMap.ReferenceEntry getEntry()
                {
/* 586*/            return null;
                }

                public final MapMakerInternalMap.ValueReference copyFor(ReferenceQueue referencequeue, Object obj, MapMakerInternalMap.ReferenceEntry referenceentry)
                {
/* 592*/            return this;
                }

                public final boolean isComputingReference()
                {
/* 597*/            return false;
                }

                public final void clear(MapMakerInternalMap.ValueReference valuereference)
                {
                }

    }

}
