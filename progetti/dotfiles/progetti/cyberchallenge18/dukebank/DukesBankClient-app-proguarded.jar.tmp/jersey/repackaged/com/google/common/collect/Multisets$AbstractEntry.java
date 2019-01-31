// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multisets.java

package jersey.repackaged.com.google.common.collect;

import jersey.repackaged.com.google.common.base.Objects;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Multiset, Multisets

static abstract class I
    implements I
{

            public boolean equals(Object obj)
            {
/* 785*/        if(obj instanceof I)
                {
/* 786*/            obj = (I)obj;
/* 787*/            return getCount() == ((getCount) (obj)).getCount() && Objects.equal(getElement(), ((getElement) (obj)).t());
                } else
                {
/* 790*/            return false;
                }
            }

            public int hashCode()
            {
                Object obj;
/* 798*/        return ((obj = getElement()) != null ? obj.hashCode() : 0) ^ getCount();
            }

            public String toString()
            {
/* 810*/        String s = String.valueOf(getElement());
                int i;
/* 811*/        if((i = getCount()) == 1)
                {
/* 812*/            return s;
                } else
                {
/* 812*/            s = String.valueOf(String.valueOf(s));
/* 812*/            return (new StringBuilder(14 + s.length())).append(s).append(" x ").append(i).toString();
                }
            }

            I()
            {
            }
}
