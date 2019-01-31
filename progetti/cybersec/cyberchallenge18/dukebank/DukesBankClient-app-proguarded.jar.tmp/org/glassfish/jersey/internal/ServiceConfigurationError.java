// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceConfigurationError.java

package org.glassfish.jersey.internal;


public class ServiceConfigurationError extends Error
{

            public ServiceConfigurationError(String s)
            {
/*  65*/        super(s);
            }

            public ServiceConfigurationError(Throwable throwable)
            {
/*  73*/        super(throwable);
            }

            private static final long serialVersionUID = 0x8996da5ff5c9e656L;
}
