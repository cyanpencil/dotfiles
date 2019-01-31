// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ScanFacadeRest.java

package b;

import c.a;
import c.b;
import c.d;
import ch.zhaw.dukesbank.client.model.Authorization;
import ch.zhaw.dukesbank.client.model.License;
import ch.zhaw.dukesbank.client.model.Scan;
import e.e;
import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public final class f
{

            public f()
            {
/*  34*/        a = b.target(e.f.a().b()).path("scan").queryParam("licenseToken", new Object[] {
/*  35*/            e.d.a().b().getLicenseToken()
                }).queryParam("seaSurfToken", new Object[] {
/*  36*/            e.a().b()
                });
/*  38*/        Object obj = e.b.a().b().getUsername();
/*  39*/        String s = e.b.a().b().getAuthenticationToken();
/*  40*/        String s1 = s;
/*  40*/        s = ((String) (obj));
/*  40*/        obj = this;
/*  40*/        a.register(new a(s, s1));
            }

            public final Scan a(Scan scan)
                throws ClientErrorException
            {
/*  48*/        return (Scan)a.request(new String[] {
/*  48*/            "application/json"
                }).post(Entity.json(scan)).readEntity(ch/zhaw/dukesbank/client/model/Scan);
            }

            public final void a()
            {
/*  52*/        b.close();
            }

            private final WebTarget a;
            private final Client b = ClientBuilder.newBuilder().sslContext(d.a()).build();

            static 
            {
/*  44*/        HttpsURLConnection.setDefaultHostnameVerifier(new b());
            }
}
