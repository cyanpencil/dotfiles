// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BasicAuthenticator.java

package org.glassfish.jersey.client.authentication;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.core.MultivaluedMap;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.Base64;

// Referenced classes of package org.glassfish.jersey.client.authentication:
//            HttpAuthenticationFilter, RequestAuthenticationException, ResponseAuthenticationException

final class BasicAuthenticator
{

            BasicAuthenticator(HttpAuthenticationFilter.Credentials credentials)
            {
/*  68*/        defaultCredentials = credentials;
            }

            private String calculateAuthentication(HttpAuthenticationFilter.Credentials credentials)
            {
/*  72*/        String s = credentials.getUsername();
/*  73*/        credentials = credentials.getPassword();
/*  74*/        if(s == null)
/*  75*/            s = "";
/*  78*/        if(credentials == null)
/*  79*/            credentials = new byte[0];
                byte abyte0[];
/*  82*/        byte abyte1[] = new byte[(abyte0 = (new StringBuilder()).append(s).append(":").toString().getBytes(HttpAuthenticationFilter.CHARACTER_SET)).length + credentials.length];
/*  85*/        System.arraycopy(abyte0, 0, abyte1, 0, abyte0.length);
/*  86*/        System.arraycopy(credentials, 0, abyte1, abyte0.length, credentials.length);
/*  88*/        return (new StringBuilder("Basic ")).append(Base64.encodeAsString(abyte1)).toString();
            }

            public final void filterRequest(ClientRequestContext clientrequestcontext)
                throws RequestAuthenticationException
            {
                HttpAuthenticationFilter.Credentials credentials;
/*  98*/        if((credentials = HttpAuthenticationFilter.getCredentials(clientrequestcontext, defaultCredentials, HttpAuthenticationFilter.Type.BASIC)) == null)
                {
/* 101*/            throw new RequestAuthenticationException(LocalizationMessages.AUTHENTICATION_CREDENTIALS_MISSING_BASIC());
                } else
                {
/* 103*/            clientrequestcontext.getHeaders().add("Authorization", calculateAuthentication(credentials));
/* 104*/            return;
                }
            }

            public final boolean filterResponseAndAuthenticate(ClientRequestContext clientrequestcontext, ClientResponseContext clientresponsecontext)
            {
                Object obj;
/* 117*/        if((obj = (String)clientresponsecontext.getHeaders().getFirst("WWW-Authenticate")) != null && ((String) (obj)).trim().toUpperCase().startsWith("BASIC"))
                {
/* 119*/            if((obj = HttpAuthenticationFilter.getCredentials(clientrequestcontext, defaultCredentials, HttpAuthenticationFilter.Type.BASIC)) == null)
/* 123*/                throw new ResponseAuthenticationException(null, LocalizationMessages.AUTHENTICATION_CREDENTIALS_MISSING_BASIC());
/* 126*/            else
/* 126*/                return HttpAuthenticationFilter.repeatRequest(clientrequestcontext, clientresponsecontext, calculateAuthentication(((HttpAuthenticationFilter.Credentials) (obj))));
                } else
                {
/* 128*/            return false;
                }
            }

            private final HttpAuthenticationFilter.Credentials defaultCredentials;
}
