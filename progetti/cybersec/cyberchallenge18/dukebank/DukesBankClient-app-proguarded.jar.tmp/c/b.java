// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CustomHostnameVerifier.java

package c;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public final class b
    implements HostnameVerifier
{

            public b()
            {
            }

            public final boolean verify(String s, SSLSession sslsession)
            {
/*  18*/        return true;
            }
}
