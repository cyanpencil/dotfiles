// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Writer.java

package com.google.zxing;

import com.google.zxing.common.BitMatrix;
import java.util.Map;

// Referenced classes of package com.google.zxing:
//            WriterException, BarcodeFormat

public interface Writer
{

    public abstract BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
        throws WriterException;

    public abstract BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
        throws WriterException;
}
