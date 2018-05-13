// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AccountFacadeRest.java

package b;

import ch.zhaw.dukesbank.client.model.Account;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

// Referenced classes of package b:
//            a

public final class b extends a
{

            public b()
            {
/*  18*/        super(ch/zhaw/dukesbank/client/model/Account);
            }

            protected final String a()
            {
/*  23*/        return "account";
            }

            public final Account d()
                throws ClientErrorException
            {
/*  27*/        return (Account)a.request(new String[] {
/*  27*/            "application/json"
                }).get(ch/zhaw/dukesbank/client/model/Account);
            }
}
