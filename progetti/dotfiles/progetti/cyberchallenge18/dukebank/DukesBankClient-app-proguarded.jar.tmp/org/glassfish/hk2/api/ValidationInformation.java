// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ValidationInformation.java

package org.glassfish.hk2.api;


// Referenced classes of package org.glassfish.hk2.api:
//            Operation, ActiveDescriptor, Injectee, Filter

public interface ValidationInformation
{

    public abstract Operation getOperation();

    public abstract ActiveDescriptor getCandidate();

    public abstract Injectee getInjectee();

    public abstract Filter getFilter();

    public abstract StackTraceElement getCaller();
}
