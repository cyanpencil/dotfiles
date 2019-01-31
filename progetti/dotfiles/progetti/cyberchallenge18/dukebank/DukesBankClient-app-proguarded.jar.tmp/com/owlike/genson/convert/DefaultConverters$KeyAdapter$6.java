// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;


// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

static class  extends 
{

            public final Float adapt(String s)
            {
/* 783*/        return Float.valueOf(Float.parseFloat(s));
            }

            public final String adapt(Float float1)
            {
/* 788*/        return float1.toString();
            }

            public final volatile String adapt(Object obj)
            {
/* 780*/        return adapt((Float)obj);
            }

            public final volatile Object adapt(String s)
            {
/* 780*/        return adapt(s);
            }

            ()
            {
            }
}
