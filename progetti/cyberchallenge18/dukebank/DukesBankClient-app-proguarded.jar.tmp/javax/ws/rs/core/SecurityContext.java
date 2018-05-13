// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SecurityContext.java

package javax.ws.rs.core;

import java.security.Principal;

public interface SecurityContext
{

    public abstract Principal getUserPrincipal();

    public abstract boolean isUserInRole(String s);

    public abstract boolean isSecure();

    public abstract String getAuthenticationScheme();

    public static final String BASIC_AUTH = "BASIC";
    public static final String CLIENT_CERT_AUTH = "CLIENT_CERT";
    public static final String DIGEST_AUTH = "DIGEST";
    public static final String FORM_AUTH = "FORM";
}
