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

            protected abstract void parseInteger(CacheBuilderSpec cachebuilderspec, int i);

            public void parse(CacheBuilderSpec cachebuilderspec, String s, String s1)
            {
/* 293*/        Preconditions.checkArgument(s1 != null && !s1.isEmpty(), "value of key %s omitted", new Object[] {
/* 293*/            s
                });
/* 295*/        try
                {
/* 295*/            parseInteger(cachebuilderspec, Integer.parseInt(s1));
/* 299*/            return;
                }
                // Misplaced declaration of an exception variable
/* 296*/        catch(CacheBuilderSpec cachebuilderspec)
                {
/* 297*/            throw new IllegalArgumentException(String.format("key %s value set to %s, must be integer", new Object[] {
/* 297*/                s, s1
                    }), cachebuilderspec);
                }
            }

            ()
            {
            }
}
