// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConcurrentHashMapV8.java

package org.glassfish.jersey.internal.util.collection;


// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            ConcurrentHashMapV8

static final class nextTable extends nextTable
{

            final nextTable find(int i, Object obj)
            {
/*1719*/        Object obj1 = nextTable;
/*1722*/label0:
/*1722*/        do
                {
                    int j;
/*1722*/            if(obj == null || obj1 == null || (j = obj1.length) == 0 || (obj1 = ConcurrentHashMapV8.tabAt(((nextTable []) (obj1)), j - 1 & i)) == null)
/*1724*/                return null;
/*1728*/            do
                    {
                        int k;
                        Object obj2;
/*1728*/                if((k = ((nextTable) (obj1)).nextTable) == i && ((obj2 = ((nextTable) (obj1)).nextTable) == obj || obj2 != null && obj.equals(obj2)))
/*1730*/                    return ((nextTable) (obj1));
/*1731*/                if(k >= 0)
/*1732*/                    continue;
/*1732*/                if(obj1 instanceof nextTable)
/*1733*/                    obj1 = ((nextTable)obj1).nextTable;
/*1736*/                else
/*1736*/                    return ((nextTable) (obj1)).nextTable(i, obj);
/*1738*/                continue label0;
                    } while((obj1 = ((nextTable) (obj1)).nextTable) != null);
/*1739*/            return null;
                } while(true);
            }

            final nextTable nextTable[];

            ( a[])
            {
/*1712*/        super(-1, null, null, null);
/*1713*/        nextTable = a;
            }
}
