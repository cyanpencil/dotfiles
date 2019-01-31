// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMapEntry.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;
import jersey.repackaged.com.google.common.base.Objects;

abstract class AbstractMapEntry
    implements java.util.Map.Entry
{

            AbstractMapEntry()
            {
            }

            public abstract Object getKey();

            public abstract Object getValue();

            public Object setValue(Object obj)
            {
/*  43*/        throw new UnsupportedOperationException();
            }

            public boolean equals(Object obj)
            {
/*  47*/        if(obj instanceof java.util.Map.Entry)
                {
/*  48*/            obj = (java.util.Map.Entry)obj;
/*  49*/            return Objects.equal(getKey(), ((java.util.Map.Entry) (obj)).getKey()) && Objects.equal(getValue(), ((java.util.Map.Entry) (obj)).getValue());
                } else
                {
/*  52*/            return false;
                }
            }

            public int hashCode()
            {
/*  56*/        Object obj = getKey();
/*  57*/        Object obj1 = getValue();
/*  58*/        return (obj != null ? obj.hashCode() : 0) ^ (obj1 != null ? obj1.hashCode() : 0);
            }

            public String toString()
            {
/*  65*/        String s = String.valueOf(String.valueOf(getKey()));
/*  65*/        String s1 = String.valueOf(String.valueOf(getValue()));
/*  65*/        return (new StringBuilder(1 + s.length() + s1.length())).append(s).append("=").append(s1).toString();
            }
}
