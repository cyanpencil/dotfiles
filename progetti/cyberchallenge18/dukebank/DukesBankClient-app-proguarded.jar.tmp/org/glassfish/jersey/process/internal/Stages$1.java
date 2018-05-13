// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Stages.java

package org.glassfish.jersey.process.internal;


// Referenced classes of package org.glassfish.jersey.process.internal:
//            AbstractChainableStage, Stage, Stages

static class tinuation extends AbstractChainableStage
{

            public final tinuation apply(Object obj)
            {
/*  61*/        return tinuation.of(obj, getDefaultNext());
            }

            tinuation()
            {
            }
}
