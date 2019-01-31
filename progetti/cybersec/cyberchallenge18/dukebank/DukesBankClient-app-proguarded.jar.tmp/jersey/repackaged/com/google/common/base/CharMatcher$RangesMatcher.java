// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CharMatcher.java

package jersey.repackaged.com.google.common.base;

import java.util.Arrays;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            CharMatcher, Preconditions

static class t extends CharMatcher
{

            public boolean matches(char c)
            {
                int i;
/* 121*/        if((i = Arrays.binarySearch(rangeStarts, c)) >= 0)
/* 123*/            return true;
/* 125*/        return (i = ~i - 1) >= 0 && c <= rangeEnds[i];
            }

            public volatile boolean apply(Object obj)
            {
/* 102*/        return super.apply((Character)obj);
            }

            private final char rangeStarts[];
            private final char rangeEnds[];

            (String s, char ac[], char ac1[])
            {
/* 107*/        super(s);
/* 108*/        rangeStarts = ac;
/* 109*/        rangeEnds = ac1;
/* 110*/        Preconditions.checkArgument(ac.length == ac1.length);
/* 111*/        for(s = 0; s < ac.length; s++)
                {
/* 112*/            Preconditions.checkArgument(ac[s] <= ac1[s]);
/* 113*/            if(s + 1 < ac.length)
/* 114*/                Preconditions.checkArgument(ac1[s] < ac[s + 1]);
                }

            }
}
