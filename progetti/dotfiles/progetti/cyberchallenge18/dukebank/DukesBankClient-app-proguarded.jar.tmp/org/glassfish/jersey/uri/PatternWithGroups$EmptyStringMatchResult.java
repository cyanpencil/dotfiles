// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PatternWithGroups.java

package org.glassfish.jersey.uri;

import java.util.regex.MatchResult;

// Referenced classes of package org.glassfish.jersey.uri:
//            PatternWithGroups

static final class 
    implements MatchResult
{

            public final int start()
            {
/* 168*/        return 0;
            }

            public final int start(int i)
            {
/* 173*/        if(i != 0)
/* 174*/            throw new IndexOutOfBoundsException();
/* 176*/        else
/* 176*/            return start();
            }

            public final int end()
            {
/* 181*/        return 0;
            }

            public final int end(int i)
            {
/* 186*/        if(i != 0)
/* 187*/            throw new IndexOutOfBoundsException();
/* 189*/        else
/* 189*/            return end();
            }

            public final String group()
            {
/* 194*/        return "";
            }

            public final String group(int i)
            {
/* 199*/        if(i != 0)
/* 200*/            throw new IndexOutOfBoundsException();
/* 202*/        else
/* 202*/            return group();
            }

            public final int groupCount()
            {
/* 207*/        return 0;
            }

            private ()
            {
            }

}
