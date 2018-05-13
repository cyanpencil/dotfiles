// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InboundMessageContext.java

package org.glassfish.jersey.message.internal;

import java.io.InputStream;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            EntityInputStream, InboundMessageContext

static class  extends EntityInputStream
{

            void setContent(InputStream inputstream, boolean flag)
            {
/* 134*/        buffered = flag;
/* 135*/        setWrappedStream(inputstream);
            }

            boolean hasContent()
            {
/* 139*/        return getWrappedStream() != InboundMessageContext.access$000();
            }

            boolean isBuffered()
            {
/* 143*/        return buffered;
            }

            public void close()
            {
/* 148*/        close(false);
            }

            void close(boolean flag)
            {
/* 152*/        if(buffered && !flag)
/* 153*/            return;
/* 156*/        super.close();
/* 158*/        buffered = false;
/* 159*/        setWrappedStream(null);
/* 160*/        return;
/* 158*/        flag;
/* 158*/        buffered = false;
/* 159*/        setWrappedStream(null);
/* 159*/        throw flag;
            }

            private boolean buffered;

            ()
            {
/* 130*/        super(InboundMessageContext.access$000());
            }
}
