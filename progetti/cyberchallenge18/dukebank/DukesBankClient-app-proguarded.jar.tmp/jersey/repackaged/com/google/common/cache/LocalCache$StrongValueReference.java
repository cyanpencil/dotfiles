// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.lang.ref.ReferenceQueue;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

static class referent
    implements referent
{

            public Object get()
            {
/*1681*/        return referent;
            }

            public int getWeight()
            {
/*1686*/        return 1;
            }

            public referent getEntry()
            {
/*1691*/        return null;
            }

            public referent copyFor(ReferenceQueue referencequeue, Object obj, referent referent1)
            {
/*1697*/        return this;
            }

            public boolean isLoading()
            {
/*1702*/        return false;
            }

            public boolean isActive()
            {
/*1707*/        return true;
            }

            public Object waitForValue()
            {
/*1712*/        return get();
            }

            public void notifyNewValue(Object obj)
            {
            }

            final Object referent;

            (Object obj)
            {
/*1676*/        referent = obj;
            }
}
