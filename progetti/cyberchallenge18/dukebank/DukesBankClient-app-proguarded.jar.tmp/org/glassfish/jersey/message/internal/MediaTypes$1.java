// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MediaTypes.java

package org.glassfish.jersey.message.internal;

import java.util.Comparator;
import javax.ws.rs.core.MediaType;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            MediaTypes

static class 
    implements Comparator
{

            private int rank(MediaType mediatype)
            {
/*  92*/        return (mediatype.isWildcardType() ? 1 : 0) << 1 | (mediatype.isWildcardSubtype() ? 1 : 0);
            }

            public final int compare(MediaType mediatype, MediaType mediatype1)
            {
/*  97*/        return rank(mediatype) - rank(mediatype1);
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/*  88*/        return compare((MediaType)obj, (MediaType)obj1);
            }

            ()
            {
            }
}
