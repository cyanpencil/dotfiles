// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheBuilderSpec.java

package jersey.repackaged.com.google.common.cache;

import java.util.concurrent.TimeUnit;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            CacheBuilderSpec

static abstract class 
    implements 
{

            protected abstract void parseDuration(CacheBuilderSpec cachebuilderspec, long l, TimeUnit timeunit);

            public void parse(CacheBuilderSpec cachebuilderspec, String s, String s1)
            {
/* 417*/        Preconditions.checkArgument(s1 != null && !s1.isEmpty(), "value of key %s omitted", new Object[] {
/* 417*/            s
                });
/* 419*/        try
                {
                    char c;
                    TimeUnit timeunit;
/* 419*/            switch(c = s1.charAt(s1.length() - 1))
                    {
/* 423*/            case 100: // 'd'
/* 423*/                timeunit = TimeUnit.DAYS;
                        break;

/* 426*/            case 104: // 'h'
/* 426*/                timeunit = TimeUnit.HOURS;
                        break;

/* 429*/            case 109: // 'm'
/* 429*/                timeunit = TimeUnit.MINUTES;
                        break;

/* 432*/            case 115: // 's'
/* 432*/                timeunit = TimeUnit.SECONDS;
                        break;

/* 435*/            default:
/* 435*/                throw new IllegalArgumentException(String.format("key %s invalid format.  was %s, must end with one of [dDhHmMsS]", new Object[] {
/* 435*/                    s, s1
                        }));
                    }
/* 440*/            long l = Long.parseLong(s1.substring(0, s1.length() - 1));
/* 441*/            parseDuration(cachebuilderspec, l, timeunit);
/* 445*/            return;
                }
/* 442*/        catch(NumberFormatException _ex)
                {
/* 443*/            throw new IllegalArgumentException(String.format("key %s value set to %s, must be integer", new Object[] {
/* 443*/                s, s1
                    }));
                }
            }

            ()
            {
            }
}
