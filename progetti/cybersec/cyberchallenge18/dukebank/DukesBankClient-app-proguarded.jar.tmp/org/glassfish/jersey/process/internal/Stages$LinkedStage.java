// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Stages.java

package org.glassfish.jersey.process.internal;

import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.process.internal:
//            Stage, Stages

public static class <init>
    implements Stage
{

            public <init> apply(Object obj)
            {
/* 308*/        return of(transformation.apply(obj), nextStage);
            }

            private final Stage nextStage;
            private final Function transformation;

            public (Function function, Stage stage)
            {
/* 293*/        nextStage = stage;
/* 294*/        transformation = function;
            }

            public transformation(Function function)
            {
/* 303*/        this(function, null);
            }
}
