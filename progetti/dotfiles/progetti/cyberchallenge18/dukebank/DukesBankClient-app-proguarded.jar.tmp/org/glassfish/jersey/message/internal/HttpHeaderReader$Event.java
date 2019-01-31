// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpHeaderReader.java

package org.glassfish.jersey.message.internal;


// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpHeaderReader

public static final class  extends Enum
{

            public static [] values()
            {
/*  68*/        return ([])$VALUES.clone();
            }

            public static t_3B_.clone valueOf(String s)
            {
/*  68*/        return (t_3B_.clone)Enum.valueOf(org/glassfish/jersey/message/internal/HttpHeaderReader$Event, s);
            }

            public static final Control Token;
            public static final Control QuotedString;
            public static final Control Comment;
            public static final Control Separator;
            public static final Control Control;
            private static final Control $VALUES[];

            static 
            {
/*  70*/        Token = new <init>("Token", 0);
/*  70*/        QuotedString = new <init>("QuotedString", 1);
/*  70*/        Comment = new <init>("Comment", 2);
/*  70*/        Separator = new <init>("Separator", 3);
/*  70*/        Control = new <init>("Control", 4);
/*  68*/        $VALUES = (new .VALUES[] {
/*  68*/            Token, QuotedString, Comment, Separator, Control
                });
            }

            private (String s, int i)
            {
/*  68*/        super(s, i);
            }
}
