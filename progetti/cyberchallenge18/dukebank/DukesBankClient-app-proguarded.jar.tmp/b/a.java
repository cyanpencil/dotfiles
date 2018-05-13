// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractFacadeRest.java

package b;

import c.b;
import c.d;
import ch.zhaw.dukesbank.client.model.Authorization;
import ch.zhaw.dukesbank.client.model.License;
import e.e;
import e.f;
import java.lang.reflect.Type;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public abstract class a
{

            public a(Class class1)
            {
/*  40*/        b = new d.a(class1);
/*  43*/        a = c.target(f.a().b()).path(a()).queryParam("licenseToken", new Object[] {
/*  44*/            e.d.a().b().getLicenseToken()
                }).queryParam("seaSurfToken", new Object[] {
/*  45*/            e.a().b()
                });
/*  47*/        String s1 = e.b.a().b().getAuthenticationToken();
/*  47*/        String s = e.b.a().b().getUsername();
/*  47*/        class1 = this;
/*  47*/        a.register(new c.a(s, s1));
            }

            protected abstract String a();

            public final void a(Object obj)
                throws ClientErrorException
            {
/*  77*/        a.request(new String[] {
/*  77*/            "application/json"
                }).post(Entity.json(obj));
            }

            public final List b()
                throws ClientErrorException
            {
/*  81*/        return (List)a.request(new String[] {
/*  81*/            "application/json"
                }).get(new GenericType(b) {

        });
            }

            public final void c()
            {
/*  90*/        c.close();
            }

            private final d.a b;
            protected final WebTarget a;
            private final Client c = ClientBuilder.newBuilder().sslContext(d.a()).build();

            static 
            {
/*  51*/        HttpsURLConnection.setDefaultHostnameVerifier(new b());
            }
}
