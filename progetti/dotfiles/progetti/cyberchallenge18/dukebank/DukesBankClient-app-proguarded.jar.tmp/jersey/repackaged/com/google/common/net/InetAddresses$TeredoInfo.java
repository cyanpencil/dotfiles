// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InetAddresses.java

package jersey.repackaged.com.google.common.net;

import java.net.Inet4Address;
import jersey.repackaged.com.google.common.base.MoreObjects;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.net:
//            InetAddresses

public static final class flags
{

            public final Inet4Address getClient()
            {
/* 660*/        return client;
            }

            private final Inet4Address server;
            private final Inet4Address client;
            private final int port;
            private final int flags;

            public (Inet4Address inet4address, Inet4Address inet4address1, int i, int j)
            {
/* 644*/        Preconditions.checkArgument(i >= 0 && i <= 65535, "port '%s' is out of range (0 <= port <= 0xffff)", new Object[] {
/* 644*/            Integer.valueOf(i)
                });
/* 646*/        Preconditions.checkArgument(j >= 0 && j <= 65535, "flags '%s' is out of range (0 <= flags <= 0xffff)", new Object[] {
/* 646*/            Integer.valueOf(j)
                });
/* 649*/        server = (Inet4Address)MoreObjects.firstNonNull(inet4address, InetAddresses.access$000());
/* 650*/        client = (Inet4Address)MoreObjects.firstNonNull(inet4address1, InetAddresses.access$000());
/* 651*/        port = i;
/* 652*/        flags = j;
            }
}
