// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheBuilderSpec.java

package jersey.repackaged.com.google.common.cache;

import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            CacheBuilderSpec

static class  extends 
{

            protected void parseLong(CacheBuilderSpec cachebuilderspec, long l)
            {
/* 333*/        Preconditions.checkArgument(cachebuilderspec.maximumSize == null, "maximum size was already set to ", new Object[] {
/* 333*/            cachebuilderspec.maximumSize
                });
/* 335*/        Preconditions.checkArgument(cachebuilderspec.maximumWeight == null, "maximum weight was already set to ", new Object[] {
/* 335*/            cachebuilderspec.maximumWeight
                });
/* 337*/        cachebuilderspec.maximumSize = Long.valueOf(l);
            }

            ()
            {
            }
}
