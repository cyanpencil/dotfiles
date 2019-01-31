// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EncoderContext.java

package com.google.zxing.datamatrix.encoder;

import com.google.zxing.Dimension;
import java.nio.charset.Charset;

// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            SymbolInfo, SymbolShapeHint

final class EncoderContext
{

            EncoderContext(String s)
            {
/*  37*/        byte abyte0[] = s.getBytes(Charset.forName("ISO-8859-1"));
/*  38*/        StringBuilder stringbuilder = new StringBuilder(abyte0.length);
/*  39*/        int i = 0;
/*  39*/        for(int j = abyte0.length; i < j; i++)
                {
                    char c;
/*  40*/            if((c = (char)(abyte0[i] & 0xff)) == '?' && s.charAt(i) != '?')
/*  42*/                throw new IllegalArgumentException("Message contains characters outside ISO-8859-1 encoding.");
/*  44*/            stringbuilder.append(c);
                }

/*  46*/        msg = stringbuilder.toString();
/*  47*/        shape = SymbolShapeHint.FORCE_NONE;
/*  48*/        codewords = new StringBuilder(s.length());
/*  49*/        newEncoding = -1;
            }

            public final void setSymbolShape(SymbolShapeHint symbolshapehint)
            {
/*  53*/        shape = symbolshapehint;
            }

            public final void setSizeConstraints(Dimension dimension, Dimension dimension1)
            {
/*  57*/        minSize = dimension;
/*  58*/        maxSize = dimension1;
            }

            public final String getMessage()
            {
/*  62*/        return msg;
            }

            public final void setSkipAtEnd(int i)
            {
/*  66*/        skipAtEnd = i;
            }

            public final char getCurrentChar()
            {
/*  70*/        return msg.charAt(pos);
            }

            public final char getCurrent()
            {
/*  74*/        return msg.charAt(pos);
            }

            public final StringBuilder getCodewords()
            {
/*  78*/        return codewords;
            }

            public final void writeCodewords(String s)
            {
/*  82*/        codewords.append(s);
            }

            public final void writeCodeword(char c)
            {
/*  86*/        codewords.append(c);
            }

            public final int getCodewordCount()
            {
/*  90*/        return codewords.length();
            }

            public final int getNewEncoding()
            {
/*  94*/        return newEncoding;
            }

            public final void signalEncoderChange(int i)
            {
/*  98*/        newEncoding = i;
            }

            public final void resetEncoderSignal()
            {
/* 102*/        newEncoding = -1;
            }

            public final boolean hasMoreCharacters()
            {
/* 106*/        return pos < getTotalMessageCharCount();
            }

            private int getTotalMessageCharCount()
            {
/* 110*/        return msg.length() - skipAtEnd;
            }

            public final int getRemainingCharacters()
            {
/* 114*/        return getTotalMessageCharCount() - pos;
            }

            public final SymbolInfo getSymbolInfo()
            {
/* 118*/        return symbolInfo;
            }

            public final void updateSymbolInfo()
            {
/* 122*/        updateSymbolInfo(getCodewordCount());
            }

            public final void updateSymbolInfo(int i)
            {
/* 126*/        if(symbolInfo == null || i > symbolInfo.getDataCapacity())
/* 127*/            symbolInfo = SymbolInfo.lookup(i, shape, minSize, maxSize, true);
            }

            public final void resetSymbolInfo()
            {
/* 132*/        symbolInfo = null;
            }

            private final String msg;
            private SymbolShapeHint shape;
            private Dimension minSize;
            private Dimension maxSize;
            private final StringBuilder codewords;
            int pos;
            private int newEncoding;
            private SymbolInfo symbolInfo;
            private int skipAtEnd;
}
