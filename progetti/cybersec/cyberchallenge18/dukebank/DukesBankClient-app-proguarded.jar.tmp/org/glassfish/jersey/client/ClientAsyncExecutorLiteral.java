// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientAsyncExecutorLiteral.java

package org.glassfish.jersey.client;

import org.glassfish.hk2.api.AnnotationLiteral;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientAsyncExecutor

public final class ClientAsyncExecutorLiteral extends AnnotationLiteral
    implements ClientAsyncExecutor
{

            private ClientAsyncExecutorLiteral()
            {
            }

            public static final ClientAsyncExecutor INSTANCE = new ClientAsyncExecutorLiteral();

}
