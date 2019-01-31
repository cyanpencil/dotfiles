// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DecoderResult.java

package com.google.zxing.common;

import java.util.List;

public final class DecoderResult
{

            public DecoderResult(byte abyte0[], String s, List list, String s1)
            {
/*  44*/        this(abyte0, s, list, s1, -1, -1);
            }

            public DecoderResult(byte abyte0[], String s, List list, String s1, int i, int j)
            {
/*  53*/        rawBytes = abyte0;
/*  54*/        text = s;
/*  55*/        byteSegments = list;
/*  56*/        ecLevel = s1;
/*  57*/        structuredAppendParity = j;
/*  58*/        structuredAppendSequenceNumber = i;
            }

            public final byte[] getRawBytes()
            {
/*  62*/        return rawBytes;
            }

            public final String getText()
            {
/*  66*/        return text;
            }

            public final List getByteSegments()
            {
/*  70*/        return byteSegments;
            }

            public final String getECLevel()
            {
/*  74*/        return ecLevel;
            }

            public final Integer getErrorsCorrected()
            {
/*  78*/        return errorsCorrected;
            }

            public final void setErrorsCorrected(Integer integer)
            {
/*  82*/        errorsCorrected = integer;
            }

            public final Integer getErasures()
            {
/*  86*/        return erasures;
            }

            public final void setErasures(Integer integer)
            {
/*  90*/        erasures = integer;
            }

            public final Object getOther()
            {
/*  94*/        return other;
            }

            public final void setOther(Object obj)
            {
/*  98*/        other = obj;
            }

            public final boolean hasStructuredAppend()
            {
/* 102*/        return structuredAppendParity >= 0 && structuredAppendSequenceNumber >= 0;
            }

            public final int getStructuredAppendParity()
            {
/* 106*/        return structuredAppendParity;
            }

            public final int getStructuredAppendSequenceNumber()
            {
/* 110*/        return structuredAppendSequenceNumber;
            }

            private final byte rawBytes[];
            private final String text;
            private final List byteSegments;
            private final String ecLevel;
            private Integer errorsCorrected;
            private Integer erasures;
            private Object other;
            private final int structuredAppendParity;
            private final int structuredAppendSequenceNumber;
}
