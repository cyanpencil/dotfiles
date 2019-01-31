// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RethrowErrorService.java

package org.glassfish.hk2.utilities;

import org.glassfish.hk2.api.*;

public class RethrowErrorService
    implements ErrorService
{

            public RethrowErrorService()
            {
            }

            public void onFailure(ErrorInformation errorinformation)
                throws MultiException
            {
/*  74*/        if(ErrorType.FAILURE_TO_REIFY.equals(errorinformation.getErrorType()))
                {
/*  75*/            if((errorinformation = errorinformation.getAssociatedException()) == null)
/*  76*/                return;
/*  78*/            else
/*  78*/                throw errorinformation;
                } else
                {
/*  80*/            return;
                }
            }
}
