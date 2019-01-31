// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractChainableStage.java

package org.glassfish.jersey.process.internal;


// Referenced classes of package org.glassfish.jersey.process.internal:
//            ChainableStage, Stage

public abstract class AbstractChainableStage
    implements ChainableStage
{

            protected AbstractChainableStage()
            {
/*  58*/        this(null);
            }

            protected AbstractChainableStage(Stage stage)
            {
/*  68*/        nextStage = stage;
            }

            public final void setDefaultNext(Stage stage)
            {
/*  73*/        nextStage = stage;
            }

            public final Stage getDefaultNext()
            {
/*  82*/        return nextStage;
            }

            private Stage nextStage;
}
