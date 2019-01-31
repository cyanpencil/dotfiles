// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   VariantSelector.java

package org.glassfish.jersey.message.internal;

import java.util.Map;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Variant;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AcceptableToken, VariantSelector

static class riantHolder
    implements mensionChecker
{

            public final String getDimension(riantHolder riantholder)
            {
/* 161*/        if((riantholder = riantHolder.access._mth000(riantholder).getMediaType()) != null)
/* 162*/            return (String)riantholder.getParameters().get("charset");
/* 162*/        else
/* 162*/            return null;
            }

            public final boolean isCompatible(AcceptableToken acceptabletoken, String s)
            {
/* 167*/        return acceptabletoken.isCompatible(s);
            }

            public final int getQualitySource(riantHolder riantholder, String s)
            {
/* 172*/        return 0;
            }

            public final String getVaryHeaderValue()
            {
/* 177*/        return "Accept-Charset";
            }

            public final volatile boolean isCompatible(Object obj, Object obj1)
            {
/* 157*/        return isCompatible((AcceptableToken)obj, (String)obj1);
            }

            public final volatile int getQualitySource(riantHolder riantholder, Object obj)
            {
/* 157*/        return getQualitySource(riantholder, (String)obj);
            }

            public final volatile Object getDimension(riantHolder riantholder)
            {
/* 157*/        return getDimension(riantholder);
            }

            riantHolder()
            {
            }
}
