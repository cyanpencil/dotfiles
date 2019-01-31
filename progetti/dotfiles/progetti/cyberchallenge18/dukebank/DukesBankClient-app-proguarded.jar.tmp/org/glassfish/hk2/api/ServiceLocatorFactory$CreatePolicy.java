// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorFactory.java

package org.glassfish.hk2.api;


// Referenced classes of package org.glassfish.hk2.api:
//            ServiceLocatorFactory

public static final class  extends Enum
{

            public static [] values()
            {
/* 204*/        return ([])$VALUES.clone();
            }

            public static y_3B_.clone valueOf(String s)
            {
/* 204*/        return (y_3B_.clone)Enum.valueOf(org/glassfish/hk2/api/ServiceLocatorFactory$CreatePolicy, s);
            }

            public static final ERROR RETURN;
            public static final ERROR DESTROY;
            public static final ERROR ERROR;
            private static final ERROR $VALUES[];

            static 
            {
/* 206*/        RETURN = new <init>("RETURN", 0);
/* 209*/        DESTROY = new <init>("DESTROY", 1);
/* 212*/        ERROR = new <init>("ERROR", 2);
/* 204*/        $VALUES = (new .VALUES[] {
/* 204*/            RETURN, DESTROY, ERROR
                });
            }

            private (String s, int i)
            {
/* 204*/        super(s, i);
            }
}
