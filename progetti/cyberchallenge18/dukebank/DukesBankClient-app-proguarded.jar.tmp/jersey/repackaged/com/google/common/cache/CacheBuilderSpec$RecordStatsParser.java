// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheBuilderSpec.java

package jersey.repackaged.com.google.common.cache;

import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            CacheBuilderSpec

static class 
    implements 
{

            public void parse(CacheBuilderSpec cachebuilderspec, String s, String s1)
            {
/* 402*/        Preconditions.checkArgument(s1 == null, "recordStats does not take values");
/* 403*/        Preconditions.checkArgument(cachebuilderspec.recordStats == null, "recordStats already set");
/* 404*/        cachebuilderspec.recordStats = Boolean.valueOf(true);
            }

            ()
            {
            }
}
