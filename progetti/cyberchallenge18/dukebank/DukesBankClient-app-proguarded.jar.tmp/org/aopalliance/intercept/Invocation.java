// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Invocation.java

package org.aopalliance.intercept;


// Referenced classes of package org.aopalliance.intercept:
//            Joinpoint

public interface Invocation
    extends Joinpoint
{

    public abstract Object[] getArguments();
}
