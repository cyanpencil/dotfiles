// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.lang.ref.ReferenceQueue;
import jersey.repackaged.com.google.common.base.Equivalence;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

static abstract class _cls1 extends Enum
{

            public static _cls1[] values()
            {
/* 372*/        return (_cls1[])$VALUES.clone();
            }

            abstract rence referenceValue(Entry entry, Entry entry1, Object obj, int i);

            abstract Equivalence defaultEquivalence();

            public static final WEAK STRONG;
            public static final WEAK SOFT;
            public static final WEAK WEAK;
            private static final WEAK $VALUES[];

            static 
            {
/* 378*/        STRONG = new LocalCache.Strength("STRONG", 0) {

                    final LocalCache.ValueReference referenceValue(LocalCache.Segment segment, LocalCache.ReferenceEntry referenceentry, Object obj, int i)
                    {
/* 382*/                if(i == 1)
/* 382*/                    return new LocalCache.StrongValueReference(obj);
/* 382*/                else
/* 382*/                    return new LocalCache.WeightedStrongValueReference(obj, i);
                    }

                    final Equivalence defaultEquivalence()
                    {
/* 389*/                return Equivalence.equals();
                    }

        };
/* 393*/        SOFT = new LocalCache.Strength("SOFT", 1) {

                    final LocalCache.ValueReference referenceValue(LocalCache.Segment segment, LocalCache.ReferenceEntry referenceentry, Object obj, int i)
                    {
/* 397*/                if(i == 1)
/* 397*/                    return new LocalCache.SoftValueReference(segment.valueReferenceQueue, obj, referenceentry);
/* 397*/                else
/* 397*/                    return new LocalCache.WeightedSoftValueReference(segment.valueReferenceQueue, obj, referenceentry, i);
                    }

                    final Equivalence defaultEquivalence()
                    {
/* 405*/                return Equivalence.identity();
                    }

        };
/* 409*/        WEAK = new LocalCache.Strength("WEAK", 2) {

                    final LocalCache.ValueReference referenceValue(LocalCache.Segment segment, LocalCache.ReferenceEntry referenceentry, Object obj, int i)
                    {
/* 413*/                if(i == 1)
/* 413*/                    return new LocalCache.WeakValueReference(segment.valueReferenceQueue, obj, referenceentry);
/* 413*/                else
/* 413*/                    return new LocalCache.WeightedWeakValueReference(segment.valueReferenceQueue, obj, referenceentry, i);
                    }

                    final Equivalence defaultEquivalence()
                    {
/* 421*/                return Equivalence.identity();
                    }

        };
/* 372*/        $VALUES = (new .VALUES[] {
/* 372*/            STRONG, SOFT, WEAK
                });
            }

            private _cls1(String s, int i)
            {
/* 372*/        super(s, i);
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
