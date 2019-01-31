// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UriComponent.java

package org.glassfish.jersey.uri;


// Referenced classes of package org.glassfish.jersey.uri:
//            UriComponent

public static final class  extends Enum
{

            public static [] values()
            {
/*  73*/        return ([])$VALUES.clone();
            }

            public static e_3B_.clone valueOf(String s)
            {
/*  73*/        return (e_3B_.clone)Enum.valueOf(org/glassfish/jersey/uri/UriComponent$Type, s);
            }

            public static final FRAGMENT UNRESERVED;
            public static final FRAGMENT SCHEME;
            public static final FRAGMENT AUTHORITY;
            public static final FRAGMENT USER_INFO;
            public static final FRAGMENT HOST;
            public static final FRAGMENT PORT;
            public static final FRAGMENT PATH;
            public static final FRAGMENT PATH_SEGMENT;
            public static final FRAGMENT MATRIX_PARAM;
            public static final FRAGMENT QUERY;
            public static final FRAGMENT QUERY_PARAM;
            public static final FRAGMENT QUERY_PARAM_SPACE_ENCODED;
            public static final FRAGMENT FRAGMENT;
            private static final FRAGMENT $VALUES[];

            static 
            {
/*  78*/        UNRESERVED = new <init>("UNRESERVED", 0);
/*  82*/        SCHEME = new <init>("SCHEME", 1);
/*  86*/        AUTHORITY = new <init>("AUTHORITY", 2);
/*  90*/        USER_INFO = new <init>("USER_INFO", 3);
/*  94*/        HOST = new <init>("HOST", 4);
/*  98*/        PORT = new <init>("PORT", 5);
/* 102*/        PATH = new <init>("PATH", 6);
/* 106*/        PATH_SEGMENT = new <init>("PATH_SEGMENT", 7);
/* 110*/        MATRIX_PARAM = new <init>("MATRIX_PARAM", 8);
/* 114*/        QUERY = new <init>("QUERY", 9);
/* 120*/        QUERY_PARAM = new <init>("QUERY_PARAM", 10);
/* 126*/        QUERY_PARAM_SPACE_ENCODED = new <init>("QUERY_PARAM_SPACE_ENCODED", 11);
/* 130*/        FRAGMENT = new <init>("FRAGMENT", 12);
/*  73*/        $VALUES = (new .VALUES[] {
/*  73*/            UNRESERVED, SCHEME, AUTHORITY, USER_INFO, HOST, PORT, PATH, PATH_SEGMENT, MATRIX_PARAM, QUERY, 
/*  73*/            QUERY_PARAM, QUERY_PARAM_SPACE_ENCODED, FRAGMENT
                });
            }

            private (String s, int i)
            {
/*  73*/        super(s, i);
            }
}
