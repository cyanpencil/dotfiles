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

            protected void parseInteger(CacheBuilderSpec cachebuilderspec, int i)
            {
/* 357*/        Preconditions.checkArgument(cachebuilderspec.concurrencyLevel == null, "concurrency level was already set to ", new Object[] {
/* 357*/            cachebuilderspec.concurrencyLevel
                });
/* 359*/        cachebuilderspec.concurrencyLevel = Integer.valueOf(i);
            }

            ()
            {
            }
}
