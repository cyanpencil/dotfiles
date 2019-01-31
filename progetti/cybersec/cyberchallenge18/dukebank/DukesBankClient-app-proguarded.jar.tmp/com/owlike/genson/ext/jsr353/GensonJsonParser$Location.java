// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonJsonParser.java

package com.owlike.genson.ext.jsr353;

import javax.json.stream.JsonLocation;

// Referenced classes of package com.owlike.genson.ext.jsr353:
//            GensonJsonParser

static class columnNumber
    implements JsonLocation
{

            public long getStreamOffset()
            {
/* 179*/        return -1L;
            }

            public long getLineNumber()
            {
/* 184*/        return lineNumber;
            }

            public long getColumnNumber()
            {
/* 189*/        return columnNumber;
            }

            final long lineNumber;
            final long columnNumber;

            public (long l, long l1)
            {
/* 173*/        lineNumber = l;
/* 174*/        columnNumber = l1;
            }
}
