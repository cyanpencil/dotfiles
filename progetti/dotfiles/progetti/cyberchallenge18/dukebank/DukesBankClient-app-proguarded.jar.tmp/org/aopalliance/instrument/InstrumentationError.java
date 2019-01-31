// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InstrumentationError.java

package org.aopalliance.instrument;


// Referenced classes of package org.aopalliance.instrument:
//            Instrumentation

public class InstrumentationError extends Error
{

            public InstrumentationError(Instrumentation instrumentation, Throwable throwable)
            {
/*  15*/        super((new StringBuilder("Error while instrumenting ")).append(instrumentation).toString(), throwable);
            }
}
