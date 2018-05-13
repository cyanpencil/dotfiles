// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MessageBodyFactory.java

package org.glassfish.jersey.message.internal;

import java.util.Comparator;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.internal.util.collection.KeyComparator;
import org.glassfish.jersey.message.AbstractEntityProviderModel;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            MediaTypes, MessageBodyFactory

static class arator
    implements Comparator
{

            public int compare(AbstractEntityProviderModel abstractentityprovidermodel, AbstractEntityProviderModel abstractentityprovidermodel1)
            {
/* 459*/        if(abstractentityprovidermodel.isCustom() ^ abstractentityprovidermodel1.isCustom())
/* 460*/            return !abstractentityprovidermodel.isCustom() ? 1 : -1;
/* 462*/        MediaType mediatype = (MediaType)abstractentityprovidermodel.declaredTypes().get(0);
/* 463*/        MediaType mediatype1 = (MediaType)abstractentityprovidermodel1.declaredTypes().get(0);
                int i;
/* 465*/        if((i = MediaTypes.PARTIAL_ORDER_COMPARATOR.compare(mediatype, mediatype1)) != 0 && !mediatype.isCompatible(mediatype1))
/* 467*/            return i;
/* 469*/        else
/* 469*/            return distanceComparator.compare(abstractentityprovidermodel.provider(), abstractentityprovidermodel1.provider());
            }

            public volatile int compare(Object obj, Object obj1)
            {
/* 448*/        return compare((AbstractEntityProviderModel)obj, (AbstractEntityProviderModel)obj1);
            }

            final arator distanceComparator;

            private arator(Class class1)
            {
/* 453*/        distanceComparator = new arator(class1);
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
