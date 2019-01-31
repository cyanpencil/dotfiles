// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AuthorizationFacade.java

package a;

import b.c;
import ch.zhaw.dukesbank.client.model.Authorization;
import ch.zhaw.dukesbank.client.model.Credential;
import java.util.logging.Level;
import java.util.logging.Logger;

public class b
{

            public b()
            {
            }

            public static Authorization a(String s, String s1)
            {
/*  21*/        c c1 = new c();
                Credential credential;
/*  23*/        (credential = new Credential()).setUsername(s);
/*  25*/        credential.setPassword(s1);
/*  27*/        s = c1.a(credential);
/*  32*/        c1.b();
/*  27*/        return s;
                Exception exception;
/*  28*/        exception;
/*  29*/        Logger.getLogger(a/b.getName()).log(Level.SEVERE, null, exception);
/*  32*/        c1.b();
/*  30*/        return null;
/*  32*/        s;
/*  32*/        c1.b();
/*  32*/        throw s;
            }

            public static void a()
            {
/*  37*/        c c1 = new c();
/*  39*/        c1.a();
/*  40*/        e.b.a().a(null);
/*  44*/        c1.b();
/*  45*/        return;
                Object obj;
/*  41*/        obj;
/*  42*/        Logger.getLogger(a/b.getName()).log(Level.SEVERE, null, ((Throwable) (obj)));
/*  44*/        c1.b();
/*  45*/        return;
/*  44*/        obj;
/*  44*/        c1.b();
/*  44*/        throw obj;
            }
}
