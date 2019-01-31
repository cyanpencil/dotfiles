// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;


// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

static class next extends renceEntry
{

            public Object getKey()
            {
/*1075*/        return key;
            }

            public ce getValueReference()
            {
/*1086*/        return valueReference;
            }

            public void setValueReference(ce ce)
            {
/*1091*/        valueReference = ce;
            }

            public int getHash()
            {
/*1096*/        return hash;
            }

            public ry getNext()
            {
/*1101*/        return next;
            }

            final Object key;
            final int hash;
            final ry next;
            volatile ce valueReference;

            ry(Object obj, int i, ry ry)
            {
/*1082*/        valueReference = LocalCache.unset();
/*1068*/        key = obj;
/*1069*/        hash = i;
/*1070*/        next = ry;
            }
}
