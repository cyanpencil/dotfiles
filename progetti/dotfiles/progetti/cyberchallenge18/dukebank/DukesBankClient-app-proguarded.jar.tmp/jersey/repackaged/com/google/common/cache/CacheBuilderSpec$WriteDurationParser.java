// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheBuilderSpec.java

package jersey.repackaged.com.google.common.cache;

import java.util.concurrent.TimeUnit;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            CacheBuilderSpec

static class  extends 
{

            protected void parseDuration(CacheBuilderSpec cachebuilderspec, long l, TimeUnit timeunit)
            {
/* 461*/        Preconditions.checkArgument(cachebuilderspec.writeExpirationTimeUnit == null, "expireAfterWrite already set");
/* 462*/        cachebuilderspec.writeExpirationDuration = l;
/* 463*/        cachebuilderspec.writeExpirationTimeUnit = timeunit;
            }

            ()
            {
            }
}
