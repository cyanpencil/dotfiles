// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PatternWithGroups.java

package org.glassfish.jersey.uri;

import java.util.regex.MatchResult;

// Referenced classes of package org.glassfish.jersey.uri:
//            PatternWithGroups

final class result
    implements MatchResult
{

            public final int start()
            {
/* 223*/        return result.start();
            }

            public final int start(int i)
            {
/* 228*/        if(i > groupCount())
/* 229*/            throw new IndexOutOfBoundsException();
/* 232*/        if(i > 0)
/* 232*/            return result.start(PatternWithGroups.access$100(PatternWithGroups.this)[i - 1]);
/* 232*/        else
/* 232*/            return result.start();
            }

            public final int end()
            {
/* 237*/        return result.end();
            }

            public final int end(int i)
            {
/* 242*/        if(i > groupCount())
/* 243*/            throw new IndexOutOfBoundsException();
/* 246*/        if(i > 0)
/* 246*/            return result.end(PatternWithGroups.access$100(PatternWithGroups.this)[i - 1]);
/* 246*/        else
/* 246*/            return result.end();
            }

            public final String group()
            {
/* 251*/        return result.group();
            }

            public final String group(int i)
            {
/* 256*/        if(i > groupCount())
/* 257*/            throw new IndexOutOfBoundsException();
/* 260*/        if(i > 0)
/* 260*/            return result.group(PatternWithGroups.access$100(PatternWithGroups.this)[i - 1]);
/* 260*/        else
/* 260*/            return result.group();
            }

            public final int groupCount()
            {
/* 265*/        return PatternWithGroups.access$100(PatternWithGroups.this).length;
            }

            private final MatchResult result;
            final PatternWithGroups this$0;

            (MatchResult matchresult)
            {
/* 217*/        this$0 = PatternWithGroups.this;
/* 217*/        super();
/* 218*/        result = matchresult;
            }
}
