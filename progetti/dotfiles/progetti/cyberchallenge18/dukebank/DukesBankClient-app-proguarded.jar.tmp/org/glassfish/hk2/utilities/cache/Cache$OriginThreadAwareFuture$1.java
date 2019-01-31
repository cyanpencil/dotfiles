// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Cache.java

package org.glassfish.hk2.utilities.cache;

import java.util.concurrent.Callable;

// Referenced classes of package org.glassfish.hk2.utilities.cache:
//            Cache, Computable

class val.key
    implements Callable
{

            public Object call()
                throws Exception
            {
/*  97*/        Object obj = Cache.access$000(_fld0).compute(val$key);
/*  99*/        cess._mth102(this._cls1.this, -1L);
/*  99*/        return obj;
                Exception exception;
/*  99*/        exception;
/*  99*/        cess._mth102(this._cls1.this, -1L);
/*  99*/        throw exception;
            }

            final Cache val$this$0;
            final Object val$key;
            final this._cls1 this$1;

            ()
            {
/*  93*/        this$1 = final_;
/*  93*/        val$this$0 = cache;
/*  93*/        val$key = Object.this;
/*  93*/        super();
            }
}
