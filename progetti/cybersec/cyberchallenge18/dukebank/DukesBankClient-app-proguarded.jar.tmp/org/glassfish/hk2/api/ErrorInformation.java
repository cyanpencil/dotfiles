// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ErrorInformation.java

package org.glassfish.hk2.api;


// Referenced classes of package org.glassfish.hk2.api:
//            ErrorType, Descriptor, Injectee, MultiException

public interface ErrorInformation
{

    public abstract ErrorType getErrorType();

    public abstract Descriptor getDescriptor();

    public abstract Injectee getInjectee();

    public abstract MultiException getAssociatedException();
}
