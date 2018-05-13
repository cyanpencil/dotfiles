// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TracingLogger.java

package org.glassfish.jersey.message.internal;


// Referenced classes of package org.glassfish.jersey.message.internal:
//            TracingLogger

static class vel
{

            static final int $SwitchMap$org$glassfish$jersey$message$internal$TracingLogger$Level[];

            static 
            {
/* 298*/        $SwitchMap$org$glassfish$jersey$message$internal$TracingLogger$Level = new int[vel.values().length];
/* 298*/        try
                {
/* 298*/            $SwitchMap$org$glassfish$jersey$message$internal$TracingLogger$Level[vel.SUMMARY.ordinal()] = 1;
                }
/* 298*/        catch(NoSuchFieldError _ex) { }
/* 298*/        try
                {
/* 298*/            $SwitchMap$org$glassfish$jersey$message$internal$TracingLogger$Level[vel.TRACE.ordinal()] = 2;
                }
/* 298*/        catch(NoSuchFieldError _ex) { }
/* 298*/        try
                {
/* 298*/            $SwitchMap$org$glassfish$jersey$message$internal$TracingLogger$Level[vel.VERBOSE.ordinal()] = 3;
                }
/* 298*/        catch(NoSuchFieldError _ex) { }
            }
}
