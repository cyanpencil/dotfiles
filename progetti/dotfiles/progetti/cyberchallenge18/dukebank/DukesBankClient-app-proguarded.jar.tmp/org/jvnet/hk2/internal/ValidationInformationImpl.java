// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ValidationInformationImpl.java

package org.jvnet.hk2.internal;

import java.util.HashSet;
import org.glassfish.hk2.api.*;

public class ValidationInformationImpl
    implements ValidationInformation
{

            public ValidationInformationImpl(Operation operation1, ActiveDescriptor activedescriptor, Injectee injectee1, Filter filter1)
            {
/*  99*/        operation = operation1;
/* 100*/        candidate = activedescriptor;
/* 101*/        injectee = injectee1;
/* 102*/        filter = filter1;
            }

            public ValidationInformationImpl(Operation operation1, ActiveDescriptor activedescriptor)
            {
/* 112*/        this(operation1, activedescriptor, null, null);
            }

            public Operation getOperation()
            {
/* 120*/        return operation;
            }

            public ActiveDescriptor getCandidate()
            {
/* 128*/        return candidate;
            }

            public Injectee getInjectee()
            {
/* 136*/        return injectee;
            }

            public Filter getFilter()
            {
/* 144*/        return filter;
            }

            private String getPackage(String s)
            {
                int i;
/* 148*/        if((i = s.lastIndexOf('.')) < 0)
/* 149*/            return s;
/* 151*/        else
/* 151*/            return s.substring(0, i);
            }

            public StackTraceElement getCaller()
            {
/* 162*/        StackTraceElement astacktraceelement[] = Thread.currentThread().getStackTrace();
/* 164*/        boolean flag = false;
/* 165*/        int i = (astacktraceelement = astacktraceelement).length;
/* 165*/        for(int j = 0; j < i; j++)
                {
/* 165*/            StackTraceElement stacktraceelement = astacktraceelement[j];
/* 166*/            if(!flag)
                    {
/* 167*/                if("org.jvnet.hk2.internal.ServiceLocatorImpl".equals(stacktraceelement.getClassName()) && ("validate".equals(stacktraceelement.getMethodName()) || "checkConfiguration".equals(stacktraceelement.getMethodName())))
/* 170*/                    flag = true;
/* 170*/                continue;
                    }
/* 174*/            String s = getPackage(stacktraceelement.getClassName());
/* 175*/            if(!PACKAGES_TO_SKIP.contains(s))
/* 175*/                return stacktraceelement;
                }

/* 179*/        return null;
            }

            public String toString()
            {
/* 183*/        return (new StringBuilder("ValidationInformation(")).append(operation).append(",").append(candidate).append(",").append(injectee).append(",").append(filter).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private static final String SERVICE_LOCATOR_IMPL = "org.jvnet.hk2.internal.ServiceLocatorImpl";
            private static final String VALIDATE_METHOD = "validate";
            private static final String CHECK_METHOD = "checkConfiguration";
            private static final String SKIP_ME[] = {
/*  60*/        "org.jvnet.hk2.internal", "org.jvnet.hk2.external.generator", "org.glassfish.hk2.extension", "org.glassfish.hk2.api", "org.glassfish.hk2.internal", "org.glassfish.hk2.utilities", "org.glassfish.hk2.utilities.binding", "org.jvnet.hk2.annotations", "org.glassfish.hk2.utilities.cache", "org.glassfish.hk2.utilities.cache.internal", 
/*  60*/        "org.glassfish.hk2.utilities.reflection", "org.jvnet.hk2.component", "java.util.concurrent"
            };
            private static final HashSet PACKAGES_TO_SKIP;
            private final Operation operation;
            private final ActiveDescriptor candidate;
            private final Injectee injectee;
            private final Filter filter;

            static 
            {
/*  76*/        PACKAGES_TO_SKIP = new HashSet();
                String as[];
/*  79*/        int i = (as = SKIP_ME).length;
/*  79*/        for(int j = 0; j < i; j++)
                {
/*  79*/            String s = as[j];
/*  80*/            PACKAGES_TO_SKIP.add(s);
                }

            }
}
