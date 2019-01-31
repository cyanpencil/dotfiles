// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ParsedResultType.java

package com.google.zxing.client.result;


public final class ParsedResultType extends Enum
{

            public static ParsedResultType[] values()
            {
/*  25*/        return (ParsedResultType[])$VALUES.clone();
            }

            public static ParsedResultType valueOf(String s)
            {
/*  25*/        return (ParsedResultType)Enum.valueOf(com/google/zxing/client/result/ParsedResultType, s);
            }

            private ParsedResultType(String s, int i)
            {
/*  25*/        super(s, i);
            }

            public static final ParsedResultType ADDRESSBOOK;
            public static final ParsedResultType EMAIL_ADDRESS;
            public static final ParsedResultType PRODUCT;
            public static final ParsedResultType URI;
            public static final ParsedResultType TEXT;
            public static final ParsedResultType GEO;
            public static final ParsedResultType TEL;
            public static final ParsedResultType SMS;
            public static final ParsedResultType CALENDAR;
            public static final ParsedResultType WIFI;
            public static final ParsedResultType ISBN;
            public static final ParsedResultType VIN;
            private static final ParsedResultType $VALUES[];

            static 
            {
/*  27*/        ADDRESSBOOK = new ParsedResultType("ADDRESSBOOK", 0);
/*  28*/        EMAIL_ADDRESS = new ParsedResultType("EMAIL_ADDRESS", 1);
/*  29*/        PRODUCT = new ParsedResultType("PRODUCT", 2);
/*  30*/        URI = new ParsedResultType("URI", 3);
/*  31*/        TEXT = new ParsedResultType("TEXT", 4);
/*  32*/        GEO = new ParsedResultType("GEO", 5);
/*  33*/        TEL = new ParsedResultType("TEL", 6);
/*  34*/        SMS = new ParsedResultType("SMS", 7);
/*  35*/        CALENDAR = new ParsedResultType("CALENDAR", 8);
/*  36*/        WIFI = new ParsedResultType("WIFI", 9);
/*  37*/        ISBN = new ParsedResultType("ISBN", 10);
/*  38*/        VIN = new ParsedResultType("VIN", 11);
/*  25*/        $VALUES = (new ParsedResultType[] {
/*  25*/            ADDRESSBOOK, EMAIL_ADDRESS, PRODUCT, URI, TEXT, GEO, TEL, SMS, CALENDAR, WIFI, 
/*  25*/            ISBN, VIN
                });
            }
}
