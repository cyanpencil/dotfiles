// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Stage.java

package org.glassfish.jersey.process.internal;

import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.process.internal:
//            ChainableStage

public interface Stage
{
    public static interface Builder
    {

        public abstract Builder to(Function function);

        public abstract Builder to(ChainableStage chainablestage);

        public abstract Stage build();

        public abstract Stage build(Stage stage);
    }

    public static final class Continuation
    {

                public static Continuation of(Object obj, Stage stage)
                {
/*  93*/            return new Continuation(obj, stage);
                }

                public static Continuation of(Object obj)
                {
/* 105*/            return new Continuation(obj, null);
                }

                public final Object result()
                {
/* 114*/            return result;
                }

                public final Stage next()
                {
/* 124*/            return next;
                }

                public final boolean hasNext()
                {
/* 139*/            return next != null;
                }

                private final Object result;
                private final Stage next;

                Continuation(Object obj, Stage stage)
                {
/*  77*/            result = obj;
/*  78*/            next = stage;
                }
    }


    public abstract Continuation apply(Object obj);
}
