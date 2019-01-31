// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpUrlConnectorProvider.java

package org.glassfish.jersey.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

// Referenced classes of package org.glassfish.jersey.client:
//            HttpUrlConnectorProvider

static class 
    implements 
{

            public HttpURLConnection getConnection(URL url)
                throws IOException
            {
/* 299*/        return (HttpURLConnection)url.openConnection();
            }

            private ()
            {
            }

}
