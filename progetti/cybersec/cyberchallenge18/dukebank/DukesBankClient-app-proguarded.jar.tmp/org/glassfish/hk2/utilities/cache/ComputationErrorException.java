// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ComputationErrorException.java

package org.glassfish.hk2.utilities.cache;


public class ComputationErrorException extends RuntimeException
{

            public ComputationErrorException()
            {
            }

            public ComputationErrorException(Object obj)
            {
/*  60*/        computation = obj;
            }

            public Object getComputation()
            {
/*  64*/        return computation;
            }

            private static final long serialVersionUID = 0x107678cbd02a7f71L;
            public Object computation;
}
