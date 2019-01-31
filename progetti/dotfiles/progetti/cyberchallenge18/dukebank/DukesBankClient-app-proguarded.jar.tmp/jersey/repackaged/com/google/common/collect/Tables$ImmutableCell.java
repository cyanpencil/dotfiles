// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Tables.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Tables

static final class value extends value
    implements Serializable
{

            public final Object getRowKey()
            {
/*  85*/        return rowKey;
            }

            public final Object getColumnKey()
            {
/*  89*/        return columnKey;
            }

            public final Object getValue()
            {
/*  93*/        return value;
            }

            private final Object rowKey;
            private final Object columnKey;
            private final Object value;

            (Object obj, Object obj1, Object obj2)
            {
/*  78*/        rowKey = obj;
/*  79*/        columnKey = obj1;
/*  80*/        value = obj2;
            }
}
