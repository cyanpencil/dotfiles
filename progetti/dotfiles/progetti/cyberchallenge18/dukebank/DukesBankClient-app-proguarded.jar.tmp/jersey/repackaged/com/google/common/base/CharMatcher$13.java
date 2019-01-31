// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CharMatcher.java

package jersey.repackaged.com.google.common.base;


// Referenced classes of package jersey.repackaged.com.google.common.base:
//            CharMatcher

static class tMatcher extends tMatcher
{

            public final boolean matches(char c)
            {
/* 561*/        return val$startInclusive <= c && c <= val$endInclusive;
            }

            final char val$startInclusive;
            final char val$endInclusive;

            tMatcher(String s, char c, char c1)
            {
/* 559*/        val$startInclusive = c;
/* 559*/        val$endInclusive = c1;
/* 559*/        super(s);
            }
}
