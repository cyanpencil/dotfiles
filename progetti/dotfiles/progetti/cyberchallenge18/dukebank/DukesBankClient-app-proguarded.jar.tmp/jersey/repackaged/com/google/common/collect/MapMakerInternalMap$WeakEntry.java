// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap

static class next extends WeakReference
    implements ntry
{

            public Object getKey()
            {
/*1437*/        return get();
            }

            public long getExpirationTime()
            {
/*1444*/        throw new UnsupportedOperationException();
            }

            public void setExpirationTime(long l)
            {
/*1449*/        throw new UnsupportedOperationException();
            }

            public ntry getNextExpirable()
            {
/*1454*/        throw new UnsupportedOperationException();
            }

            public void setNextExpirable(ntry ntry)
            {
/*1459*/        throw new UnsupportedOperationException();
            }

            public ntry getPreviousExpirable()
            {
/*1464*/        throw new UnsupportedOperationException();
            }

            public void setPreviousExpirable(ntry ntry)
            {
/*1469*/        throw new UnsupportedOperationException();
            }

            public ntry getNextEvictable()
            {
/*1476*/        throw new UnsupportedOperationException();
            }

            public void setNextEvictable(ntry ntry)
            {
/*1481*/        throw new UnsupportedOperationException();
            }

            public ntry getPreviousEvictable()
            {
/*1486*/        throw new UnsupportedOperationException();
            }

            public void setPreviousEvictable(ntry ntry)
            {
/*1491*/        throw new UnsupportedOperationException();
            }

            public ence getValueReference()
            {
/*1502*/        return valueReference;
            }

            public void setValueReference(ence ence)
            {
/*1507*/        ence ence1 = valueReference;
/*1508*/        valueReference = ence;
/*1509*/        ence1.clear(ence);
            }

            public int getHash()
            {
/*1514*/        return hash;
            }

            public ntry getNext()
            {
/*1519*/        return next;
            }

            final int hash;
            final ntry next;
            volatile ence valueReference;

            ntry(ReferenceQueue referencequeue, Object obj, int i, ntry ntry)
            {
/*1430*/        super(obj, referencequeue);
/*1498*/        valueReference = MapMakerInternalMap.unset();
/*1431*/        hash = i;
/*1432*/        next = ntry;
            }
}
