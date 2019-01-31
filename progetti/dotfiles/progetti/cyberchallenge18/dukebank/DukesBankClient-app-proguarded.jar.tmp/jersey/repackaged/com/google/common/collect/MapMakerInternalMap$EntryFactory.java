// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;

import java.lang.ref.ReferenceQueue;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap

static abstract class _cls1 extends Enum
{

            static _cls1 getFactory(_cls1 _pcls1, boolean flag, boolean flag1)
            {
/* 478*/        flag = (flag ? 1 : 0) | (flag1 ? 2 : 0);
/* 479*/        return factories[_pcls1.nal()][flag];
            }

            abstract y newEntry(y y, Object obj, int i, y y1);

            y copyEntry(y y, y y1, y y2)
            {
/* 502*/        return newEntry(y, y1.getKey(), y1.getHash(), y2);
            }

            void copyExpirableEntry(y y, y y1)
            {
/* 509*/        y1.setExpirationTime(y.getExpirationTime());
/* 511*/        MapMakerInternalMap.connectExpirables(y.getPreviousExpirable(), y1);
/* 512*/        MapMakerInternalMap.connectExpirables(y1, y.getNextExpirable());
/* 514*/        MapMakerInternalMap.nullifyExpirable(y);
            }

            void copyEvictableEntry(y y, y y1)
            {
/* 521*/        MapMakerInternalMap.connectEvictables(y.getPreviousEvictable(), y1);
/* 522*/        MapMakerInternalMap.connectEvictables(y1, y.getNextEvictable());
/* 524*/        MapMakerInternalMap.nullifyEvictable(y);
            }

            public static final WEAK_EXPIRABLE_EVICTABLE STRONG;
            public static final WEAK_EXPIRABLE_EVICTABLE STRONG_EXPIRABLE;
            public static final WEAK_EXPIRABLE_EVICTABLE STRONG_EVICTABLE;
            public static final WEAK_EXPIRABLE_EVICTABLE STRONG_EXPIRABLE_EVICTABLE;
            public static final WEAK_EXPIRABLE_EVICTABLE WEAK;
            public static final WEAK_EXPIRABLE_EVICTABLE WEAK_EXPIRABLE;
            public static final WEAK_EXPIRABLE_EVICTABLE WEAK_EVICTABLE;
            public static final WEAK_EXPIRABLE_EVICTABLE WEAK_EXPIRABLE_EVICTABLE;
            static final WEAK_EXPIRABLE_EVICTABLE factories[][];
            private static final WEAK_EXPIRABLE_EVICTABLE $VALUES[];

            static 
            {
/* 352*/        STRONG = new MapMakerInternalMap.EntryFactory("STRONG", 0) {

                    final MapMakerInternalMap.ReferenceEntry newEntry(MapMakerInternalMap.Segment segment, Object obj, int i, MapMakerInternalMap.ReferenceEntry referenceentry)
                    {
/* 356*/                return new MapMakerInternalMap.StrongEntry(obj, i, referenceentry);
                    }

        };
/* 359*/        STRONG_EXPIRABLE = new MapMakerInternalMap.EntryFactory("STRONG_EXPIRABLE", 1) {

                    final MapMakerInternalMap.ReferenceEntry newEntry(MapMakerInternalMap.Segment segment, Object obj, int i, MapMakerInternalMap.ReferenceEntry referenceentry)
                    {
/* 363*/                return new MapMakerInternalMap.StrongExpirableEntry(obj, i, referenceentry);
                    }

                    final MapMakerInternalMap.ReferenceEntry copyEntry(MapMakerInternalMap.Segment segment, MapMakerInternalMap.ReferenceEntry referenceentry, MapMakerInternalMap.ReferenceEntry referenceentry1)
                    {
/* 369*/                segment = copyEntry(segment, referenceentry, referenceentry1);
/* 370*/                copyExpirableEntry(referenceentry, segment);
/* 371*/                return segment;
                    }

        };
/* 374*/        STRONG_EVICTABLE = new MapMakerInternalMap.EntryFactory("STRONG_EVICTABLE", 2) {

                    final MapMakerInternalMap.ReferenceEntry newEntry(MapMakerInternalMap.Segment segment, Object obj, int i, MapMakerInternalMap.ReferenceEntry referenceentry)
                    {
/* 378*/                return new MapMakerInternalMap.StrongEvictableEntry(obj, i, referenceentry);
                    }

                    final MapMakerInternalMap.ReferenceEntry copyEntry(MapMakerInternalMap.Segment segment, MapMakerInternalMap.ReferenceEntry referenceentry, MapMakerInternalMap.ReferenceEntry referenceentry1)
                    {
/* 384*/                segment = copyEntry(segment, referenceentry, referenceentry1);
/* 385*/                copyEvictableEntry(referenceentry, segment);
/* 386*/                return segment;
                    }

        };
/* 389*/        STRONG_EXPIRABLE_EVICTABLE = new MapMakerInternalMap.EntryFactory("STRONG_EXPIRABLE_EVICTABLE", 3) {

                    final MapMakerInternalMap.ReferenceEntry newEntry(MapMakerInternalMap.Segment segment, Object obj, int i, MapMakerInternalMap.ReferenceEntry referenceentry)
                    {
/* 393*/                return new MapMakerInternalMap.StrongExpirableEvictableEntry(obj, i, referenceentry);
                    }

                    final MapMakerInternalMap.ReferenceEntry copyEntry(MapMakerInternalMap.Segment segment, MapMakerInternalMap.ReferenceEntry referenceentry, MapMakerInternalMap.ReferenceEntry referenceentry1)
                    {
/* 399*/                segment = copyEntry(segment, referenceentry, referenceentry1);
/* 400*/                copyExpirableEntry(referenceentry, segment);
/* 401*/                copyEvictableEntry(referenceentry, segment);
/* 402*/                return segment;
                    }

        };
/* 406*/        WEAK = new MapMakerInternalMap.EntryFactory("WEAK", 4) {

                    final MapMakerInternalMap.ReferenceEntry newEntry(MapMakerInternalMap.Segment segment, Object obj, int i, MapMakerInternalMap.ReferenceEntry referenceentry)
                    {
/* 410*/                return new MapMakerInternalMap.WeakEntry(segment.keyReferenceQueue, obj, i, referenceentry);
                    }

        };
/* 413*/        WEAK_EXPIRABLE = new MapMakerInternalMap.EntryFactory("WEAK_EXPIRABLE", 5) {

                    final MapMakerInternalMap.ReferenceEntry newEntry(MapMakerInternalMap.Segment segment, Object obj, int i, MapMakerInternalMap.ReferenceEntry referenceentry)
                    {
/* 417*/                return new MapMakerInternalMap.WeakExpirableEntry(segment.keyReferenceQueue, obj, i, referenceentry);
                    }

                    final MapMakerInternalMap.ReferenceEntry copyEntry(MapMakerInternalMap.Segment segment, MapMakerInternalMap.ReferenceEntry referenceentry, MapMakerInternalMap.ReferenceEntry referenceentry1)
                    {
/* 423*/                segment = copyEntry(segment, referenceentry, referenceentry1);
/* 424*/                copyExpirableEntry(referenceentry, segment);
/* 425*/                return segment;
                    }

        };
/* 428*/        WEAK_EVICTABLE = new MapMakerInternalMap.EntryFactory("WEAK_EVICTABLE", 6) {

                    final MapMakerInternalMap.ReferenceEntry newEntry(MapMakerInternalMap.Segment segment, Object obj, int i, MapMakerInternalMap.ReferenceEntry referenceentry)
                    {
/* 432*/                return new MapMakerInternalMap.WeakEvictableEntry(segment.keyReferenceQueue, obj, i, referenceentry);
                    }

                    final MapMakerInternalMap.ReferenceEntry copyEntry(MapMakerInternalMap.Segment segment, MapMakerInternalMap.ReferenceEntry referenceentry, MapMakerInternalMap.ReferenceEntry referenceentry1)
                    {
/* 438*/                segment = copyEntry(segment, referenceentry, referenceentry1);
/* 439*/                copyEvictableEntry(referenceentry, segment);
/* 440*/                return segment;
                    }

        };
/* 443*/        WEAK_EXPIRABLE_EVICTABLE = new MapMakerInternalMap.EntryFactory("WEAK_EXPIRABLE_EVICTABLE", 7) {

                    final MapMakerInternalMap.ReferenceEntry newEntry(MapMakerInternalMap.Segment segment, Object obj, int i, MapMakerInternalMap.ReferenceEntry referenceentry)
                    {
/* 447*/                return new MapMakerInternalMap.WeakExpirableEvictableEntry(segment.keyReferenceQueue, obj, i, referenceentry);
                    }

                    final MapMakerInternalMap.ReferenceEntry copyEntry(MapMakerInternalMap.Segment segment, MapMakerInternalMap.ReferenceEntry referenceentry, MapMakerInternalMap.ReferenceEntry referenceentry1)
                    {
/* 453*/                segment = copyEntry(segment, referenceentry, referenceentry1);
/* 454*/                copyExpirableEntry(referenceentry, segment);
/* 455*/                copyEvictableEntry(referenceentry, segment);
/* 456*/                return segment;
                    }

        };
/* 351*/        $VALUES = (new .VALUES[] {
/* 351*/            STRONG, STRONG_EXPIRABLE, STRONG_EVICTABLE, STRONG_EXPIRABLE_EVICTABLE, WEAK, WEAK_EXPIRABLE, WEAK_EVICTABLE, WEAK_EXPIRABLE_EVICTABLE
                });
/* 470*/        factories = (new factories[][] {
/* 470*/            new factories[] {
/* 470*/                STRONG, STRONG_EXPIRABLE, STRONG_EVICTABLE, STRONG_EXPIRABLE_EVICTABLE
                    }, new STRONG_EXPIRABLE_EVICTABLE[0], new STRONG_EXPIRABLE_EVICTABLE[] {
/* 470*/                WEAK, WEAK_EXPIRABLE, WEAK_EVICTABLE, WEAK_EXPIRABLE_EVICTABLE
                    }
                });
            }

            private _cls1(String s, int i)
            {
/* 351*/        super(s, i);
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
