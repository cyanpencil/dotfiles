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
/* 255*/        return true;
            }

            public final int indexIn(CharSequence charsequence, int i)
            {
/* 263*/        charsequence = charsequence.length();
/* 264*/        Preconditions.checkPositionIndex(i, charsequence);
/* 265*/        if(i == charsequence)
/* 265*/            return -1;
/* 265*/        else
/* 265*/            return i;
            }

            public final CharMatcher or(CharMatcher charmatcher)
            {
/* 318*/        Preconditions.checkNotNull(charmatcher);
/* 319*/        return this;
            }

            stMatcher(String s)
            {
/* 253*/        super(s);
            }
}
