// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   VariantSelector.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.core.Variant;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AcceptableToken, VariantSelector

static class riantHolder
    implements mensionChecker
{

            public final String getDimension(riantHolder riantholder)
            {
/* 185*/        return riantHolder.access._mth000(riantholder).getEncoding();
            }

            public final boolean isCompatible(AcceptableToken acceptabletoken, String s)
            {
/* 190*/        return acceptabletoken.isCompatible(s);
            }

            public final int getQualitySource(riantHolder riantholder, String s)
            {
/* 195*/        return 0;
            }

            public final String getVaryHeaderValue()
            {
/* 200*/        return "Accept-Encoding";
            }

            public final volatile boolean isCompatible(Object obj, Object obj1)
            {
/* 181*/        return isCompatible((AcceptableToken)obj, (String)obj1);
            }

            public final volatile int getQualitySource(riantHolder riantholder, Object obj)
            {
/* 181*/        return getQualitySource(riantholder, (String)obj);
            }

            public final volatile Object getDimension(riantHolder riantholder)
            {
/* 181*/        return getDimension(riantholder);
            }

            riantHolder()
            {
            }
}
