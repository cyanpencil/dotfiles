// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpAuthenticationFilter.java

package org.glassfish.jersey.client.authentication;


// Referenced classes of package org.glassfish.jersey.client.authentication:
//            HttpAuthenticationFeature, HttpAuthenticationFilter

static class ode
{

            static final int $SwitchMap$org$glassfish$jersey$client$authentication$HttpAuthenticationFeature$Mode[];

            static 
            {
/* 147*/        $SwitchMap$org$glassfish$jersey$client$authentication$HttpAuthenticationFeature$Mode = new int[ode.values().length];
/* 147*/        try
                {
/* 147*/            $SwitchMap$org$glassfish$jersey$client$authentication$HttpAuthenticationFeature$Mode[ode.BASIC_PREEMPTIVE.ordinal()] = 1;
                }
/* 147*/        catch(NoSuchFieldError _ex) { }
/* 147*/        try
                {
/* 147*/            $SwitchMap$org$glassfish$jersey$client$authentication$HttpAuthenticationFeature$Mode[ode.BASIC_NON_PREEMPTIVE.ordinal()] = 2;
                }
/* 147*/        catch(NoSuchFieldError _ex) { }
/* 147*/        try
                {
/* 147*/            $SwitchMap$org$glassfish$jersey$client$authentication$HttpAuthenticationFeature$Mode[ode.DIGEST.ordinal()] = 3;
                }
/* 147*/        catch(NoSuchFieldError _ex) { }
/* 147*/        try
                {
/* 147*/            $SwitchMap$org$glassfish$jersey$client$authentication$HttpAuthenticationFeature$Mode[ode.UNIVERSAL.ordinal()] = 4;
                }
/* 147*/        catch(NoSuchFieldError _ex) { }
            }
}
