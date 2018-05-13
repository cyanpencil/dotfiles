// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.lang.ref.ReferenceQueue;
import jersey.repackaged.com.google.common.base.Equivalence;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

static class nit> extends nit>
{

            final nce referenceValue(try try1, try try2, Object obj, int i)
            {
/* 382*/        if(i == 1)
/* 382*/            return new Reference(obj);
/* 382*/        else
/* 382*/            return new ongValueReference(obj, i);
            }

            final Equivalence defaultEquivalence()
            {
/* 389*/        return Equivalence.equals();
            }

            nce(String s, int i)
            {
/* 378*/        super(s, i);
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
