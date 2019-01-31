// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Stage.java

package org.glassfish.jersey.process.internal;

import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package org.glassfish.jersey.process.internal:
//            Stage, ChainableStage

public static interface 
{

    public abstract  to(Function function);

    public abstract  to(ChainableStage chainablestage);

    public abstract Stage build();

    public abstract Stage build(Stage stage);
}
