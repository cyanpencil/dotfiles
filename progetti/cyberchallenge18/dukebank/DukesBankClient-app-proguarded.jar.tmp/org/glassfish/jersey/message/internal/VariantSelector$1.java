// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   VariantSelector.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Variant;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AcceptableMediaType, VariantSelector

static class riantHolder
    implements mensionChecker
{

            public final MediaType getDimension(riantHolder riantholder)
            {
/* 115*/        return riantHolder.access._mth000(riantholder).getMediaType();
            }

            public final boolean isCompatible(AcceptableMediaType acceptablemediatype, MediaType mediatype)
            {
/* 120*/        return acceptablemediatype.isCompatible(mediatype);
            }

            public final int getQualitySource(riantHolder riantholder, MediaType mediatype)
            {
/* 125*/        return riantHolder.access._mth100(riantholder);
            }

            public final String getVaryHeaderValue()
            {
/* 130*/        return "Accept";
            }

            public final volatile boolean isCompatible(Object obj, Object obj1)
            {
/* 111*/        return isCompatible((AcceptableMediaType)obj, (MediaType)obj1);
            }

            public final volatile int getQualitySource(riantHolder riantholder, Object obj)
            {
/* 111*/        return getQualitySource(riantholder, (MediaType)obj);
            }

            public final volatile Object getDimension(riantHolder riantholder)
            {
/* 111*/        return getDimension(riantholder);
            }

            riantHolder()
            {
            }
}
