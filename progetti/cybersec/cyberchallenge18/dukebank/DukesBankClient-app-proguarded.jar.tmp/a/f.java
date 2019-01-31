// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TransactionFacade.java

package a;

import b.g;
import ch.zhaw.dukesbank.client.model.Transaction;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class f
{

            public f()
            {
            }

            public static void a(Transaction transaction)
            {
/*  21*/        g g1 = new g();
/*  23*/        g1.a(transaction);
/*  27*/        g1.c();
/*  28*/        return;
/*  24*/        transaction;
/*  25*/        Logger.getLogger(a/f.getName()).log(Level.SEVERE, null, transaction);
/*  27*/        g1.c();
/*  28*/        return;
/*  27*/        transaction;
/*  27*/        g1.c();
/*  27*/        throw transaction;
            }

            public static List a()
            {
/*  32*/        g g1 = new g();
/*  34*/        Object obj = g1.b();
/*  39*/        g1.c();
/*  34*/        return ((List) (obj));
                Object obj1;
/*  35*/        obj1;
/*  36*/        Logger.getLogger(a/f.getName()).log(Level.SEVERE, null, ((Throwable) (obj1)));
/*  37*/        obj1 = new LinkedList();
/*  39*/        g1.c();
/*  37*/        return ((List) (obj1));
/*  39*/        obj1;
/*  39*/        g1.c();
/*  39*/        throw obj1;
            }
}
