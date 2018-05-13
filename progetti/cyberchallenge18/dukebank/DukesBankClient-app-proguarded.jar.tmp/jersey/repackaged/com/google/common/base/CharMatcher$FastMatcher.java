// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CharMatcher.java

package jersey.repackaged.com.google.common.base;


// Referenced classes of package jersey.repackaged.com.google.common.base:
//            CharMatcher

static abstract class  extends CharMatcher
{

            public volatile boolean apply(Object obj)
            {
/* 813*/        return super.apply((Character)obj);
            }

            (String s)
            {
/* 819*/        super(s);
            }
}
