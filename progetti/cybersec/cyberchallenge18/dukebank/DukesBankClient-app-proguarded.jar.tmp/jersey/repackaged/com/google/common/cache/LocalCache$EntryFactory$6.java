// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.lang.ref.ReferenceQueue;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

static class nit> extends nit>
{

            final nit> newEntry(nit> nit>, Object obj, int i, nit> nit>1)
            {
/* 508*/        return new (nit>.renceQueue, obj, i, nit>1);
            }

            final renceQueue copyEntry(renceQueue rencequeue, renceQueue rencequeue1, renceQueue rencequeue2)
            {
/* 514*/        rencequeue = super.pyEntry(rencequeue, rencequeue1, rencequeue2);
/* 515*/        copyAccessEntry(rencequeue1, rencequeue);
/* 516*/        return rencequeue;
            }

            (String s, int i)
            {
/* 504*/        super(s, i);
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
