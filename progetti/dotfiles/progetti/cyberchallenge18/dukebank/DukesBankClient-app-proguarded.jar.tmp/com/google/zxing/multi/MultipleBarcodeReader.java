// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MultipleBarcodeReader.java

package com.google.zxing.multi;

import com.google.zxing.*;
import java.util.Map;

public interface MultipleBarcodeReader
{

    public abstract Result[] decodeMultiple(BinaryBitmap binarybitmap)
        throws NotFoundException;

    public abstract Result[] decodeMultiple(BinaryBitmap binarybitmap, Map map)
        throws NotFoundException;
}
