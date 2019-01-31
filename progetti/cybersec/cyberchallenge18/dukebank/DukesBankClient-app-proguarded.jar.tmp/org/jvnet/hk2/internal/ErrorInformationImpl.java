// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ErrorInformationImpl.java

package org.jvnet.hk2.internal;

import org.glassfish.hk2.api.*;

public class ErrorInformationImpl
    implements ErrorInformation
{

            ErrorInformationImpl(ErrorType errortype, Descriptor descriptor1, Injectee injectee1, MultiException multiexception)
            {
/*  63*/        errorType = errortype;
/*  64*/        descriptor = descriptor1;
/*  65*/        injectee = injectee1;
/*  66*/        exception = multiexception;
            }

            public ErrorType getErrorType()
            {
/*  74*/        return errorType;
            }

            public Descriptor getDescriptor()
            {
/*  82*/        return descriptor;
            }

            public Injectee getInjectee()
            {
/*  90*/        return injectee;
            }

            public MultiException getAssociatedException()
            {
/*  98*/        return exception;
            }

            public String toString()
            {
/* 102*/        return (new StringBuilder("ErrorInformation(")).append(errorType).append(",").append(descriptor).append(",").append(injectee).append(",").append(exception).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private final ErrorType errorType;
            private final Descriptor descriptor;
            private final Injectee injectee;
            private final MultiException exception;
}
