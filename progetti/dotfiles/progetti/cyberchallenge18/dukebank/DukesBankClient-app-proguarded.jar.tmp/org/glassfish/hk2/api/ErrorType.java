// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ErrorType.java

package org.glassfish.hk2.api;


public final class ErrorType extends Enum
{

            public static ErrorType[] values()
            {
/*  49*/        return (ErrorType[])$VALUES.clone();
            }

            public static ErrorType valueOf(String s)
            {
/*  49*/        return (ErrorType)Enum.valueOf(org/glassfish/hk2/api/ErrorType, s);
            }

            private ErrorType(String s, int i)
            {
/*  49*/        super(s, i);
            }

            public static final ErrorType FAILURE_TO_REIFY;
            public static final ErrorType DYNAMIC_CONFIGURATION_FAILURE;
            public static final ErrorType SERVICE_CREATION_FAILURE;
            public static final ErrorType SERVICE_DESTRUCTION_FAILURE;
            public static final ErrorType VALIDATE_FAILURE;
            private static final ErrorType $VALUES[];

            static 
            {
/*  53*/        FAILURE_TO_REIFY = new ErrorType("FAILURE_TO_REIFY", 0);
/*  58*/        DYNAMIC_CONFIGURATION_FAILURE = new ErrorType("DYNAMIC_CONFIGURATION_FAILURE", 1);
/*  63*/        SERVICE_CREATION_FAILURE = new ErrorType("SERVICE_CREATION_FAILURE", 2);
/*  68*/        SERVICE_DESTRUCTION_FAILURE = new ErrorType("SERVICE_DESTRUCTION_FAILURE", 3);
/*  73*/        VALIDATE_FAILURE = new ErrorType("VALIDATE_FAILURE", 4);
/*  49*/        $VALUES = (new ErrorType[] {
/*  49*/            FAILURE_TO_REIFY, DYNAMIC_CONFIGURATION_FAILURE, SERVICE_CREATION_FAILURE, SERVICE_DESTRUCTION_FAILURE, VALIDATE_FAILURE
                });
            }
}
