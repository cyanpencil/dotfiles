// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LoggingFilter.java

package org.glassfish.jersey.filter;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.*;
import javax.ws.rs.container.*;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import org.glassfish.jersey.message.MessageUtils;

public final class LoggingFilter
    implements ClientRequestFilter, ClientResponseFilter, ContainerRequestFilter, ContainerResponseFilter, WriterInterceptor
{
    class LoggingStream extends FilterOutputStream
    {

                StringBuilder getStringBuilder(Charset charset)
                {
/* 330*/            byte abyte0[] = baos.toByteArray();
/* 332*/            b.append(new String(abyte0, 0, Math.min(abyte0.length, maxEntitySize), charset));
/* 333*/            if(abyte0.length > maxEntitySize)
/* 334*/                b.append("...more...");
/* 336*/            b.append('\n');
/* 338*/            return b;
                }

                public void write(int i)
                    throws IOException
                {
/* 343*/            if(baos.size() <= maxEntitySize)
/* 344*/                baos.write(i);
/* 346*/            out.write(i);
                }

                private final StringBuilder b;
                private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final LoggingFilter this$0;

                LoggingStream(StringBuilder stringbuilder, OutputStream outputstream)
                {
/* 322*/            this$0 = LoggingFilter.this;
/* 323*/            super(outputstream);
/* 325*/            b = stringbuilder;
                }
    }


            public LoggingFilter()
            {
/* 121*/        this(LOGGER, false);
            }

            public LoggingFilter(Logger logger1, boolean flag)
            {
/* 111*/        _id = new AtomicLong(0L);
/* 133*/        logger = logger1;
/* 134*/        printEntity = flag;
/* 135*/        maxEntitySize = 8192;
            }

            public LoggingFilter(Logger logger1, int i)
            {
/* 111*/        _id = new AtomicLong(0L);
/* 148*/        logger = logger1;
/* 149*/        printEntity = true;
/* 150*/        maxEntitySize = Math.max(0, i);
            }

            private void log(StringBuilder stringbuilder)
            {
/* 154*/        if(logger != null)
/* 155*/            logger.info(stringbuilder.toString());
            }

            private StringBuilder prefixId(StringBuilder stringbuilder, long l)
            {
/* 160*/        stringbuilder.append(Long.toString(l)).append(" ");
/* 161*/        return stringbuilder;
            }

            private void printRequestLine(StringBuilder stringbuilder, String s, long l, String s1, URI uri)
            {
/* 165*/        prefixId(stringbuilder, l).append("* ").append(s).append(" on thread ").append(Thread.currentThread().getName()).append("\n");
/* 169*/        prefixId(stringbuilder, l).append("> ").append(s1).append(" ").append(uri.toASCIIString()).append("\n");
            }

            private void printResponseLine(StringBuilder stringbuilder, String s, long l, int i)
            {
/* 174*/        prefixId(stringbuilder, l).append("* ").append(s).append(" on thread ").append(Thread.currentThread().getName()).append("\n");
/* 177*/        prefixId(stringbuilder, l).append("< ").append(Integer.toString(i)).append("\n");
            }

            private void printPrefixedHeaders(StringBuilder stringbuilder, long l, String s, MultivaluedMap multivaluedmap)
            {
/* 186*/        for(multivaluedmap = getSortedHeaders(multivaluedmap.entrySet()).iterator(); multivaluedmap.hasNext();)
                {
                    Object obj;
/* 186*/            Object obj1 = (List)((java.util.Map.Entry) (obj = (java.util.Map.Entry)multivaluedmap.next())).getValue();
/* 188*/            obj = (String)((java.util.Map.Entry) (obj)).getKey();
/* 190*/            if(((List) (obj1)).size() == 1)
                    {
/* 191*/                prefixId(stringbuilder, l).append(s).append(((String) (obj))).append(": ").append(((List) (obj1)).get(0)).append("\n");
                    } else
                    {
/* 193*/                StringBuilder stringbuilder1 = new StringBuilder();
/* 194*/                boolean flag = false;
                        Object obj2;
/* 195*/                for(obj1 = ((List) (obj1)).iterator(); ((Iterator) (obj1)).hasNext(); stringbuilder1.append(obj2))
                        {
/* 195*/                    obj2 = ((Iterator) (obj1)).next();
/* 196*/                    if(flag)
/* 197*/                        stringbuilder1.append(',');
/* 199*/                    flag = true;
                        }

/* 202*/                prefixId(stringbuilder, l).append(s).append(((String) (obj))).append(": ").append(stringbuilder1.toString()).append("\n");
                    }
                }

            }

            private Set getSortedHeaders(Set set)
            {
                TreeSet treeset;
/* 208*/        (treeset = new TreeSet(COMPARATOR)).addAll(set);
/* 210*/        return treeset;
            }

            private InputStream logInboundEntity(StringBuilder stringbuilder, InputStream inputstream, Charset charset)
                throws IOException
            {
/* 214*/        if(!inputstream.markSupported())
/* 215*/            inputstream = new BufferedInputStream(inputstream);
/* 217*/        inputstream.mark(maxEntitySize + 1);
/* 218*/        byte abyte0[] = new byte[maxEntitySize + 1];
/* 219*/        int i = inputstream.read(abyte0);
/* 220*/        stringbuilder.append(new String(abyte0, 0, Math.min(i, maxEntitySize), charset));
/* 221*/        if(i > maxEntitySize)
/* 222*/            stringbuilder.append("...more...");
/* 224*/        stringbuilder.append('\n');
/* 225*/        inputstream.reset();
/* 226*/        return inputstream;
            }

            public final void filter(ClientRequestContext clientrequestcontext)
                throws IOException
            {
/* 231*/        long l = _id.incrementAndGet();
/* 232*/        clientrequestcontext.setProperty(LOGGING_ID_PROPERTY, Long.valueOf(l));
/* 234*/        StringBuilder stringbuilder = new StringBuilder();
/* 236*/        printRequestLine(stringbuilder, "Sending client request", l, clientrequestcontext.getMethod(), clientrequestcontext.getUri());
/* 237*/        printPrefixedHeaders(stringbuilder, l, "> ", clientrequestcontext.getStringHeaders());
/* 239*/        if(printEntity && clientrequestcontext.hasEntity())
                {
/* 240*/            LoggingStream loggingstream = new LoggingStream(stringbuilder, clientrequestcontext.getEntityStream());
/* 241*/            clientrequestcontext.setEntityStream(loggingstream);
/* 242*/            clientrequestcontext.setProperty(ENTITY_LOGGER_PROPERTY, loggingstream);
/* 244*/            return;
                } else
                {
/* 245*/            log(stringbuilder);
/* 247*/            return;
                }
            }

            public final void filter(ClientRequestContext clientrequestcontext, ClientResponseContext clientresponsecontext)
                throws IOException
            {
/* 252*/        long l = (clientrequestcontext = ((ClientRequestContext) (clientrequestcontext.getProperty(LOGGING_ID_PROPERTY)))) == null ? _id.incrementAndGet() : ((Long)clientrequestcontext).longValue();
/* 255*/        clientrequestcontext = new StringBuilder();
/* 257*/        printResponseLine(clientrequestcontext, "Client response received", l, clientresponsecontext.getStatus());
/* 258*/        printPrefixedHeaders(clientrequestcontext, l, "< ", clientresponsecontext.getHeaders());
/* 260*/        if(printEntity && clientresponsecontext.hasEntity())
/* 261*/            clientresponsecontext.setEntityStream(logInboundEntity(clientrequestcontext, clientresponsecontext.getEntityStream(), MessageUtils.getCharset(clientresponsecontext.getMediaType())));
/* 265*/        log(clientrequestcontext);
            }

            public final void filter(ContainerRequestContext containerrequestcontext)
                throws IOException
            {
/* 270*/        long l = _id.incrementAndGet();
/* 271*/        containerrequestcontext.setProperty(LOGGING_ID_PROPERTY, Long.valueOf(l));
/* 273*/        StringBuilder stringbuilder = new StringBuilder();
/* 275*/        printRequestLine(stringbuilder, "Server has received a request", l, containerrequestcontext.getMethod(), containerrequestcontext.getUriInfo().getRequestUri());
/* 276*/        printPrefixedHeaders(stringbuilder, l, "> ", containerrequestcontext.getHeaders());
/* 278*/        if(printEntity && containerrequestcontext.hasEntity())
/* 279*/            containerrequestcontext.setEntityStream(logInboundEntity(stringbuilder, containerrequestcontext.getEntityStream(), MessageUtils.getCharset(containerrequestcontext.getMediaType())));
/* 283*/        log(stringbuilder);
            }

            public final void filter(ContainerRequestContext containerrequestcontext, ContainerResponseContext containerresponsecontext)
                throws IOException
            {
                Object obj;
/* 289*/        long l = (obj = containerrequestcontext.getProperty(LOGGING_ID_PROPERTY)) == null ? _id.incrementAndGet() : ((Long)obj).longValue();
/* 292*/        obj = new StringBuilder();
/* 294*/        printResponseLine(((StringBuilder) (obj)), "Server responded with a response", l, containerresponsecontext.getStatus());
/* 295*/        printPrefixedHeaders(((StringBuilder) (obj)), l, "< ", containerresponsecontext.getStringHeaders());
/* 297*/        if(printEntity && containerresponsecontext.hasEntity())
                {
/* 298*/            obj = new LoggingStream(((StringBuilder) (obj)), containerresponsecontext.getEntityStream());
/* 299*/            containerresponsecontext.setEntityStream(((OutputStream) (obj)));
/* 300*/            containerrequestcontext.setProperty(ENTITY_LOGGER_PROPERTY, obj);
/* 302*/            return;
                } else
                {
/* 303*/            log(((StringBuilder) (obj)));
/* 305*/            return;
                }
            }

            public final void aroundWriteTo(WriterInterceptorContext writerinterceptorcontext)
                throws IOException, WebApplicationException
            {
/* 310*/        LoggingStream loggingstream = (LoggingStream)writerinterceptorcontext.getProperty(ENTITY_LOGGER_PROPERTY);
/* 311*/        writerinterceptorcontext.proceed();
/* 312*/        if(loggingstream != null)
/* 313*/            log(loggingstream.getStringBuilder(MessageUtils.getCharset(writerinterceptorcontext.getMediaType())));
            }

            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/filter/LoggingFilter.getName());
            private static final String NOTIFICATION_PREFIX = "* ";
            private static final String REQUEST_PREFIX = "> ";
            private static final String RESPONSE_PREFIX = "< ";
            private static final String ENTITY_LOGGER_PROPERTY = (new StringBuilder()).append(org/glassfish/jersey/filter/LoggingFilter.getName()).append(".entityLogger").toString();
            private static final String LOGGING_ID_PROPERTY = (new StringBuilder()).append(org/glassfish/jersey/filter/LoggingFilter.getName()).append(".id").toString();
            private static final Comparator COMPARATOR = new Comparator() {

                public final int compare(java.util.Map.Entry entry, java.util.Map.Entry entry1)
                {
/* 102*/            return ((String)entry.getKey()).compareToIgnoreCase((String)entry1.getKey());
                }

                public final volatile int compare(Object obj, Object obj1)
                {
/*  98*/            return compare((java.util.Map.Entry)obj, (java.util.Map.Entry)obj1);
                }

    };
            private static final int DEFAULT_MAX_ENTITY_SIZE = 8192;
            private final Logger logger;
            private final AtomicLong _id;
            private final boolean printEntity;
            private final int maxEntitySize;


}
