// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ChunkedInput.java

package org.glassfish.jersey.client;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.*;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.internal.PropertiesDelegate;
import org.glassfish.jersey.message.MessageBodyWorkers;

// Referenced classes of package org.glassfish.jersey.client:
//            ChunkParser

public class ChunkedInput extends GenericType
    implements Closeable
{
    static class FixedMultiBoundaryParser extends AbstractBoundaryParser
    {

                byte[] getDelimiter(byte byte0, int i, byte abyte0[])
                {
/* 305*/            (abyte0 = Arrays.copyOf(abyte0, abyte0.length))[i] = byte0;
/* 308*/            return getDelimiter(i, abyte0);
                }

                byte[] getDelimiter(int i, byte abyte0[])
                {
/* 314*/            Iterator iterator = delimiters.iterator();
/* 314*/            do
                    {
/* 314*/                if(!iterator.hasNext())
/* 314*/                    break;
/* 314*/                byte abyte1[] = (byte[])iterator.next();
/* 315*/                if(i <= abyte1.length)
                        {
/* 319*/                    int j = 0;
/* 319*/                    while(j <= i && j < abyte1.length && abyte1[j] == abyte0[j]) 
                            {
/* 322*/                        if(i == j)
/* 323*/                            return abyte1;
/* 319*/                        j++;
                            }
                        }
                    } while(true);
/* 328*/            return null;
                }

                int getDelimiterBufferSize()
                {
/* 333*/            return longestDelimiterLength;
                }

                private final List delimiters = new ArrayList();
                private final int longestDelimiterLength;

                public transient FixedMultiBoundaryParser(String as[])
                {
/* 287*/            int i = (as = as).length;
/* 287*/            for(int j = 0; j < i; j++)
                    {
                        String s;
/* 287*/                byte abyte0[] = (s = as[j]).getBytes();
/* 289*/                delimiters.add(Arrays.copyOf(abyte0, abyte0.length));
                    }

/* 292*/            Collections.sort(delimiters, new Comparator() {

                        public int compare(byte abyte1[], byte abyte2[])
                        {
/* 295*/                    return Integer.compare(abyte1.length, abyte2.length);
                        }

                        public volatile int compare(Object obj, Object obj1)
                        {
/* 292*/                    return compare((byte[])obj, (byte[])obj1);
                        }

                        final FixedMultiBoundaryParser this$0;

                        
                        {
/* 292*/                    this$0 = FixedMultiBoundaryParser.this;
/* 292*/                    super();
                        }
            });
/* 299*/            as = (byte[])delimiters.get(delimiters.size() - 1);
/* 300*/            longestDelimiterLength = as.length;
                }
    }

    static class FixedBoundaryParser extends AbstractBoundaryParser
    {

                byte[] getDelimiter(byte byte0, int i, byte abyte0[])
                {
/* 266*/            return delimiter;
                }

                byte[] getDelimiter(int i, byte abyte0[])
                {
/* 271*/            return delimiter;
                }

                int getDelimiterBufferSize()
                {
/* 276*/            return delimiter.length;
                }

                private final byte delimiter[];

                public FixedBoundaryParser(byte abyte0[])
                {
/* 261*/            delimiter = Arrays.copyOf(abyte0, abyte0.length);
                }
    }

    static abstract class AbstractBoundaryParser
        implements ChunkParser
    {

                public byte[] readChunk(InputStream inputstream)
                    throws IOException
                {
/* 128*/            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
/* 129*/            byte abyte0[] = new byte[getDelimiterBufferSize()];
                    int i;
                    int j;
/* 134*/            do
                    {
/* 134*/                j = 0;
/* 135*/                do
                        {
/* 135*/                    if((i = inputstream.read()) == -1)
/* 136*/                        break;
/* 136*/                    byte byte0 = (byte)i;
                            byte abyte1[];
/* 137*/                    if((abyte1 = getDelimiter(byte0, j, abyte0)) != null && byte0 == abyte1[j])
                            {
/* 141*/                        abyte0[j++] = byte0;
/* 142*/                        if(j == abyte1.length)
/* 144*/                            break;
/* 146*/                        continue;
                            }
/* 146*/                    if(j > 0)
                            {
/* 147*/                        byte abyte2[] = getDelimiter(j - 1, abyte0);
/* 148*/                        abyte0[j] = byte0;
                                int k;
/* 150*/                        if((k = matchTail(abyte0, 1, j, abyte2)) == 0)
                                {
/* 153*/                            bytearrayoutputstream.write(abyte0, 0, j);
/* 154*/                            bytearrayoutputstream.write(byte0);
/* 155*/                            j = 0;
/* 155*/                            continue;
                                }
/* 156*/                        if(k == abyte2.length)
/* 162*/                            break;
/* 162*/                        bytearrayoutputstream.write(abyte0, 0, (j + 1) - k);
/* 163*/                        j = k;
                            } else
                            {
/* 166*/                        bytearrayoutputstream.write(byte0);
                            }
                        } while(true);
                    } while(i != -1 && bytearrayoutputstream.size() == 0);
/* 172*/            if(j > 0 && j != getDelimiter(j - 1, abyte0).length)
/* 174*/                bytearrayoutputstream.write(abyte0, 0, j);
/* 177*/            if(bytearrayoutputstream.size() > 0)
/* 177*/                return bytearrayoutputstream.toByteArray();
/* 177*/            else
/* 177*/                return null;
                }

                abstract byte[] getDelimiter(byte byte0, int i, byte abyte0[]);

                abstract byte[] getDelimiter(int i, byte abyte0[]);

                abstract int getDelimiterBufferSize();

                private static int matchTail(byte abyte0[], int i, int j, byte abyte1[])
                {
/* 235*/            if(abyte1 == null)
/* 236*/                return 0;
/* 240*/            int k = 0;
/* 240*/label0:
/* 240*/            do
                    {
/* 240*/label1:
                        {
/* 240*/                    if(k >= j)
/* 241*/                        break label0;
/* 241*/                    int l = j - k;
/* 242*/                    for(int i1 = 0; i1 < l; i1++)
/* 243*/                        if(abyte0[i + k + i1] != abyte1[i1])
/* 242*/                            break label1;

/* 250*/                    return l;
                        }
/* 240*/                k++;
                    } while(true);
/* 252*/            return 0;
                }

                private AbstractBoundaryParser()
                {
                }

    }


            public static ChunkParser createParser(String s)
            {
/*  99*/        return new FixedBoundaryParser(s.getBytes());
            }

            public static ChunkParser createParser(byte abyte0[])
            {
/* 110*/        return new FixedBoundaryParser(abyte0);
            }

            public static transient ChunkParser createMultiParser(String as[])
            {
/* 121*/        return new FixedMultiBoundaryParser(as);
            }

            protected ChunkedInput(Type type, InputStream inputstream, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, MessageBodyWorkers messagebodyworkers, PropertiesDelegate propertiesdelegate)
            {
/* 356*/        super(type);
/*  82*/        parser = createParser("\r\n");
/* 358*/        inputStream = inputstream;
/* 359*/        annotations = aannotation;
/* 360*/        mediaType = mediatype;
/* 361*/        headers = multivaluedmap;
/* 362*/        messageBodyWorkers = messagebodyworkers;
/* 363*/        propertiesDelegate = propertiesdelegate;
            }

            public ChunkParser getParser()
            {
/* 376*/        return parser;
            }

            public void setParser(ChunkParser chunkparser)
            {
/* 389*/        parser = chunkparser;
            }

            public MediaType getChunkType()
            {
/* 407*/        return mediaType;
            }

            public void setChunkType(MediaType mediatype)
                throws IllegalArgumentException
            {
/* 429*/        if(mediatype == null)
                {
/* 430*/            throw new IllegalArgumentException(LocalizationMessages.CHUNKED_INPUT_MEDIA_TYPE_NULL());
                } else
                {
/* 432*/            mediaType = mediatype;
/* 433*/            return;
                }
            }

            public void setChunkType(String s)
                throws IllegalArgumentException
            {
/* 448*/        mediaType = MediaType.valueOf(s);
            }

            public void close()
            {
/* 453*/        if(closed.compareAndSet(false, true) && inputStream != null)
/* 456*/            try
                    {
/* 456*/                inputStream.close();
/* 459*/                return;
                    }
/* 457*/            catch(IOException ioexception)
                    {
/* 458*/                LOGGER.log(Level.FINE, LocalizationMessages.CHUNKED_INPUT_STREAM_CLOSING_ERROR(), ioexception);
                    }
            }

            public boolean isClosed()
            {
/* 470*/        return closed.get();
            }

            public Object read()
                throws IllegalStateException
            {
/* 489*/        if(closed.get())
/* 490*/            throw new IllegalStateException(LocalizationMessages.CHUNKED_INPUT_CLOSED());
                ByteArrayInputStream bytearrayinputstream;
                byte abyte0[];
/* 494*/        if((abyte0 = parser.readChunk(inputStream)) == null)
                {
/* 496*/            close();
/* 496*/            break MISSING_BLOCK_LABEL_120;
                }
/* 498*/        bytearrayinputstream = new ByteArrayInputStream(abyte0);
/* 504*/        return messageBodyWorkers.readFrom(getRawType(), getType(), annotations, mediaType, headers, propertiesDelegate, bytearrayinputstream, Collections.emptyList(), false);
                IOException ioexception;
/* 515*/        ioexception;
/* 516*/        Logger.getLogger(getClass().getName()).log(Level.FINE, ioexception.getMessage(), ioexception);
/* 517*/        close();
/* 519*/        return null;
            }

            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/client/ChunkedInput.getName());
            private final AtomicBoolean closed = new AtomicBoolean(false);
            private ChunkParser parser;
            private MediaType mediaType;
            private final InputStream inputStream;
            private final Annotation annotations[];
            private final MultivaluedMap headers;
            private final MessageBodyWorkers messageBodyWorkers;
            private final PropertiesDelegate propertiesDelegate;

}
