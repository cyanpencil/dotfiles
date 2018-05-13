// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Synchronized

static class mutex
    implements Serializable
{

            Object _mthdelegate()
            {
/*  73*/        return _flddelegate;
            }

            public String toString()
            {
/*  79*/        Object obj = mutex;
/*  79*/        JVM INSTR monitorenter ;
/*  80*/        return _flddelegate.toString();
                Exception exception;
/*  81*/        exception;
/*  81*/        throw exception;
            }

            final Object _flddelegate;
            final Object mutex;

            Q(Object obj, Object obj1)
            {
/*  68*/        _flddelegate = Preconditions.checkNotNull(obj);
/*  69*/        mutex = obj1 != null ? obj1 : ((Object) (this));
            }
}
