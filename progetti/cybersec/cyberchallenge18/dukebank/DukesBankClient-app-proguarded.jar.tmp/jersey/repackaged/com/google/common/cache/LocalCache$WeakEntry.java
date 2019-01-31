// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

static class next extends WeakReference
    implements ntry
{

            public Object getKey()
            {
/*1295*/        return get();
            }

            public long getAccessTime()
            {
/*1307*/        throw new UnsupportedOperationException();
            }

            public void setAccessTime(long l)
            {
/*1312*/        throw new UnsupportedOperationException();
            }

            public ntry getNextInAccessQueue()
            {
/*1317*/        throw new UnsupportedOperationException();
            }

            public void setNextInAccessQueue(ntry ntry)
            {
/*1322*/        throw new UnsupportedOperationException();
            }

            public ntry getPreviousInAccessQueue()
            {
/*1327*/        throw new UnsupportedOperationException();
            }

            public void setPreviousInAccessQueue(ntry ntry)
            {
/*1332*/        throw new UnsupportedOperationException();
            }

            public long getWriteTime()
            {
/*1339*/        throw new UnsupportedOperationException();
            }

            public void setWriteTime(long l)
            {
/*1344*/        throw new UnsupportedOperationException();
            }

            public ntry getNextInWriteQueue()
            {
/*1349*/        throw new UnsupportedOperationException();
            }

            public void setNextInWriteQueue(ntry ntry)
            {
/*1354*/        throw new UnsupportedOperationException();
            }

            public ntry getPreviousInWriteQueue()
            {
/*1359*/        throw new UnsupportedOperationException();
            }

            public void setPreviousInWriteQueue(ntry ntry)
            {
/*1364*/        throw new UnsupportedOperationException();
            }

            public ence getValueReference()
            {
/*1375*/        return valueReference;
            }

            public void setValueReference(ence ence)
            {
/*1380*/        valueReference = ence;
            }

            public int getHash()
            {
/*1385*/        return hash;
            }

            public ntry getNext()
            {
/*1390*/        return next;
            }

            final int hash;
            final ntry next;
            volatile ence valueReference;

            ntry(ReferenceQueue referencequeue, Object obj, int i, ntry ntry)
            {
/*1288*/        super(obj, referencequeue);
/*1371*/        valueReference = LocalCache.unset();
/*1289*/        hash = i;
/*1290*/        next = ntry;
            }
}
