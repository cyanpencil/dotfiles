// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;


// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

static class  extends 
{

            public final Integer adapt(String s)
            {
/* 759*/        return Integer.valueOf(Integer.parseInt(s));
            }

            public final String adapt(Integer integer)
            {
/* 764*/        return integer.toString();
            }

            public final volatile String adapt(Object obj)
            {
/* 756*/        return adapt((Integer)obj);
            }

            public final volatile Object adapt(String s)
            {
/* 756*/        return adapt(s);
            }

            ()
            {
            }
}
