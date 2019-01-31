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
/* 373*/        Preconditions.checkArgument(s1 == null, "key %s does not take values", new Object[] {
/* 373*/            s
                });
/* 374*/        Preconditions.checkArgument(cachebuilderspec.keyStrength == null, "%s was already set to %s", new Object[] {
/* 374*/            s, cachebuilderspec.keyStrength
                });
/* 375*/        cachebuilderspec.keyStrength = strength;
            }

            private final strength strength;

            public ( )
            {
/* 368*/        strength = ;
            }
}
