// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AuthorizationFacadeRest.java

package b;

import c.a;
import c.b;
import c.d;
import ch.zhaw.dukesbank.client.model.Authorization;
import ch.zhaw.dukesbank.client.model.Credential;
import ch.zhaw.dukesbank.client.model.License;
import e.f;
import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public final class c
{

            public c()
            {
/*  34*/        a = b.target(f.a().b()).path("authorization");
            }

            public final Authorization a(Credential credential)
                throws ClientErrorException
            {
/*  42*/        return (Authorization)a.queryParam("licenseToken", new Object[] {
/*  42*/            e.d.a().b().getLicenseToken()
                }).request(new String[] {
/*  42*/            "application/json"
                }).post(Entity.json(credential)).readEntity(ch/zhaw/dukesbank/client/model/Authorization);
            }

            public final void a()
                throws ClientErrorException
            {
/*  46*/        Object obj = e.b.a().b().getUsername();
/*  47*/        String s = e.b.a().b().getAuthenticationToken();
/*  48*/        String s1 = s;
/*  48*/        s = ((String) (obj));
/*  48*/        obj = this;
/*  48*/        a.register(new a(s, s1));
/*  49*/        a.request().delete();
            }

            public final void b()
            {
/*  53*/        b.close();
            }

            private final WebTarget a;
            private final Client b = ClientBuilder.newBuilder().sslContext(d.a()).build();

            static 
            {
/*  38*/        HttpsURLConnection.setDefaultHostnameVerifier(new b());
            }
}
