// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CommittingOutputStream.java

package org.glassfish.jersey.message.internal;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            CommittingOutputStream, NullOutputStream, OutboundMessageContext

static class reamProvider
    implements reamProvider
{

            public final OutputStream getOutputStream(int i)
                throws IOException
            {
/*  92*/        return new NullOutputStream();
            }

            reamProvider()
            {
            }
}
