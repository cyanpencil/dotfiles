// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Predicates.java

package jersey.repackaged.com.google.common.base;

import java.util.regex.Pattern;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Predicates

static class  extends 
{

            public String toString()
            {
/* 629*/        String s = String.valueOf(String.valueOf(pattern.pattern()));
/* 629*/        return (new StringBuilder(28 + s.length())).append("Predicates.containsPattern(").append(s).append(")").toString();
            }

            (String s)
            {
/* 625*/        super(Pattern.compile(s));
            }
}
