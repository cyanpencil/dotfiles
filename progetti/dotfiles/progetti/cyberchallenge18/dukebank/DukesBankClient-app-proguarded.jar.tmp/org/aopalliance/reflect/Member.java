// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Member.java

package org.aopalliance.reflect;


// Referenced classes of package org.aopalliance.reflect:
//            ProgramUnit, Class

public interface Member
    extends ProgramUnit
{

    public abstract Class getDeclaringClass();

    public abstract String getName();

    public abstract int getModifiers();

    public static final int USER_SIDE = 0;
    public static final int PROVIDER_SIDE = 1;
}
