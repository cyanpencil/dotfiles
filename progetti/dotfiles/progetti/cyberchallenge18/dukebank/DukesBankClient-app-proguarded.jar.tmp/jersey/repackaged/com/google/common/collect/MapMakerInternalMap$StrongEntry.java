// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap

static class next
    implements ry
{

            public Object getKey()
            {
/* 925*/        return key;
            }

            public long getExpirationTime()
            {
/* 932*/        throw new UnsupportedOperationException();
            }

            public void setExpirationTime(long l)
            {
/* 937*/        throw new UnsupportedOperationException();
            }

            public ry getNextExpirable()
            {
/* 942*/        throw new UnsupportedOperationException();
            }

            public void setNextExpirable(ry ry)
            {
/* 947*/        throw new UnsupportedOperationException();
            }

            public ry getPreviousExpirable()
            {
/* 952*/        throw new UnsupportedOperationException();
            }

            public void setPreviousExpirable(ry ry)
            {
/* 957*/        throw new UnsupportedOperationException();
            }

            public ry getNextEvictable()
            {
/* 964*/        throw new UnsupportedOperationException();
            }

            public void setNextEvictable(ry ry)
            {
/* 969*/        throw new UnsupportedOperationException();
            }

            public ry getPreviousEvictable()
            {
/* 974*/        throw new UnsupportedOperationException();
            }

            public void setPreviousEvictable(ry ry)
            {
/* 979*/        throw new UnsupportedOperationException();
            }

            public ce getValueReference()
            {
/* 990*/        return valueReference;
            }

            public void setValueReference(ce ce)
            {
/* 995*/        ce ce1 = valueReference;
/* 996*/        valueReference = ce;
/* 997*/        ce1.clear(ce);
            }

            public int getHash()
            {
/*1002*/        return hash;
            }

            public ry getNext()
            {
/*1007*/        return next;
            }

            final Object key;
            final int hash;
            final ry next;
            volatile ce valueReference;

            ry(Object obj, int i, ry ry)
            {
/* 986*/        valueReference = MapMakerInternalMap.unset();
/* 918*/        key = obj;
/* 919*/        hash = i;
/* 920*/        next = ry;
            }
}
