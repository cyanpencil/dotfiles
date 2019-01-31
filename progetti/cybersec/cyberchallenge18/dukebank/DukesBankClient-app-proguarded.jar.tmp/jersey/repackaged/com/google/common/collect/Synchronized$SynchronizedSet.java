// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import java.util.Set;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Synchronized

static class ection extends ection
    implements Set
{

            Set _mthdelegate()
            {
/* 221*/        return (Set)super._mthdelegate();
            }

            public boolean equals(Object obj)
            {
/* 225*/        if(obj == this)
/* 226*/            return true;
/* 228*/        Object obj1 = mutex;
/* 228*/        JVM INSTR monitorenter ;
/* 229*/        return _mthdelegate().equals(obj);
/* 230*/        obj;
/* 230*/        throw obj;
            }

            public int hashCode()
            {
/* 234*/        Object obj = mutex;
/* 234*/        JVM INSTR monitorenter ;
/* 235*/        return _mthdelegate().hashCode();
                Exception exception;
/* 236*/        exception;
/* 236*/        throw exception;
            }

            volatile Collection _mthdelegate()
            {
/* 213*/        return _mthdelegate();
            }

            volatile Object _mthdelegate()
            {
/* 213*/        return _mthdelegate();
            }

            ection(Set set, Object obj)
            {
/* 217*/        super(set, obj, null);
            }
}
