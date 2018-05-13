// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MessageBodyFactory.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.internal.util.collection.KeyComparator;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            MessageBodyFactory

static class 
    implements KeyComparator
{

            public final boolean equals(MediaType mediatype, MediaType mediatype1)
            {
/* 137*/        return mediatype.isCompatible(mediatype1);
            }

            public final int hash(MediaType mediatype)
            {
/* 143*/        return mediatype.getType().toLowerCase().hashCode() + mediatype.getSubtype().toLowerCase().hashCode();
            }

            public final volatile int hash(Object obj)
            {
/* 131*/        return hash((MediaType)obj);
            }

            public final volatile boolean equals(Object obj, Object obj1)
            {
/* 131*/        return equals((MediaType)obj, (MediaType)obj1);
            }

            private static final long serialVersionUID = 0x167019115f5932f3L;

            ()
            {
            }
}
