// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MessageBodyFactory.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.internal.util.collection.KeyComparator;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            MessageBodyFactory

static class mediaType
{

            public boolean equals(Object obj)
            {
/* 485*/        if(this == obj)
/* 486*/            return true;
/* 488*/        if(obj == null || getClass() != obj.getClass())
/* 489*/            return false;
/* 492*/        obj = (mediaType)obj;
/* 494*/        return (clazz == null ? ((clazz) (obj)).clazz == null : clazz.equals(((clazz) (obj)).clazz)) && (mediaType == null ? ((mediaType) (obj)).mediaType == null : mediaType.equals(((mediaType) (obj)).mediaType));
            }

            public int hashCode()
            {
/* 500*/        int i = clazz == null ? 0 : clazz.hashCode();
/* 501*/        return i = i * 31 + (mediaType == null ? 0 : mediaType.hashCode());
            }

            final Class clazz;
            final MediaType mediaType;

            private (Class class1, MediaType mediatype)
            {
/* 479*/        clazz = class1;
/* 480*/        mediaType = mediatype;
            }


            // Unreferenced inner class org/glassfish/jersey/message/internal/MessageBodyFactory$1

/* anonymous class */
    static class MessageBodyFactory._cls1
        implements KeyComparator
    {

                public final boolean equals(MediaType mediatype, MediaType mediatype1)
                {
/* 137*/            return mediatype.isCompatible(mediatype1);
                }

                public final int hash(MediaType mediatype)
                {
/* 143*/            return mediatype.getType().toLowerCase().hashCode() + mediatype.getSubtype().toLowerCase().hashCode();
                }

                public final volatile int hash(Object obj)
                {
/* 131*/            return hash((MediaType)obj);
                }

                public final volatile boolean equals(Object obj, Object obj1)
                {
/* 131*/            return equals((MediaType)obj, (MediaType)obj1);
                }

                private static final long serialVersionUID = 0x167019115f5932f3L;

    }

}
