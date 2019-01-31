// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Stages.java

package org.glassfish.jersey.process.internal;

import java.util.Deque;
import java.util.LinkedList;
import jersey.repackaged.com.google.common.base.Function;
import org.glassfish.jersey.internal.util.collection.Ref;
import org.glassfish.jersey.process.Inflector;

// Referenced classes of package org.glassfish.jersey.process.internal:
//            Inflecting, Stage, ChainableStage, AbstractChainableStage

public final class Stages
{
    public static class LinkedStage
        implements Stage
    {

                public Stage.Continuation apply(Object obj)
                {
/* 308*/            return Stage.Continuation.of(transformation.apply(obj), nextStage);
                }

                private final Stage nextStage;
                private final Function transformation;

                public LinkedStage(Function function, Stage stage)
                {
/* 293*/            nextStage = stage;
/* 294*/            transformation = function;
                }

                public LinkedStage(Function function)
                {
/* 303*/            this(function, null);
                }
    }

    static class StageChainBuilder
        implements Stage.Builder
    {

                public Stage.Builder to(Function function)
                {
/* 222*/            transformations.push(function);
/* 223*/            return this;
                }

                public Stage.Builder to(ChainableStage chainablestage)
                {
/* 228*/            addTailStage(chainablestage);
/* 229*/            lastStage = chainablestage;
/* 231*/            return this;
                }

                private void addTailStage(Stage stage)
                {
/* 235*/            Stage stage1 = stage;
/* 236*/            if(!transformations.isEmpty())
/* 237*/                stage1 = convertTransformations(stage);
/* 239*/            if(rootStage != null)
                    {
/* 240*/                lastStage.setDefaultNext(stage1);
/* 240*/                return;
                    } else
                    {
/* 242*/                rootStage = stage1;
/* 244*/                return;
                    }
                }

                public Stage build(Stage stage)
                {
/* 248*/            addTailStage(stage);
/* 250*/            return rootStage;
                }

                public Stage build()
                {
/* 255*/            return build(null);
                }

                private Stage convertTransformations(Stage stage)
                {
/* 260*/            if(stage == null)
/* 261*/                stage = new LinkedStage((Function)transformations.poll());
/* 263*/            else
/* 263*/                stage = new LinkedStage((Function)transformations.poll(), stage);
                    Function function;
/* 267*/            while((function = (Function)transformations.poll()) != null) 
/* 268*/                stage = new LinkedStage(function, stage);
/* 271*/            return stage;
                }

                private final Deque transformations;
                private Stage rootStage;
                private ChainableStage lastStage;

                private StageChainBuilder(Function function)
                {
/* 207*/            transformations = new LinkedList();
/* 212*/            transformations.push(function);
                }

                private StageChainBuilder(ChainableStage chainablestage)
                {
/* 207*/            transformations = new LinkedList();
/* 216*/            rootStage = chainablestage;
/* 217*/            lastStage = chainablestage;
                }


    }

    static class InflectingStage
        implements Inflecting, Stage
    {

                public Inflector inflector()
                {
/* 111*/            return inflector;
                }

                public Stage.Continuation apply(Object obj)
                {
/* 116*/            return Stage.Continuation.of(obj);
                }

                private final Inflector inflector;

                public InflectingStage(Inflector inflector1)
                {
/* 106*/            inflector = inflector1;
                }
    }


            private Stages()
            {
            }

            public static ChainableStage identity()
            {
/*  82*/        return IDENTITY;
            }

            public static Stage asStage(Inflector inflector)
            {
/*  98*/        return new InflectingStage(inflector);
            }

            public static Inflector extractInflector(Object obj)
            {
/* 132*/        if(obj instanceof Inflecting)
/* 133*/            return ((Inflecting)obj).inflector();
/* 136*/        else
/* 136*/            return null;
            }

            public static Stage.Builder chain(Function function)
            {
/* 146*/        return new StageChainBuilder(function);
            }

            public static Stage.Builder chain(ChainableStage chainablestage)
            {
/* 156*/        return new StageChainBuilder(chainablestage);
            }

            public static Object process(Object obj, Stage stage)
            {
/* 168*/        for(obj = Stage.Continuation.of(obj, stage); (stage = ((Stage.Continuation) (obj)).next()) != null; obj = stage.apply(((Stage.Continuation) (obj)).result()));
/* 173*/        return ((Stage.Continuation) (obj)).result();
            }

            public static Object process(Object obj, Stage stage, Ref ref)
            {
/* 193*/        stage = stage;
/* 194*/        for(obj = Stage.Continuation.of(obj, stage); ((Stage.Continuation) (obj)).next() != null; obj = (stage = ((Stage.Continuation) (obj)).next()).apply(((Stage.Continuation) (obj)).result()));
/* 200*/        ref.set(extractInflector(stage));
/* 202*/        return ((Stage.Continuation) (obj)).result();
            }

            private static final ChainableStage IDENTITY = new AbstractChainableStage() {

                public final Stage.Continuation apply(Object obj)
                {
/*  61*/            return Stage.Continuation.of(obj, getDefaultNext());
                }

    };

}
