// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheBuilderSpec.java

package jersey.repackaged.com.google.common.cache;

import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            CacheBuilderSpec

static abstract class 
    implements 
{

            protected abstract void parseLong(CacheBuilderSpec cachebuilderspec, long l);

            public void parse(CacheBuilderSpec cachebuilderspec, String s, String s1)
            {
/* 309*/        Preconditions.checkArgument(s1 != null && !s1.isEmpty(), "value of key %s omitted", new Object[] {
/* 309*/            s
                });
/* 311*/        try
                {
/* 311*/            parseLong(cachebuilderspec, Long.parseLong(s1));
/* 315*/            return;
                }
                // Misplaced declaration of an exception variable
/* 312*/        catch(CacheBuilderSpec cachebuilderspec)
                {
/* 313*/            throw new IllegalArgumentException(String.format("key %s value set to %s, must be integer", new Object[] {
/* 313*/                s, s1
                    }), cachebuilderspec);
                }
            }

            ()
            {
            }
}
