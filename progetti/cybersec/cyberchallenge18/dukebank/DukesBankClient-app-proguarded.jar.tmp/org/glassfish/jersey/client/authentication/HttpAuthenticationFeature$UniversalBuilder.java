// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpAuthenticationFeature.java

package org.glassfish.jersey.client.authentication;


// Referenced classes of package org.glassfish.jersey.client.authentication:
//            HttpAuthenticationFeature

public static interface 
    extends 
{

    public abstract  credentialsForBasic(String s, String s1);

    public abstract  credentialsForBasic(String s, byte abyte0[]);

    public abstract  credentialsForDigest(String s, String s1);

    public abstract  credentialsForDigest(String s, byte abyte0[]);
}
