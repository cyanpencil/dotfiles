// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReaderWriter.java

package org.glassfish.jersey.message.internal;

import java.io.*;
import java.nio.charset.Charset;
import java.security.AccessController;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.PropertiesHelper;

public final class ReaderWriter
{

            private static int getBufferSize()
            {
                String s;
/*  86*/        if((s = (String)AccessController.doPrivileged(PropertiesHelper.getSystemProperty("jersey.config.io.bufferSize"))) == null)
/*  89*/            break MISSING_BLOCK_LABEL_80;
                int i;
/*  89*/        if((i = Integer.parseInt(s)) <= 0)
/*  91*/            throw new NumberFormatException("Value not positive.");
/*  93*/        return i;
                NumberFormatException numberformatexception;
/*  94*/        numberformatexception;
/*  95*/        LOGGER.log(Level.CONFIG, (new StringBuilder("Value of jersey.config.io.bufferSize property is not a valid positive integer [")).append(s).append("]. Reverting to default [").append(8192).append("].").toString(), numberformatexception);
/* 102*/        return 8192;
            }

            public static void writeTo(InputStream inputstream, OutputStream outputstream)
                throws IOException
            {
/* 114*/        byte abyte0[] = new byte[BUFFER_SIZE];
                int i;
/* 115*/        while((i = inputstream.read(abyte0)) != -1) 
/* 116*/            outputstream.write(abyte0, 0, i);
            }

            public static void writeTo(Reader reader, Writer writer)
                throws IOException
            {
/* 129*/        char ac[] = new char[BUFFER_SIZE];
                int i;
/* 130*/        while((i = reader.read(ac)) != -1) 
/* 131*/            writer.write(ac, 0, i);
            }

            public static Charset getCharset(MediaType mediatype)
            {
/* 145*/        if((mediatype = mediatype != null ? ((MediaType) ((String)mediatype.getParameters().get("charset"))) : null) == null)
/* 146*/            return UTF8;
/* 146*/        else
/* 146*/            return Charset.forName(mediatype);
            }

            public static String readFromAsString(InputStream inputstream, MediaType mediatype)
                throws IOException
            {
/* 160*/        return readFromAsString(((Reader) (new InputStreamReader(inputstream, getCharset(mediatype)))));
            }

            public static String readFromAsString(Reader reader)
                throws IOException
            {
/* 172*/        StringBuilder stringbuilder = new StringBuilder();
/* 173*/        char ac[] = new char[BUFFER_SIZE];
                int i;
/* 175*/        while((i = reader.read(ac)) != -1) 
/* 176*/            stringbuilder.append(ac, 0, i);
/* 178*/        return stringbuilder.toString();
            }

            public static void writeToAsString(String s, OutputStream outputstream, MediaType mediatype)
                throws IOException
            {
/* 191*/        (outputstream = new OutputStreamWriter(outputstream, getCharset(mediatype))).write(s, 0, s.length());
/* 193*/        outputstream.flush();
            }

            public static void safelyClose(Closeable closeable)
            {
/* 203*/        try
                {
/* 203*/            closeable.close();
/* 208*/            return;
                }
                // Misplaced declaration of an exception variable
/* 204*/        catch(Closeable closeable)
                {
/* 205*/            LOGGER.log(Level.FINE, LocalizationMessages.MESSAGE_CONTENT_INPUT_STREAM_CLOSE_FAILED(), closeable);
/* 208*/            return;
                }
                // Misplaced declaration of an exception variable
/* 206*/        catch(Closeable closeable)
                {
/* 207*/            LOGGER.log(Level.FINE, LocalizationMessages.MESSAGE_CONTENT_INPUT_STREAM_CLOSE_FAILED(), closeable);
                }
            }

            private ReaderWriter()
            {
            }

            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/message/internal/ReaderWriter.getName());
            public static final Charset UTF8 = Charset.forName("UTF-8");
            public static final int BUFFER_SIZE = getBufferSize();

}
