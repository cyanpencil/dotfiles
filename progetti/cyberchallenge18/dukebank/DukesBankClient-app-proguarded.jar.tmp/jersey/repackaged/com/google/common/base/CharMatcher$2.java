// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CharMatcher.java

package jersey.repackaged.com.google.common.base;


// Referenced classes of package jersey.repackaged.com.google.common.base:
//            CharMatcher

static class nit> extends CharMatcher
{

            public final boolean matches(char c)
            {
/* 160*/        return Character.isDigit(c);
            }

            public final volatile boolean apply(Object obj)
            {
/* 158*/        return super.apply((Character)obj);
            }

            (String s)
            {
/* 158*/        super(s);
            }
}
