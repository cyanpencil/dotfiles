// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpHeaderListAdapter.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpHeaderReader

class HttpHeaderListAdapter extends HttpHeaderReader
{

            public HttpHeaderListAdapter(HttpHeaderReader httpheaderreader)
            {
/*  63*/        reader = httpheaderreader;
            }

            public void reset()
            {
/*  67*/        isTerminated = false;
            }

            public boolean hasNext()
            {
/*  72*/        if(isTerminated)
/*  73*/            return false;
/*  76*/        if(reader.hasNext())
                {
/*  77*/            if(reader.hasNextSeparator(',', true))
                    {
/*  78*/                isTerminated = true;
/*  79*/                return false;
                    } else
                    {
/*  81*/                return true;
                    }
                } else
                {
/*  85*/            return false;
                }
            }

            public boolean hasNextSeparator(char c, boolean flag)
            {
/*  90*/        if(isTerminated)
/*  91*/            return false;
/*  94*/        if(reader.hasNextSeparator(',', flag))
                {
/*  95*/            isTerminated = true;
/*  96*/            return false;
                } else
                {
/*  98*/            return reader.hasNextSeparator(c, flag);
                }
            }

            public HttpHeaderReader.Event next()
                throws ParseException
            {
/* 104*/        return next(true);
            }

            public HttpHeaderReader.Event next(boolean flag)
                throws ParseException
            {
/* 109*/        return next(flag, false);
            }

            public HttpHeaderReader.Event next(boolean flag, boolean flag1)
                throws ParseException
            {
/* 114*/        if(isTerminated)
/* 115*/            throw new ParseException("End of header", getIndex());
/* 118*/        if(reader.hasNextSeparator(',', flag))
                {
/* 119*/            isTerminated = true;
/* 120*/            throw new ParseException("End of header", getIndex());
                } else
                {
/* 123*/            return reader.next(flag, flag1);
                }
            }

            public CharSequence nextSeparatedString(char c, char c1)
                throws ParseException
            {
/* 128*/        if(isTerminated)
/* 129*/            throw new ParseException("End of header", getIndex());
/* 132*/        if(reader.hasNextSeparator(',', true))
                {
/* 133*/            isTerminated = true;
/* 134*/            throw new ParseException("End of header", getIndex());
                } else
                {
/* 137*/            return reader.nextSeparatedString(c, c1);
                }
            }

            public HttpHeaderReader.Event getEvent()
            {
/* 142*/        return reader.getEvent();
            }

            public CharSequence getEventValue()
            {
/* 147*/        return reader.getEventValue();
            }

            public CharSequence getRemainder()
            {
/* 152*/        return reader.getRemainder();
            }

            public int getIndex()
            {
/* 157*/        return reader.getIndex();
            }

            private final HttpHeaderReader reader;
            private boolean isTerminated;
}
