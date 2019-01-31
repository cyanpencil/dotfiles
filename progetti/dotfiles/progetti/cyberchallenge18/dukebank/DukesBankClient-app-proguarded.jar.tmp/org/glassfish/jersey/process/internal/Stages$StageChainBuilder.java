// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Stages.java

package org.glassfish.jersey.process.internal;

import java.util.Deque;
import java.util.LinkedList;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.process.internal:
//            ChainableStage, Stage, Stages, AbstractChainableStage

static class lastStage
    implements lastStage
{

            public lastStage to(Function function)
            {
/* 222*/        transformations.push(function);
/* 223*/        return this;
            }

            public transformations to(ChainableStage chainablestage)
            {
/* 228*/        addTailStage(chainablestage);
/* 229*/        lastStage = chainablestage;
/* 231*/        return this;
            }

            private void addTailStage(Stage stage)
            {
/* 235*/        Stage stage1 = stage;
/* 236*/        if(!transformations.isEmpty())
/* 237*/            stage1 = convertTransformations(stage);
/* 239*/        if(rootStage != null)
                {
/* 240*/            lastStage.setDefaultNext(stage1);
/* 240*/            return;
                } else
                {
/* 242*/            rootStage = stage1;
/* 244*/            return;
                }
            }

            public Stage build(Stage stage)
            {
/* 248*/        addTailStage(stage);
/* 250*/        return rootStage;
            }

            public Stage build()
            {
/* 255*/        return build(null);
            }

            private Stage convertTransformations(Stage stage)
            {
/* 260*/        if(stage == null)
/* 261*/            stage = new ((Function)transformations.poll());
/* 263*/        else
/* 263*/            stage = new ((Function)transformations.poll(), stage);
                Function function;
/* 267*/        while((function = (Function)transformations.poll()) != null) 
/* 268*/            stage = new (function, stage);
/* 271*/        return stage;
            }

            private final Deque transformations;
            private Stage rootStage;
            private ChainableStage lastStage;

            private (Function function)
            {
/* 207*/        transformations = new LinkedList();
/* 212*/        transformations.push(function);
            }

            private transformations(ChainableStage chainablestage)
            {
/* 207*/        transformations = new LinkedList();
/* 216*/        rootStage = chainablestage;
/* 217*/        lastStage = chainablestage;
            }



            // Unreferenced inner class org/glassfish/jersey/process/internal/Stages$1

/* anonymous class */
    static class Stages._cls1 extends AbstractChainableStage
    {

                public final Stage.Continuation apply(Object obj)
                {
/*  61*/            return Stage.Continuation.of(obj, getDefaultNext());
                }

    }

}
