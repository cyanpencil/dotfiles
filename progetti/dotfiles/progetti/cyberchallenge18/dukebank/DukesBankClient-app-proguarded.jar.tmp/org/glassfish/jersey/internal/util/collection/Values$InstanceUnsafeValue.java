// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Values.java

package org.glassfish.jersey.internal.util.collection;


// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            UnsafeValue, Values

static class value
    implements UnsafeValue
{

            public Object get()
            {
/* 177*/        return value;
            }

            public boolean equals(Object obj)
            {
/* 182*/        if(this == obj)
/* 183*/            return true;
/* 185*/        if(obj == null || getClass() != obj.getClass())
/* 186*/            return false;
/* 189*/        else
/* 189*/            return value.equals(((value)obj).value);
            }

            public int hashCode()
            {
/* 194*/        if(value != null)
/* 194*/            return value.hashCode();
/* 194*/        else
/* 194*/            return 0;
            }

            public String toString()
            {
/* 199*/        return (new StringBuilder("InstanceUnsafeValue{value=")).append(value).append('}').toString();
            }

            private final Object value;

            public (Object obj)
            {
/* 172*/        value = obj;
            }
}
