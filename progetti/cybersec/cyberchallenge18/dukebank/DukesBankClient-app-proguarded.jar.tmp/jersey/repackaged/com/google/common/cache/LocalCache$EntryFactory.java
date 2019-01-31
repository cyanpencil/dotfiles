// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.lang.ref.ReferenceQueue;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

static abstract class _cls1 extends Enum
{

            static _cls1 getFactory(_cls1 _pcls1, boolean flag, boolean flag1)
            {
/* 568*/        _pcls1 = (_pcls1 !=  ? 0 : 4) | (flag ? 1 : 0) | (flag1 ? 2 : 0);
/* 571*/        return factories[_pcls1];
            }

            abstract y newEntry(y y, Object obj, int i, y y1);

            y copyEntry(y y, y y1, y y2)
            {
/* 594*/        return newEntry(y, y1.getKey(), y1.getHash(), y2);
            }

            void copyAccessEntry(y y, y y1)
            {
/* 601*/        y1.setAccessTime(y.getAccessTime());
/* 603*/        LocalCache.connectAccessOrder(y.getPreviousInAccessQueue(), y1);
/* 604*/        LocalCache.connectAccessOrder(y1, y.getNextInAccessQueue());
/* 606*/        LocalCache.nullifyAccessOrder(y);
            }

            void copyWriteEntry(y y, y y1)
            {
/* 613*/        y1.setWriteTime(y.getWriteTime());
/* 615*/        LocalCache.connectWriteOrder(y.getPreviousInWriteQueue(), y1);
/* 616*/        LocalCache.connectWriteOrder(y1, y.getNextInWriteQueue());
/* 618*/        LocalCache.nullifyWriteOrder(y);
            }

            public static final WEAK_ACCESS_WRITE STRONG;
            public static final WEAK_ACCESS_WRITE STRONG_ACCESS;
            public static final WEAK_ACCESS_WRITE STRONG_WRITE;
            public static final WEAK_ACCESS_WRITE STRONG_ACCESS_WRITE;
            public static final WEAK_ACCESS_WRITE WEAK;
            public static final WEAK_ACCESS_WRITE WEAK_ACCESS;
            public static final WEAK_ACCESS_WRITE WEAK_WRITE;
            public static final WEAK_ACCESS_WRITE WEAK_ACCESS_WRITE;
            static final WEAK_ACCESS_WRITE factories[];
            private static final WEAK_ACCESS_WRITE $VALUES[];

            static 
            {
/* 443*/        STRONG = new LocalCache.EntryFactory("STRONG", 0) {

                    final LocalCache.ReferenceEntry newEntry(LocalCache.Segment segment, Object obj, int i, LocalCache.ReferenceEntry referenceentry)
                    {
/* 447*/                return new LocalCache.StrongEntry(obj, i, referenceentry);
                    }

        };
/* 450*/        STRONG_ACCESS = new LocalCache.EntryFactory("STRONG_ACCESS", 1) {

                    final LocalCache.ReferenceEntry newEntry(LocalCache.Segment segment, Object obj, int i, LocalCache.ReferenceEntry referenceentry)
                    {
/* 454*/                return new LocalCache.StrongAccessEntry(obj, i, referenceentry);
                    }

                    final LocalCache.ReferenceEntry copyEntry(LocalCache.Segment segment, LocalCache.ReferenceEntry referenceentry, LocalCache.ReferenceEntry referenceentry1)
                    {
/* 460*/                segment = copyEntry(segment, referenceentry, referenceentry1);
/* 461*/                copyAccessEntry(referenceentry, segment);
/* 462*/                return segment;
                    }

        };
/* 465*/        STRONG_WRITE = new LocalCache.EntryFactory("STRONG_WRITE", 2) {

                    final LocalCache.ReferenceEntry newEntry(LocalCache.Segment segment, Object obj, int i, LocalCache.ReferenceEntry referenceentry)
                    {
/* 469*/                return new LocalCache.StrongWriteEntry(obj, i, referenceentry);
                    }

                    final LocalCache.ReferenceEntry copyEntry(LocalCache.Segment segment, LocalCache.ReferenceEntry referenceentry, LocalCache.ReferenceEntry referenceentry1)
                    {
/* 475*/                segment = copyEntry(segment, referenceentry, referenceentry1);
/* 476*/                copyWriteEntry(referenceentry, segment);
/* 477*/                return segment;
                    }

        };
/* 480*/        STRONG_ACCESS_WRITE = new LocalCache.EntryFactory("STRONG_ACCESS_WRITE", 3) {

                    final LocalCache.ReferenceEntry newEntry(LocalCache.Segment segment, Object obj, int i, LocalCache.ReferenceEntry referenceentry)
                    {
/* 484*/                return new LocalCache.StrongAccessWriteEntry(obj, i, referenceentry);
                    }

                    final LocalCache.ReferenceEntry copyEntry(LocalCache.Segment segment, LocalCache.ReferenceEntry referenceentry, LocalCache.ReferenceEntry referenceentry1)
                    {
/* 490*/                segment = copyEntry(segment, referenceentry, referenceentry1);
/* 491*/                copyAccessEntry(referenceentry, segment);
/* 492*/                copyWriteEntry(referenceentry, segment);
/* 493*/                return segment;
                    }

        };
/* 497*/        WEAK = new LocalCache.EntryFactory("WEAK", 4) {

                    final LocalCache.ReferenceEntry newEntry(LocalCache.Segment segment, Object obj, int i, LocalCache.ReferenceEntry referenceentry)
                    {
/* 501*/                return new LocalCache.WeakEntry(segment.keyReferenceQueue, obj, i, referenceentry);
                    }

        };
/* 504*/        WEAK_ACCESS = new LocalCache.EntryFactory("WEAK_ACCESS", 5) {

                    final LocalCache.ReferenceEntry newEntry(LocalCache.Segment segment, Object obj, int i, LocalCache.ReferenceEntry referenceentry)
                    {
/* 508*/                return new LocalCache.WeakAccessEntry(segment.keyReferenceQueue, obj, i, referenceentry);
                    }

                    final LocalCache.ReferenceEntry copyEntry(LocalCache.Segment segment, LocalCache.ReferenceEntry referenceentry, LocalCache.ReferenceEntry referenceentry1)
                    {
/* 514*/                segment = copyEntry(segment, referenceentry, referenceentry1);
/* 515*/                copyAccessEntry(referenceentry, segment);
/* 516*/                return segment;
                    }

        };
/* 519*/        WEAK_WRITE = new LocalCache.EntryFactory("WEAK_WRITE", 6) {

                    final LocalCache.ReferenceEntry newEntry(LocalCache.Segment segment, Object obj, int i, LocalCache.ReferenceEntry referenceentry)
                    {
/* 523*/                return new LocalCache.WeakWriteEntry(segment.keyReferenceQueue, obj, i, referenceentry);
                    }

                    final LocalCache.ReferenceEntry copyEntry(LocalCache.Segment segment, LocalCache.ReferenceEntry referenceentry, LocalCache.ReferenceEntry referenceentry1)
                    {
/* 529*/                segment = copyEntry(segment, referenceentry, referenceentry1);
/* 530*/                copyWriteEntry(referenceentry, segment);
/* 531*/                return segment;
                    }

        };
/* 534*/        WEAK_ACCESS_WRITE = new LocalCache.EntryFactory("WEAK_ACCESS_WRITE", 7) {

                    final LocalCache.ReferenceEntry newEntry(LocalCache.Segment segment, Object obj, int i, LocalCache.ReferenceEntry referenceentry)
                    {
/* 538*/                return new LocalCache.WeakAccessWriteEntry(segment.keyReferenceQueue, obj, i, referenceentry);
                    }

                    final LocalCache.ReferenceEntry copyEntry(LocalCache.Segment segment, LocalCache.ReferenceEntry referenceentry, LocalCache.ReferenceEntry referenceentry1)
                    {
/* 544*/                segment = copyEntry(segment, referenceentry, referenceentry1);
/* 545*/                copyAccessEntry(referenceentry, segment);
/* 546*/                copyWriteEntry(referenceentry, segment);
/* 547*/                return segment;
                    }

        };
/* 442*/        $VALUES = (new .VALUES[] {
/* 442*/            STRONG, STRONG_ACCESS, STRONG_WRITE, STRONG_ACCESS_WRITE, WEAK, WEAK_ACCESS, WEAK_WRITE, WEAK_ACCESS_WRITE
                });
/* 561*/        factories = (new factories[] {
/* 561*/            STRONG, STRONG_ACCESS, STRONG_WRITE, STRONG_ACCESS_WRITE, WEAK, WEAK_ACCESS, WEAK_WRITE, WEAK_ACCESS_WRITE
                });
            }

            private _cls1(String s, int i)
            {
/* 442*/        super(s, i);
            }


            // Unreferenced inner class jersey/repackaged/com/google/common/cache/LocalCache$1

/* anonymous class */
    static class LocalCache._cls1
        implements LocalCache.ValueReference
    {

                public final Object get()
                {
/* 690*/            return null;
                }

                public final int getWeight()
                {
/* 695*/            return 0;
                }

                public final LocalCache.ReferenceEntry getEntry()
                {
/* 700*/            return null;
                }

                public final LocalCache.ValueReference copyFor(ReferenceQueue referencequeue, Object obj, LocalCache.ReferenceEntry referenceentry)
                {
/* 706*/            return this;
                }

                public final boolean isLoading()
                {
/* 711*/            return false;
                }

                public final boolean isActive()
                {
/* 716*/            return false;
                }

                public final Object waitForValue()
                {
/* 721*/            return null;
                }

                public final void notifyNewValue(Object obj)
                {
                }

    }

}
