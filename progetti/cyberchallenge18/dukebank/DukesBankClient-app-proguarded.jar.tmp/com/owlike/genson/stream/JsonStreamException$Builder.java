// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JsonStreamException.java

package com.owlike.genson.stream;


// Referenced classes of package com.owlike.genson.stream:
//            JsonStreamException

static class 
{

            public JsonStreamException create()
            {
/*  63*/        return new JsonStreamException(message, cause, row, col);
            }

            col locate(int i, int j)
            {
/*  67*/        row = i;
/*  68*/        col = j;
/*  69*/        return this;
            }

            public col message(String s)
            {
/*  73*/        message = s;
/*  74*/        return this;
            }

            public message cause(Throwable throwable)
            {
/*  78*/        cause = throwable;
/*  79*/        return this;
            }

            private int col;
            private int row;
            private String message;
            private Throwable cause;

            ()
            {
            }
}
