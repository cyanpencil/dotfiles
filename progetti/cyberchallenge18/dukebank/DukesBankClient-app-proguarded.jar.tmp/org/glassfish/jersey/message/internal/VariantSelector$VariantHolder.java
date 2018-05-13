// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   VariantSelector.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.core.Variant;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            VariantSelector

static class mediaTypeQs
{

            private final Variant v;
            private final int mediaTypeQs;



            public I(Variant variant)
            {
/* 279*/        this(variant, 1000);
            }

            public <init>(Variant variant, int i)
            {
/* 283*/        v = variant;
/* 284*/        mediaTypeQs = i;
            }
}
