// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProgramUnit.java

package org.aopalliance.reflect;


// Referenced classes of package org.aopalliance.reflect:
//            UnitLocator, Metadata

public interface ProgramUnit
{

    public abstract UnitLocator getLocator();

    public abstract Metadata getMetadata(Object obj);

    public abstract Metadata[] getMetadatas();

    public abstract void addMetadata(Metadata metadata);

    public abstract void removeMetadata(Object obj);
}
