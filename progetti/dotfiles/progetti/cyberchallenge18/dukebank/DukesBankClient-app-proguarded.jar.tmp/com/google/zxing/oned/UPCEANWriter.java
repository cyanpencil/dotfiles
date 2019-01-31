// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UPCEANWriter.java

package com.google.zxing.oned;


// Referenced classes of package com.google.zxing.oned:
//            OneDimensionalCodeWriter, UPCEANReader

public abstract class UPCEANWriter extends OneDimensionalCodeWriter
{

            public UPCEANWriter()
            {
            }

            public int getDefaultMargin()
            {
/*  31*/        return UPCEANReader.START_END_PATTERN.length;
            }
}
