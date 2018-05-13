// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CsrfProtectionFilter.java

package org.glassfish.jersey.client.filter;

import java.io.IOException;
import java.util.*;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;

public class CsrfProtectionFilter
    implements ClientRequestFilter
{

            public CsrfProtectionFilter()
            {
/*  82*/        this("");
            }

            public CsrfProtectionFilter(String s)
            {
/*  92*/        requestedBy = s;
            }

            public void filter(ClientRequestContext clientrequestcontext)
                throws IOException
            {
/*  97*/        if(!METHODS_TO_IGNORE.contains(clientrequestcontext.getMethod()) && !clientrequestcontext.getHeaders().containsKey("X-Requested-By"))
/*  98*/            clientrequestcontext.getHeaders().add("X-Requested-By", requestedBy);
            }

            public static final String HEADER_NAME = "X-Requested-By";
            private static final Set METHODS_TO_IGNORE;
            private final String requestedBy;

            static 
            {
                HashSet hashset;
/*  69*/        (hashset = new HashSet()).add("GET");
/*  71*/        hashset.add("OPTIONS");
/*  72*/        hashset.add("HEAD");
/*  73*/        METHODS_TO_IGNORE = Collections.unmodifiableSet(hashset);
            }
}
