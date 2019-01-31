// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Tables.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.base.Objects;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Table

public final class Tables
{
    static abstract class AbstractCell
        implements Table.Cell
    {

                public boolean equals(Object obj)
                {
/* 104*/            if(obj == this)
/* 105*/                return true;
/* 107*/            if(obj instanceof Table.Cell)
                    {
/* 108*/                obj = (Table.Cell)obj;
/* 109*/                return Objects.equal(getRowKey(), ((Table.Cell) (obj)).getRowKey()) && Objects.equal(getColumnKey(), ((Table.Cell) (obj)).getColumnKey()) && Objects.equal(getValue(), ((Table.Cell) (obj)).getValue());
                    } else
                    {
/* 113*/                return false;
                    }
                }

                public int hashCode()
                {
/* 117*/            return Objects.hashCode(new Object[] {
/* 117*/                getRowKey(), getColumnKey(), getValue()
                    });
                }

                public String toString()
                {
/* 121*/            String s = String.valueOf(String.valueOf(getRowKey()));
/* 121*/            String s1 = String.valueOf(String.valueOf(getColumnKey()));
/* 121*/            String s2 = String.valueOf(String.valueOf(getValue()));
/* 121*/            return (new StringBuilder(4 + s.length() + s1.length() + s2.length())).append("(").append(s).append(",").append(s1).append(")=").append(s2).toString();
                }

                AbstractCell()
                {
                }
    }

    static final class ImmutableCell extends AbstractCell
        implements Serializable
    {

                public final Object getRowKey()
                {
/*  85*/            return rowKey;
                }

                public final Object getColumnKey()
                {
/*  89*/            return columnKey;
                }

                public final Object getValue()
                {
/*  93*/            return value;
                }

                private final Object rowKey;
                private final Object columnKey;
                private final Object value;

                ImmutableCell(Object obj, Object obj1, Object obj2)
                {
/*  78*/            rowKey = obj;
/*  79*/            columnKey = obj1;
/*  80*/            value = obj2;
                }
    }


            public static Table.Cell immutableCell(Object obj, Object obj1, Object obj2)
            {
/*  67*/        return new ImmutableCell(obj, obj1, obj2);
            }

            static boolean equalsImpl(Table table, Object obj)
            {
/* 600*/        if(obj == table)
/* 601*/            return true;
/* 602*/        if(obj instanceof Table)
                {
/* 603*/            obj = (Table)obj;
/* 604*/            return table.cellSet().equals(((Table) (obj)).cellSet());
                } else
                {
/* 606*/            return false;
                }
            }

            private static final Function UNMODIFIABLE_WRAPPER = new Function() {

                public final Map apply(Map map)
                {
/* 595*/            return Collections.unmodifiableMap(map);
                }

                public final volatile Object apply(Object obj)
                {
/* 592*/            return apply((Map)obj);
                }

    };

}
