// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CharMatcher.java

package jersey.repackaged.com.google.common.base;


// Referenced classes of package jersey.repackaged.com.google.common.base:
//            CharMatcher, Preconditions

static class stMatcher extends stMatcher
{

            public final boolean matches(char c)
            {
/* 331*/        return false;
            }

            public final int indexIn(CharSequence charsequence, int i)
            {
/* 340*/        charsequence = charsequence.length();
/* 341*/        Preconditions.checkPositionIndex(i, charsequence);
/* 342*/        return -1;
            }

            public final CharMatcher or(CharMatcher charmatcher)
            {
/* 401*/        return (CharMatcher)Preconditions.checkNotNull(charmatcher);
            }

            stMatcher(String s)
            {
/* 329*/        super(s);
            }
}
