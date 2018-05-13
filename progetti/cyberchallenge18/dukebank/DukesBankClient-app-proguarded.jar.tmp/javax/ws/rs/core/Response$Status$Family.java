// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Response.java

package javax.ws.rs.core;


// Referenced classes of package javax.ws.rs.core:
//            Response

public static final class  extends Enum
{

            public static [] values()
            {
/*1412*/        return ([])$VALUES.clone();
            }

            public static y_3B_.clone valueOf(String s)
            {
/*1412*/        return (y_3B_.clone)Enum.valueOf(javax/ws/rs/core/Response$Status$Family, s);
            }

            public static y_3B_.clone familyOf(int i)
            {
/*1446*/        switch(i / 100)
                {
/*1448*/        case 1: // '\001'
/*1448*/            return INFORMATIONAL;

/*1450*/        case 2: // '\002'
/*1450*/            return SUCCESSFUL;

/*1452*/        case 3: // '\003'
/*1452*/            return REDIRECTION;

/*1454*/        case 4: // '\004'
/*1454*/            return CLIENT_ERROR;

/*1456*/        case 5: // '\005'
/*1456*/            return SERVER_ERROR;
                }
/*1458*/        return OTHER;
            }

            public static final OTHER INFORMATIONAL;
            public static final OTHER SUCCESSFUL;
            public static final OTHER REDIRECTION;
            public static final OTHER CLIENT_ERROR;
            public static final OTHER SERVER_ERROR;
            public static final OTHER OTHER;
            private static final OTHER $VALUES[];

            static 
            {
/*1417*/        INFORMATIONAL = new <init>("INFORMATIONAL", 0);
/*1421*/        SUCCESSFUL = new <init>("SUCCESSFUL", 1);
/*1425*/        REDIRECTION = new <init>("REDIRECTION", 2);
/*1429*/        CLIENT_ERROR = new <init>("CLIENT_ERROR", 3);
/*1433*/        SERVER_ERROR = new <init>("SERVER_ERROR", 4);
/*1437*/        OTHER = new <init>("OTHER", 5);
/*1412*/        $VALUES = (new .VALUES[] {
/*1412*/            INFORMATIONAL, SUCCESSFUL, REDIRECTION, CLIENT_ERROR, SERVER_ERROR, OTHER
                });
            }

            private (String s, int i)
            {
/*1412*/        super(s, i);
            }
}
