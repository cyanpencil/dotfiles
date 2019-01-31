// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Values.java

package org.glassfish.jersey.internal.util.collection;


// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            Value, Values

static class value
    implements Value
{

            public Object get()
            {
/* 126*/        return value;
            }

            public boolean equals(Object obj)
            {
/* 131*/        if(this == obj)
/* 132*/            return true;
/* 134*/        if(obj == null || getClass() != obj.getClass())
/* 135*/            return false;
/* 138*/        else
/* 138*/            return value.equals(((value)obj).value);
            }

            public int hashCode()
            {
/* 143*/        if(value != null)
/* 143*/            return value.hashCode();
/* 143*/        else
/* 143*/            return 0;
            }

            public String toString()
            {
/* 148*/        return (new StringBuilder("InstanceValue{value=")).append(value).append('}').toString();
            }

            private final Object value;

            public (Object obj)
            {
/* 121*/        value = obj;
            }
}
