// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CustomClientRequestFilter.java

package c;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.DatatypeConverter;

public final class a
    implements ClientRequestFilter
{

            public a(String s, String s1)
            {
/*  24*/        a = s;
/*  25*/        b = s1;
            }

            public final void filter(ClientRequestContext clientrequestcontext)
                throws IOException
            {
/*  30*/        clientrequestcontext = clientrequestcontext.getHeaders();
/*  31*/        String s = a();
/*  32*/        clientrequestcontext.add("Authorization", s);
            }

            private String a()
            {
/*  36*/        String s = (new StringBuilder()).append(a).append(":").append(b).toString();
/*  38*/        return (new StringBuilder("BASIC ")).append(DatatypeConverter.printBase64Binary(s.getBytes("UTF-8"))).toString();
                UnsupportedEncodingException unsupportedencodingexception;
/*  39*/        unsupportedencodingexception;
/*  40*/        throw new IllegalStateException("Cannot encode with UTF-8", unsupportedencodingexception);
            }

            private final String a;
            private final String b;
}
