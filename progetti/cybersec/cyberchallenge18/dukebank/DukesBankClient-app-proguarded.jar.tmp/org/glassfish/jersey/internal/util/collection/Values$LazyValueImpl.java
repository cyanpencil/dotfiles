// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Values.java

package org.glassfish.jersey.internal.util.collection;


// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            LazyValue, Value, Values

static class delegate
    implements LazyValue
{

            public Object get()
            {
/* 335*/        if((obj = value) == null)
/* 337*/            synchronized(lock)
                    {
/* 338*/                if((obj = value) == null)
/* 340*/                    value = ((Value) (obj = Values.of(_flddelegate.get())));
                    }
/* 344*/        return ((Value) (obj)).get();
            }

            public boolean isInitialized()
            {
/* 349*/        return value != null;
            }

            public boolean equals(Object obj)
            {
/* 354*/        if(this == obj)
/* 355*/            return true;
/* 357*/        if(obj == null || getClass() != obj.getClass())
/* 358*/            return false;
/* 361*/        else
/* 361*/            return _flddelegate.equals(((delegate)obj)._flddelegate);
            }

            public int hashCode()
            {
/* 366*/        if(_flddelegate != null)
/* 366*/            return _flddelegate.hashCode();
/* 366*/        else
/* 366*/            return 0;
            }

            public String toString()
            {
/* 371*/        return (new StringBuilder("LazyValue{delegate=")).append(_flddelegate.toString()).append('}').toString();
            }

            private final Object lock = new Object();
            private final Value _flddelegate;
            private volatile Value value;

            public (Value value1)
            {
/* 329*/        _flddelegate = value1;
            }
}
