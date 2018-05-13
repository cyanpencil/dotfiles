// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Stages.java

package org.glassfish.jersey.process.internal;

import org.glassfish.jersey.process.Inflector;

// Referenced classes of package org.glassfish.jersey.process.internal:
//            Inflecting, Stage, Stages

static class inflector
    implements Inflecting, Stage
{

            public Inflector inflector()
            {
/* 111*/        return inflector;
            }

            public inflector apply(Object obj)
            {
/* 116*/        return inflector(obj);
            }

            private final Inflector inflector;

            public (Inflector inflector1)
            {
/* 106*/        inflector = inflector1;
            }
}
