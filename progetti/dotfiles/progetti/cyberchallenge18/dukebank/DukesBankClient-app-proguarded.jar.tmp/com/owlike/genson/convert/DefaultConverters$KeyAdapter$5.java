// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;


// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

static class  extends 
{

            public final Long adapt(String s)
            {
/* 771*/        return Long.valueOf(Long.parseLong(s));
            }

            public final String adapt(Long long1)
            {
/* 776*/        return long1.toString();
            }

            public final volatile String adapt(Object obj)
            {
/* 768*/        return adapt((Long)obj);
            }

            public final volatile Object adapt(String s)
            {
/* 768*/        return adapt(s);
            }

            ()
            {
            }
}
