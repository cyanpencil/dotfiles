// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Values.java

package org.glassfish.jersey.internal.util.collection;


// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            UnsafeValue, Values

static class throwable
    implements UnsafeValue
{

            public Object get()
                throws Throwable
            {
/* 233*/        throw throwable;
            }

            public boolean equals(Object obj)
            {
/* 238*/        if(this == obj)
/* 239*/            return true;
/* 241*/        if(obj == null || getClass() != obj.getClass())
/* 242*/            return false;
/* 245*/        else
/* 245*/            return throwable.equals(((throwable)obj).throwable);
            }

            public int hashCode()
            {
/* 250*/        if(throwable != null)
/* 250*/            return throwable.hashCode();
/* 250*/        else
/* 250*/            return 0;
            }

            public String toString()
            {
/* 255*/        return (new StringBuilder("ExceptionValue{throwable=")).append(throwable).append('}').toString();
            }

            private final Throwable throwable;

            public I(Throwable throwable1)
            {
/* 228*/        throwable = throwable1;
            }
}
