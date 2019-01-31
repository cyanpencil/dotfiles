// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ChunkedInput.java

package org.glassfish.jersey.client;

import java.util.*;

// Referenced classes of package org.glassfish.jersey.client:
//            ChunkedInput

static class longestDelimiterLength extends longestDelimiterLength
{

            byte[] getDelimiter(byte byte0, int i, byte abyte0[])
            {
/* 305*/        (abyte0 = Arrays.copyOf(abyte0, abyte0.length))[i] = byte0;
/* 308*/        return getDelimiter(i, abyte0);
            }

            byte[] getDelimiter(int i, byte abyte0[])
            {
/* 314*/        Iterator iterator = delimiters.iterator();
/* 314*/        do
                {
/* 314*/            if(!iterator.hasNext())
/* 314*/                break;
/* 314*/            byte abyte1[] = (byte[])iterator.next();
/* 315*/            if(i <= abyte1.length)
                    {
/* 319*/                int j = 0;
/* 319*/                while(j <= i && j < abyte1.length && abyte1[j] == abyte0[j]) 
                        {
/* 322*/                    if(i == j)
/* 323*/                        return abyte1;
/* 319*/                    j++;
                        }
                    }
                } while(true);
/* 328*/        return null;
            }

            int getDelimiterBufferSize()
            {
/* 333*/        return longestDelimiterLength;
            }

            private final List delimiters = new ArrayList();
            private final int longestDelimiterLength;

            public transient _cls1.this._cls0(String as[])
            {
/* 287*/        int i = (as = as).length;
/* 287*/        for(int j = 0; j < i; j++)
                {
                    String s;
/* 287*/            byte abyte0[] = (s = as[j]).getBytes();
/* 289*/            delimiters.add(Arrays.copyOf(abyte0, abyte0.length));
                }

/* 292*/        Collections.sort(delimiters, new Comparator() {

                    public int compare(byte abyte1[], byte abyte2[])
                    {
/* 295*/                return Integer.compare(abyte1.length, abyte2.length);
                    }

                    public volatile int compare(Object obj, Object obj1)
                    {
/* 292*/                return compare((byte[])obj, (byte[])obj1);
                    }

                    final ChunkedInput.FixedMultiBoundaryParser this$0;

                    
                    {
/* 292*/                this$0 = ChunkedInput.FixedMultiBoundaryParser.this;
/* 292*/                super();
                    }
        });
/* 299*/        as = (byte[])delimiters.get(delimiters.size() - 1);
/* 300*/        longestDelimiterLength = as.length;
            }
}
