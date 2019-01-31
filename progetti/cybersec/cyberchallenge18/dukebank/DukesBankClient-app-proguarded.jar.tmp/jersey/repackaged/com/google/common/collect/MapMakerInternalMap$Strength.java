// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;

import java.lang.ref.ReferenceQueue;
import jersey.repackaged.com.google.common.base.Equivalence;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap

static abstract class _cls1 extends Enum
{

            abstract rence referenceValue(Entry entry, Entry entry1, Object obj);

            abstract Equivalence defaultEquivalence();

            public static final WEAK STRONG;
            public static final WEAK SOFT;
            public static final WEAK WEAK;
            private static final WEAK $VALUES[];

            static 
            {
/* 295*/        STRONG = new MapMakerInternalMap.Strength("STRONG", 0) {

                    final MapMakerInternalMap.ValueReference referenceValue(MapMakerInternalMap.Segment segment, MapMakerInternalMap.ReferenceEntry referenceentry, Object obj)
                    {
/* 299*/                return new MapMakerInternalMap.StrongValueReference(obj);
                    }

                    final Equivalence defaultEquivalence()
                    {
/* 304*/                return Equivalence.equals();
                    }

        };
/* 308*/        SOFT = new MapMakerInternalMap.Strength("SOFT", 1) {

                    final MapMakerInternalMap.ValueReference referenceValue(MapMakerInternalMap.Segment segment, MapMakerInternalMap.ReferenceEntry referenceentry, Object obj)
                    {
/* 312*/                return new MapMakerInternalMap.SoftValueReference(segment.valueReferenceQueue, obj, referenceentry);
                    }

                    final Equivalence defaultEquivalence()
                    {
/* 317*/                return Equivalence.identity();
                    }

        };
/* 321*/        WEAK = new MapMakerInternalMap.Strength("WEAK", 2) {

                    final MapMakerInternalMap.ValueReference referenceValue(MapMakerInternalMap.Segment segment, MapMakerInternalMap.ReferenceEntry referenceentry, Object obj)
                    {
/* 325*/                return new MapMakerInternalMap.WeakValueReference(segment.valueReferenceQueue, obj, referenceentry);
                    }

                    final Equivalence defaultEquivalence()
                    {
/* 330*/                return Equivalence.identity();
                    }

        };
/* 289*/        $VALUES = (new .VALUES[] {
/* 289*/            STRONG, SOFT, WEAK
                });
            }

            private _cls1(String s, int i)
            {
/* 289*/        super(s, i);
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
