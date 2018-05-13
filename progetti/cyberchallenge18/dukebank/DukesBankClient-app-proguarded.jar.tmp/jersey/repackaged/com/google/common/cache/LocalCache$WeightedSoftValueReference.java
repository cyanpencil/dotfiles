// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.lang.ref.ReferenceQueue;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

static final class weight extends weight
{

            public final int getWeight()
            {
/*1757*/        return weight;
            }

            public final weight copyFor(ReferenceQueue referencequeue, Object obj, weight weight1)
            {
/*1762*/        return new <init>(referencequeue, obj, weight1, weight);
            }

            final int weight;

            (ReferenceQueue referencequeue, Object obj,  , int i)
            {
/*1751*/        super(referencequeue, obj, );
/*1752*/        weight = i;
            }
}
