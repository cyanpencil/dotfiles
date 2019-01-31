// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LicenseFacadeRest.java

package b;

import c.b;
import c.d;
import ch.zhaw.dukesbank.client.model.License;
import e.f;
import java.text.MessageFormat;
import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;

public final class e
{

            public e()
            {
/*  31*/        a = b.target(f.a().b()).path("license");
            }

            public final License a(License license)
                throws ClientErrorException
            {
/*  39*/        return (License)a.request(new String[] {
/*  39*/            "application/json"
                }).post(Entity.json(license)).readEntity(ch/zhaw/dukesbank/client/model/License);
            }

            public final void a(String s)
                throws ClientErrorException
            {
/*  43*/        a.path(MessageFormat.format("{0}", new Object[] {
/*  43*/            s
                })).request().delete();
            }

            public final void a()
            {
/*  47*/        b.close();
            }

            private final WebTarget a;
            private final Client b = ClientBuilder.newBuilder().sslContext(d.a()).build();

            static 
            {
/*  35*/        HttpsURLConnection.setDefaultHostnameVerifier(new b());
            }
}
