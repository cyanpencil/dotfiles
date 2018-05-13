// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Reader.java

package com.google.zxing;

import java.util.Map;

// Referenced classes of package com.google.zxing:
//            ChecksumException, FormatException, NotFoundException, BinaryBitmap, 
//            Result

public interface Reader
{

    public abstract Result decode(BinaryBitmap binarybitmap)
        throws NotFoundException, ChecksumException, FormatException;

    public abstract Result decode(BinaryBitmap binarybitmap, Map map)
        throws NotFoundException, ChecksumException, FormatException;

    public abstract void reset();
}
