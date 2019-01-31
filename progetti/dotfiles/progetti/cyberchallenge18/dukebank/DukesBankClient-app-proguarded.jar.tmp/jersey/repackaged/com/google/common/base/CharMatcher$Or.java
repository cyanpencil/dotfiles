// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CharMatcher.java

package jersey.repackaged.com.google.common.base;


// Referenced classes of package jersey.repackaged.com.google.common.base:
//            CharMatcher, Preconditions

static class <init> extends CharMatcher
{

            public boolean matches(char c)
            {
/* 746*/        return first.matches(c) || second.matches(c);
            }

            CharMatcher withToString(String s)
            {
/* 751*/        return new <init>(first, second, s);
            }

            public volatile boolean apply(Object obj)
            {
/* 723*/        return super.apply((Character)obj);
            }

            final CharMatcher first;
            final CharMatcher second;

            (CharMatcher charmatcher, CharMatcher charmatcher1, String s)
            {
/* 728*/        super(s);
/* 729*/        first = (CharMatcher)Preconditions.checkNotNull(charmatcher);
/* 730*/        second = (CharMatcher)Preconditions.checkNotNull(charmatcher1);
            }

            heckNotNull(CharMatcher charmatcher, CharMatcher charmatcher1)
            {
/* 734*/        this(charmatcher, charmatcher1, (new StringBuilder(18 + (charmatcher = String.valueOf(String.valueOf(charmatcher))).length() + (charmatcher1 = String.valueOf(String.valueOf(charmatcher1))).length())).append("CharMatcher.or(").append(charmatcher).append(", ").append(charmatcher1).append(")").toString());
            }
}
