// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;


// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

static class  extends 
{

            public final Short adapt(String s)
            {
/* 747*/        return Short.valueOf(Short.parseShort(s));
            }

            public final String adapt(Short short1)
            {
/* 752*/        return short1.toString();
            }

            public final volatile String adapt(Object obj)
            {
/* 744*/        return adapt((Short)obj);
            }

            public final volatile Object adapt(String s)
            {
/* 744*/        return adapt(s);
            }

            ()
            {
            }
}
