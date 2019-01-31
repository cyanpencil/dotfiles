// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheBuilderSpec.java

package jersey.repackaged.com.google.common.cache;

import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            CacheBuilderSpec, LocalCache

static class strength
    implements strength
{

            public void parse(CacheBuilderSpec cachebuilderspec, String s, String s1)
            {
/* 389*/        Preconditions.checkArgument(s1 == null, "key %s does not take values", new Object[] {
/* 389*/            s
                });
/* 390*/        Preconditions.checkArgument(cachebuilderspec.valueStrength == null, "%s was already set to %s", new Object[] {
/* 390*/            s, cachebuilderspec.valueStrength
                });
/* 393*/        cachebuilderspec.valueStrength = strength;
            }

            private final strength strength;

            public ( )
            {
/* 384*/        strength = ;
            }
}
