// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CharMatcher.java

package jersey.repackaged.com.google.common.base;


// Referenced classes of package jersey.repackaged.com.google.common.base:
//            CharMatcher

static class stMatcher extends stMatcher
{

            public final boolean matches(char c)
            {
/* 418*/        return c == val$match;
            }

            public final CharMatcher or(CharMatcher charmatcher)
            {
/* 430*/        if(charmatcher.matches(val$match))
/* 430*/            return charmatcher;
/* 430*/        else
/* 430*/            return super.or(charmatcher);
            }

            final char val$match;

            stMatcher(String s, char c)
            {
/* 416*/        val$match = c;
/* 416*/        super(s);
            }
}
