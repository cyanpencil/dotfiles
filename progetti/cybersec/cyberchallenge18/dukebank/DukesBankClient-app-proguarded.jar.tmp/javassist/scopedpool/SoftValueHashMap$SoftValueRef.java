// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SoftValueHashMap.java

package javassist.scopedpool;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

// Referenced classes of package javassist.scopedpool:
//            SoftValueHashMap

static class key extends SoftReference
{

            private static key create(Object obj, Object obj1, ReferenceQueue referencequeue)
            {
/*  44*/        if(obj1 == null)
/*  45*/            return null;
/*  47*/        else
/*  47*/            return new <init>(obj, obj1, referencequeue);
            }

            public Object key;


            private (Object obj, Object obj1, ReferenceQueue referencequeue)
            {
/*  38*/        super(obj1, referencequeue);
/*  39*/        key = obj;
            }
}
