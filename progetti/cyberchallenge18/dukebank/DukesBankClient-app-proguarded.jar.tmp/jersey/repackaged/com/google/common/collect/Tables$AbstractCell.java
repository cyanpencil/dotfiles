// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Tables.java

package jersey.repackaged.com.google.common.collect;

import jersey.repackaged.com.google.common.base.Objects;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Table, Tables

static abstract class 
    implements 
{

            public boolean equals(Object obj)
            {
/* 104*/        if(obj == this)
/* 105*/            return true;
/* 107*/        if(obj instanceof )
                {
/* 108*/            obj = ()obj;
/* 109*/            return Objects.equal(getRowKey(), ((getRowKey) (obj)).()) && Objects.equal(getColumnKey(), ((getColumnKey) (obj)).Key()) && Objects.equal(getValue(), ((getValue) (obj)).getValue());
                } else
                {
/* 113*/            return false;
                }
            }

            public int hashCode()
            {
/* 117*/        return Objects.hashCode(new Object[] {
/* 117*/            getRowKey(), getColumnKey(), getValue()
                });
            }

            public String toString()
            {
/* 121*/        String s = String.valueOf(String.valueOf(getRowKey()));
/* 121*/        String s1 = String.valueOf(String.valueOf(getColumnKey()));
/* 121*/        String s2 = String.valueOf(String.valueOf(getValue()));
/* 121*/        return (new StringBuilder(4 + s.length() + s1.length() + s2.length())).append("(").append(s).append(",").append(s1).append(")=").append(s2).toString();
            }

            ()
            {
            }
}
