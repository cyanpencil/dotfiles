// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ChainableStage.java

package org.glassfish.jersey.process.internal;


// Referenced classes of package org.glassfish.jersey.process.internal:
//            Stage

public interface ChainableStage
    extends Stage
{

    public abstract void setDefaultNext(Stage stage);
}