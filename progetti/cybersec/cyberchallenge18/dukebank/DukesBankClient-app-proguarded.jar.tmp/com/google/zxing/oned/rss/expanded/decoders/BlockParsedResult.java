// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BlockParsedResult.java

package com.google.zxing.oned.rss.expanded.decoders;


// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            DecodedInformation

final class BlockParsedResult
{

            BlockParsedResult(boolean flag)
            {
/*  39*/        this(null, flag);
            }

            BlockParsedResult(DecodedInformation decodedinformation, boolean flag)
            {
/*  43*/        finished = flag;
/*  44*/        decodedInformation = decodedinformation;
            }

            final DecodedInformation getDecodedInformation()
            {
/*  48*/        return decodedInformation;
            }

            final boolean isFinished()
            {
/*  52*/        return finished;
            }

            private final DecodedInformation decodedInformation;
            private final boolean finished;
}
