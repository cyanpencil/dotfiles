// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap

static final class entry extends WeakReference
    implements entry
{

            public final entry getEntry()
            {
/*1697*/        return entry;
            }

            public final void clear(entry entry1)
            {
/*1702*/        clear();
            }

            public final clear copyFor(ReferenceQueue referencequeue, Object obj, clear clear1)
            {
/*1708*/        return new <init>(referencequeue, obj, clear1);
            }

            public final boolean isComputingReference()
            {
/*1713*/        return false;
            }

            final <init> entry;

            (ReferenceQueue referencequeue, Object obj,  )
            {
/*1691*/        super(obj, referencequeue);
/*1692*/        entry = ;
            }
}
