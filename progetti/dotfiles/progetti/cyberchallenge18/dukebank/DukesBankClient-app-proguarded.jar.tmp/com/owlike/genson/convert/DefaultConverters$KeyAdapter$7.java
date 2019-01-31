// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;


// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

static class  extends 
{

            public final Double adapt(String s)
            {
/* 795*/        return Double.valueOf(Double.parseDouble(s));
            }

            public final String adapt(Double double1)
            {
/* 800*/        return double1.toString();
            }

            public final volatile String adapt(Object obj)
            {
/* 792*/        return adapt((Double)obj);
            }

            public final volatile Object adapt(String s)
            {
/* 792*/        return adapt(s);
            }

            ()
            {
            }
}
