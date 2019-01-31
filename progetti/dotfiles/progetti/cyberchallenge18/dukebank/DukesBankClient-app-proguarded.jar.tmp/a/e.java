// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ScanFacade.java

package a;

import b.f;
import ch.zhaw.dukesbank.client.model.Customer;
import ch.zhaw.dukesbank.client.model.Scan;
import e.c;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class e
{

            public e()
            {
            }

            public static Scan a()
            {
/*  21*/        f f1 = new f();
                Scan scan;
                Object obj1;
/*1019*/        ((Calendar) (obj1 = Calendar.getInstance(TimeZone.getTimeZone("CET")))).setTime(new Date());
/*  23*/        (scan = new Scan()).setClientTimestamp((obj1 = ((Calendar) (obj1)).getTime()) != null ? (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(((Date) (obj1))) : "date unknown");
/*  25*/        scan.setUsername(c.a().b().getUsername());
/*  27*/        scan = f1.a(scan);
/*  32*/        f1.a();
/*  27*/        return scan;
                Object obj;
/*  28*/        obj;
/*  29*/        Logger.getLogger(a/e.getName()).log(Level.SEVERE, null, ((Throwable) (obj)));
/*  32*/        f1.a();
/*  30*/        return null;
/*  32*/        obj;
/*  32*/        f1.a();
/*  32*/        throw obj;
            }
}
