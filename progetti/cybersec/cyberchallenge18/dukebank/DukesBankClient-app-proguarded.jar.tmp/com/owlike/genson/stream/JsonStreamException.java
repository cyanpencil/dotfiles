// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JsonStreamException.java

package com.owlike.genson.stream;


public final class JsonStreamException extends RuntimeException
{
    static class Builder
    {

                public JsonStreamException create()
                {
/*  63*/            return new JsonStreamException(message, cause, row, col);
                }

                Builder locate(int i, int j)
                {
/*  67*/            row = i;
/*  68*/            col = j;
/*  69*/            return this;
                }

                public Builder message(String s)
                {
/*  73*/            message = s;
/*  74*/            return this;
                }

                public Builder cause(Throwable throwable)
                {
/*  78*/            cause = throwable;
/*  79*/            return this;
                }

                private int col;
                private int row;
                private String message;
                private Throwable cause;

                Builder()
                {
                }
    }


            public JsonStreamException(String s, Throwable throwable)
            {
/*  17*/        this(s, throwable, -1, -1);
            }

            public JsonStreamException(String s)
            {
/*  21*/        this(s, null);
            }

            public JsonStreamException(Throwable throwable)
            {
/*  25*/        this(null, throwable);
            }

            JsonStreamException(String s, Throwable throwable, int i, int j)
            {
/*  30*/        super(s, throwable);
/*  31*/        column = j;
/*  32*/        row = i;
            }

            public final int getColumn()
            {
/*  36*/        return column;
            }

            public final int getRow()
            {
/*  40*/        return row;
            }

            public static Exception niceTrace(Exception exception)
            {
                StackTraceElement astacktraceelement[];
/*  44*/        StackTraceElement astacktraceelement1[] = new StackTraceElement[(astacktraceelement = exception.getStackTrace()).length - 1];
/*  47*/        System.arraycopy(astacktraceelement, 1, astacktraceelement1, 0, astacktraceelement.length - 1);
/*  48*/        exception.setStackTrace(astacktraceelement1);
/*  49*/        return exception;
            }

            public final JsonStreamException niceTrace()
            {
/*  53*/        return (JsonStreamException)niceTrace(((Exception) (this)));
            }

            private static final long serialVersionUID = 0x6f7dbc085d9b2addL;
            private final int column;
            private final int row;
}
