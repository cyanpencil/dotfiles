// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractFormProvider.java

package org.glassfish.jersey.message.internal;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AbstractMessageReaderWriterProvider, ReaderWriter

public abstract class AbstractFormProvider extends AbstractMessageReaderWriterProvider
{

            public AbstractFormProvider()
            {
            }

            public MultivaluedMap readFrom(MultivaluedMap multivaluedmap, MediaType mediatype, boolean flag, InputStream inputstream)
                throws IOException
            {
/*  69*/        inputstream = readFromAsString(inputstream, mediatype);
/*  71*/        mediatype = ReaderWriter.getCharset(mediatype).name();
/*  73*/        inputstream = new StringTokenizer(inputstream, "&");
/*  76*/        do
                {
/*  76*/            if(!inputstream.hasMoreTokens())
/*  77*/                break;
                    String s;
                    int i;
/*  77*/            if((i = (s = inputstream.nextToken()).indexOf('=')) < 0)
/*  80*/                multivaluedmap.add(flag ? ((Object) (URLDecoder.decode(s, mediatype))) : ((Object) (s)), null);
/*  81*/            else
/*  81*/            if(i > 0)
/*  82*/                if(flag)
/*  83*/                    multivaluedmap.add(URLDecoder.decode(s.substring(0, i), mediatype), URLDecoder.decode(s.substring(i + 1), mediatype));
/*  86*/                else
/*  86*/                    multivaluedmap.add(s.substring(0, i), s.substring(i + 1));
                } while(true);
/*  90*/        return multivaluedmap;
                IllegalArgumentException illegalargumentexception;
/*  91*/        illegalargumentexception;
/*  92*/        throw new BadRequestException(illegalargumentexception);
            }

            public void writeTo(MultivaluedMap multivaluedmap, MediaType mediatype, OutputStream outputstream)
                throws IOException
            {
/* 100*/        String s = ReaderWriter.getCharset(mediatype).name();
/* 102*/        StringBuilder stringbuilder = new StringBuilder();
/* 103*/        for(multivaluedmap = multivaluedmap.entrySet().iterator(); multivaluedmap.hasNext();)
                {
                    java.util.Map.Entry entry;
/* 103*/            Iterator iterator = ((List)(entry = (java.util.Map.Entry)multivaluedmap.next()).getValue()).iterator();
/* 104*/            while(iterator.hasNext()) 
                    {
/* 104*/                String s1 = (String)iterator.next();
/* 105*/                if(stringbuilder.length() > 0)
/* 106*/                    stringbuilder.append('&');
/* 108*/                stringbuilder.append(URLEncoder.encode((String)entry.getKey(), s));
/* 109*/                if(s1 != null)
                        {
/* 110*/                    stringbuilder.append('=');
/* 111*/                    stringbuilder.append(URLEncoder.encode(s1, s));
                        }
                    }
                }

/* 116*/        writeToAsString(stringbuilder.toString(), outputstream, mediatype);
            }
}
