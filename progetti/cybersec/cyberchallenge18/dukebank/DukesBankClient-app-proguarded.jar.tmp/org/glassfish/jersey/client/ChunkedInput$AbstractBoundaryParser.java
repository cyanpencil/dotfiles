// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ChunkedInput.java

package org.glassfish.jersey.client;

import java.io.*;

// Referenced classes of package org.glassfish.jersey.client:
//            ChunkParser, ChunkedInput

static abstract class 
    implements ChunkParser
{

            public byte[] readChunk(InputStream inputstream)
                throws IOException
            {
/* 128*/        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
/* 129*/        byte abyte0[] = new byte[getDelimiterBufferSize()];
                int i;
                int j;
/* 134*/        do
                {
/* 134*/            j = 0;
/* 135*/            do
                    {
/* 135*/                if((i = inputstream.read()) == -1)
/* 136*/                    break;
/* 136*/                byte byte0 = (byte)i;
                        byte abyte1[];
/* 137*/                if((abyte1 = getDelimiter(byte0, j, abyte0)) != null && byte0 == abyte1[j])
                        {
/* 141*/                    abyte0[j++] = byte0;
/* 142*/                    if(j == abyte1.length)
/* 144*/                        break;
/* 146*/                    continue;
                        }
/* 146*/                if(j > 0)
                        {
/* 147*/                    byte abyte2[] = getDelimiter(j - 1, abyte0);
/* 148*/                    abyte0[j] = byte0;
                            int k;
/* 150*/                    if((k = matchTail(abyte0, 1, j, abyte2)) == 0)
                            {
/* 153*/                        bytearrayoutputstream.write(abyte0, 0, j);
/* 154*/                        bytearrayoutputstream.write(byte0);
/* 155*/                        j = 0;
/* 155*/                        continue;
                            }
/* 156*/                    if(k == abyte2.length)
/* 162*/                        break;
/* 162*/                    bytearrayoutputstream.write(abyte0, 0, (j + 1) - k);
/* 163*/                    j = k;
                        } else
                        {
/* 166*/                    bytearrayoutputstream.write(byte0);
                        }
                    } while(true);
                } while(i != -1 && bytearrayoutputstream.size() == 0);
/* 172*/        if(j > 0 && j != getDelimiter(j - 1, abyte0).length)
/* 174*/            bytearrayoutputstream.write(abyte0, 0, j);
/* 177*/        if(bytearrayoutputstream.size() > 0)
/* 177*/            return bytearrayoutputstream.toByteArray();
/* 177*/        else
/* 177*/            return null;
            }

            abstract byte[] getDelimiter(byte byte0, int i, byte abyte0[]);

            abstract byte[] getDelimiter(int i, byte abyte0[]);

            abstract int getDelimiterBufferSize();

            private static int matchTail(byte abyte0[], int i, int j, byte abyte1[])
            {
/* 235*/        if(abyte1 == null)
/* 236*/            return 0;
/* 240*/        int k = 0;
/* 240*/label0:
/* 240*/        do
                {
/* 240*/label1:
                    {
/* 240*/                if(k >= j)
/* 241*/                    break label0;
/* 241*/                int l = j - k;
/* 242*/                for(int i1 = 0; i1 < l; i1++)
/* 243*/                    if(abyte0[i + k + i1] != abyte1[i1])
/* 242*/                        break label1;

/* 250*/                return l;
                    }
/* 240*/            k++;
                } while(true);
/* 252*/        return 0;
            }

            private ()
            {
            }

}
