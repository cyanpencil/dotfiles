// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Errors.java

package org.glassfish.jersey.internal;

import org.glassfish.jersey.Severity;

// Referenced classes of package org.glassfish.jersey.internal:
//            Errors

static class 
{

            static final int $SwitchMap$org$glassfish$jersey$Severity[];

            static 
            {
/* 172*/        $SwitchMap$org$glassfish$jersey$Severity = new int[Severity.values().length];
/* 172*/        try
                {
/* 172*/            $SwitchMap$org$glassfish$jersey$Severity[Severity.FATAL.ordinal()] = 1;
                }
/* 172*/        catch(NoSuchFieldError _ex) { }
/* 172*/        try
                {
/* 172*/            $SwitchMap$org$glassfish$jersey$Severity[Severity.WARNING.ordinal()] = 2;
                }
/* 172*/        catch(NoSuchFieldError _ex) { }
/* 172*/        try
                {
/* 172*/            $SwitchMap$org$glassfish$jersey$Severity[Severity.HINT.ordinal()] = 3;
                }
/* 172*/        catch(NoSuchFieldError _ex) { }
            }
}
