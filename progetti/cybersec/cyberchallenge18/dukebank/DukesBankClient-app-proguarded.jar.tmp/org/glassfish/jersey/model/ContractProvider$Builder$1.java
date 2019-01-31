// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ContractProvider.java

package org.glassfish.jersey.model;

import jersey.repackaged.com.google.common.collect.Maps;

// Referenced classes of package org.glassfish.jersey.model:
//            ContractProvider

class this._cls0
    implements jersey.repackaged.com.google.common.collect.
{

            public Integer transformEntry(Class class1, Integer integer)
            {
/* 241*/        return Integer.valueOf(integer.intValue() == -1 ? cess._mth700(this._cls0.this) : integer.intValue());
            }

            public volatile Object transformEntry(Object obj, Object obj1)
            {
/* 238*/        return transformEntry((Class)obj, (Integer)obj1);
            }

            final transformEntry this$0;

            Transformer()
            {
/* 238*/        this$0 = this._cls0.this;
/* 238*/        super();
            }
}
