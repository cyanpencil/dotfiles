// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap

static final class entry extends SoftReference
    implements entry
{

            public final entry getEntry()
            {
/*1736*/        return entry;
            }

            public final void clear(entry entry1)
            {
/*1741*/        clear();
            }

            public final clear copyFor(ReferenceQueue referencequeue, Object obj, clear clear1)
            {
/*1747*/        return new <init>(referencequeue, obj, clear1);
            }

            public final boolean isComputingReference()
            {
/*1752*/        return false;
            }

            final <init> entry;

            (ReferenceQueue referencequeue, Object obj,  )
            {
/*1730*/        super(obj, referencequeue);
/*1731*/        entry = ;
            }
}
