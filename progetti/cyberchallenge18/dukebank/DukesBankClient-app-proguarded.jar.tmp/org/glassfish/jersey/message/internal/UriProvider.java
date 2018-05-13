// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UriProvider.java

package org.glassfish.jersey.message.internal;

import java.net.URI;
import java.net.URISyntaxException;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.spi.HeaderDelegateProvider;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            Utils

public class UriProvider
    implements HeaderDelegateProvider
{

            public UriProvider()
            {
            }

            public boolean supports(Class class1)
            {
/*  63*/        return class1 == java/net/URI;
            }

            public String toString(URI uri)
            {
/*  68*/        Utils.throwIllegalArgumentExceptionIfNull(uri, LocalizationMessages.URI_IS_NULL());
/*  69*/        return uri.toASCIIString();
            }

            public URI fromString(String s)
            {
/*  74*/        Utils.throwIllegalArgumentExceptionIfNull(s, LocalizationMessages.URI_IS_NULL());
/*  76*/        return new URI(s);
                URISyntaxException urisyntaxexception;
/*  77*/        urisyntaxexception;
/*  78*/        throw new IllegalArgumentException((new StringBuilder("Error parsing uri '")).append(s).append("'").toString(), urisyntaxexception);
            }

            public volatile String toString(Object obj)
            {
/*  58*/        return toString((URI)obj);
            }

            public volatile Object fromString(String s)
            {
/*  58*/        return fromString(s);
            }
}
