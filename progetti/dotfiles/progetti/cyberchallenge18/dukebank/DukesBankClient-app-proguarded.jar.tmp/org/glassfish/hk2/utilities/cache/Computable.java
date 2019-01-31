// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Computable.java

package org.glassfish.hk2.utilities.cache;


// Referenced classes of package org.glassfish.hk2.utilities.cache:
//            ComputationErrorException

public interface Computable
{

    public abstract Object compute(Object obj)
        throws ComputationErrorException;
}
