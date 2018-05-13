// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LicenseFacade.java

package a;

import b.e;
import ch.zhaw.dukesbank.client.model.License;
import java.util.logging.Level;
import java.util.logging.Logger;

public class d
{

            public d()
            {
            }

            public static License a(String s)
            {
/*  20*/        e e1 = new e();
                License license;
/*  22*/        (license = new License()).setLicense(s);
/*  25*/        license = e1.a(license);
/*  27*/        e.d.a().a(license);
/*  28*/        s = license;
/*  33*/        e1.a();
/*  28*/        return s;
                Exception exception;
/*  29*/        exception;
/*  30*/        Logger.getLogger(a/d.getName()).log(Level.SEVERE, null, exception);
/*  33*/        e1.a();
/*  31*/        return null;
/*  33*/        s;
/*  33*/        e1.a();
/*  33*/        throw s;
            }

            public static void a()
            {
/*  38*/        e e1 = new e();
/*  40*/        e1.a(e.d.a().b().getLicenseToken());
/*  41*/        e.d.a().a(null);
/*  45*/        e1.a();
/*  46*/        return;
                Object obj;
/*  42*/        obj;
/*  43*/        Logger.getLogger(a/d.getName()).log(Level.SEVERE, null, ((Throwable) (obj)));
/*  45*/        e1.a();
/*  46*/        return;
/*  45*/        obj;
/*  45*/        e1.a();
/*  45*/        throw obj;
            }
}
