// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Suppliers.java

package jersey.repackaged.com.google.common.base;

import java.io.Serializable;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Objects, Supplier, Suppliers

static class instance
    implements Serializable, Supplier
{

            public Object get()
            {
/* 231*/        return instance;
            }

            public boolean equals(Object obj)
            {
/* 235*/        if(obj instanceof instance)
                {
/* 236*/            obj = (instance)obj;
/* 237*/            return Objects.equal(instance, ((instance) (obj)).instance);
                } else
                {
/* 239*/            return false;
                }
            }

            public int hashCode()
            {
/* 243*/        return Objects.hashCode(new Object[] {
/* 243*/            instance
                });
            }

            public String toString()
            {
/* 247*/        String s = String.valueOf(String.valueOf(instance));
/* 247*/        return (new StringBuilder(22 + s.length())).append("Suppliers.ofInstance(").append(s).append(")").toString();
            }

            final Object instance;

            (Object obj)
            {
/* 227*/        instance = obj;
            }
}
