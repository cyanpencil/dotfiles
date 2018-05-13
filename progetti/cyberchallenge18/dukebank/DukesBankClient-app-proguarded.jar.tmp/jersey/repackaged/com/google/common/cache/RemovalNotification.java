// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RemovalNotification.java

package jersey.repackaged.com.google.common.cache;

import java.util.Map;
import jersey.repackaged.com.google.common.base.Objects;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            RemovalCause

public final class RemovalNotification
    implements java.util.Map.Entry
{

            RemovalNotification(Object obj, Object obj1, RemovalCause removalcause)
            {
/*  48*/        key = obj;
/*  49*/        value = obj1;
/*  50*/        cause = (RemovalCause)Preconditions.checkNotNull(removalcause);
            }

            public final Object getKey()
            {
/*  69*/        return key;
            }

            public final Object getValue()
            {
/*  73*/        return value;
            }

            public final Object setValue(Object obj)
            {
/*  77*/        throw new UnsupportedOperationException();
            }

            public final boolean equals(Object obj)
            {
/*  81*/        if(obj instanceof java.util.Map.Entry)
                {
/*  82*/            obj = (java.util.Map.Entry)obj;
/*  83*/            return Objects.equal(getKey(), ((java.util.Map.Entry) (obj)).getKey()) && Objects.equal(getValue(), ((java.util.Map.Entry) (obj)).getValue());
                } else
                {
/*  86*/            return false;
                }
            }

            public final int hashCode()
            {
/*  90*/        Object obj = getKey();
/*  91*/        Object obj1 = getValue();
/*  92*/        return (obj != null ? obj.hashCode() : 0) ^ (obj1 != null ? obj1.hashCode() : 0);
            }

            public final String toString()
            {
/*  99*/        String s = String.valueOf(String.valueOf(getKey()));
/*  99*/        String s1 = String.valueOf(String.valueOf(getValue()));
/*  99*/        return (new StringBuilder(1 + s.length() + s1.length())).append(s).append("=").append(s1).toString();
            }

            private final Object key;
            private final Object value;
            private final RemovalCause cause;
}
