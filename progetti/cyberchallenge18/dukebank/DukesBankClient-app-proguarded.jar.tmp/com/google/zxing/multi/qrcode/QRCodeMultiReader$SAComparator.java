// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   QRCodeMultiReader.java

package com.google.zxing.multi.qrcode;

import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

// Referenced classes of package com.google.zxing.multi.qrcode:
//            QRCodeMultiReader

static final class <init>
    implements Serializable, Comparator
{

            public final int compare(Result result, Result result1)
            {
/* 169*/        result = ((Integer)result.getResultMetadata().get(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)).intValue();
/* 170*/        result1 = ((Integer)result1.getResultMetadata().get(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)).intValue();
/* 171*/        if(result < result1)
/* 172*/            return -1;
/* 174*/        return result <= result1 ? 0 : 1;
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/* 166*/        return compare((Result)obj, (Result)obj1);
            }

            private ()
            {
            }

            ( )
            {
/* 166*/        this();
            }
}
