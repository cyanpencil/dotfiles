// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;

import java.lang.ref.ReferenceQueue;
import jersey.repackaged.com.google.common.base.Equivalence;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap

static class nit> extends nit>
{

            final nce referenceValue(try try1, try try2, Object obj)
            {
/* 325*/        return new ference(try1.ueReferenceQueue, obj, try2);
            }

            final Equivalence defaultEquivalence()
            {
/* 330*/        return Equivalence.identity();
            }

            nce(String s, int i)
            {
/* 321*/        super(s, i);
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
