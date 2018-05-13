// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WifiParsedResult.java

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class WifiParsedResult extends ParsedResult
{

            public WifiParsedResult(String s, String s1, String s2)
            {
/*  30*/        this(s, s1, s2, false);
            }

            public WifiParsedResult(String s, String s1, String s2, boolean flag)
            {
/*  34*/        super(ParsedResultType.WIFI);
/*  35*/        ssid = s1;
/*  36*/        networkEncryption = s;
/*  37*/        password = s2;
/*  38*/        hidden = flag;
            }

            public final String getSsid()
            {
/*  42*/        return ssid;
            }

            public final String getNetworkEncryption()
            {
/*  46*/        return networkEncryption;
            }

            public final String getPassword()
            {
/*  50*/        return password;
            }

            public final boolean isHidden()
            {
/*  54*/        return hidden;
            }

            public final String getDisplayResult()
            {
/*  59*/        StringBuilder stringbuilder = new StringBuilder(80);
/*  60*/        maybeAppend(ssid, stringbuilder);
/*  61*/        maybeAppend(networkEncryption, stringbuilder);
/*  62*/        maybeAppend(password, stringbuilder);
/*  63*/        maybeAppend(Boolean.toString(hidden), stringbuilder);
/*  64*/        return stringbuilder.toString();
            }

            private final String ssid;
            private final String networkEncryption;
            private final String password;
            private final boolean hidden;
}
