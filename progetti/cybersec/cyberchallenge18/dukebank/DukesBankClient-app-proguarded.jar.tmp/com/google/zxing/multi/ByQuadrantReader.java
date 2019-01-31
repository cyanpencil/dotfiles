// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ByQuadrantReader.java

package com.google.zxing.multi;

import com.google.zxing.*;
import java.util.Map;

public final class ByQuadrantReader
    implements Reader
{

            public ByQuadrantReader(Reader reader)
            {
/*  43*/        _flddelegate = reader;
            }

            public final Result decode(BinaryBitmap binarybitmap)
                throws NotFoundException, ChecksumException, FormatException
            {
/*  49*/        return decode(binarybitmap, null);
            }

            public final Result decode(BinaryBitmap binarybitmap, Map map)
                throws NotFoundException, ChecksumException, FormatException
            {
                int i;
                int j;
                BinaryBitmap binarybitmap1;
/*  56*/        i = binarybitmap.getWidth();
/*  57*/        j = binarybitmap.getHeight();
/*  58*/        i /= 2;
/*  59*/        j /= 2;
/*  61*/        binarybitmap1 = binarybitmap.crop(0, 0, i, j);
/*  63*/        return _flddelegate.decode(binarybitmap1, map);
/*  64*/        JVM INSTR pop ;
/*  68*/        binarybitmap1 = binarybitmap.crop(i, 0, i, j);
/*  70*/        return _flddelegate.decode(binarybitmap1, map);
/*  71*/        JVM INSTR pop ;
/*  75*/        binarybitmap1 = binarybitmap.crop(0, j, i, j);
/*  77*/        return _flddelegate.decode(binarybitmap1, map);
/*  78*/        JVM INSTR pop ;
/*  82*/        binarybitmap1 = binarybitmap.crop(i, j, i, j);
/*  84*/        return _flddelegate.decode(binarybitmap1, map);
/*  85*/        JVM INSTR pop ;
/*  89*/        int k = i / 2;
/*  90*/        int l = j / 2;
/*  91*/        binarybitmap = binarybitmap.crop(k, l, i, j);
/*  92*/        return _flddelegate.decode(binarybitmap, map);
            }

            public final void reset()
            {
/*  97*/        _flddelegate.reset();
            }

            private final Reader _flddelegate;
}
