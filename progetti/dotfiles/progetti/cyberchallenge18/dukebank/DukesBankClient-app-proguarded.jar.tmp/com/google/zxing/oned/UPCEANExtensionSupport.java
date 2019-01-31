// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UPCEANExtensionSupport.java

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANExtension2Support, UPCEANExtension5Support, UPCEANReader

final class UPCEANExtensionSupport
{

            UPCEANExtensionSupport()
            {
            }

            final Result decodeRow(int i, BitArray bitarray, int j)
                throws NotFoundException
            {
/*  32*/        j = UPCEANReader.findGuardPattern(bitarray, j, false, EXTENSION_START_PATTERN);
/*  34*/        return fiveSupport.decodeRow(i, bitarray, j);
/*  35*/        JVM INSTR pop ;
/*  36*/        return twoSupport.decodeRow(i, bitarray, j);
            }

            private static final int EXTENSION_START_PATTERN[] = {
/*  26*/        1, 1, 2
            };
            private final UPCEANExtension2Support twoSupport = new UPCEANExtension2Support();
            private final UPCEANExtension5Support fiveSupport = new UPCEANExtension5Support();

}
