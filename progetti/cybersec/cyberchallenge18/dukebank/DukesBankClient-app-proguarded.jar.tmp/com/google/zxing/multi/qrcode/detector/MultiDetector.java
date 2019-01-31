// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MultiDetector.java

package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.qrcode.detector.Detector;
import java.util.*;

// Referenced classes of package com.google.zxing.multi.qrcode.detector:
//            MultiFinderPatternFinder

public final class MultiDetector extends Detector
{

            public MultiDetector(BitMatrix bitmatrix)
            {
/*  44*/        super(bitmatrix);
            }

            public final DetectorResult[] detectMulti(Map map)
                throws NotFoundException
            {
/*  48*/        Object obj = getImage();
/*  49*/        ResultPointCallback resultpointcallback = map != null ? (ResultPointCallback)map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK) : null;
/*  51*/        if((map = ((MultiFinderPatternFinder) (obj = new MultiFinderPatternFinder(((BitMatrix) (obj)), resultpointcallback))).findMulti(map)).length == 0)
/*  55*/            throw NotFoundException.getNotFoundInstance();
/*  58*/        obj = new ArrayList();
/*  59*/        int i = (map = map).length;
/*  59*/        for(int j = 0; j < i; j++)
                {
/*  59*/            com.google.zxing.qrcode.detector.FinderPatternInfo finderpatterninfo = map[j];
/*  61*/            try
                    {
/*  61*/                ((List) (obj)).add(processFinderPatternInfo(finderpatterninfo));
                    }
/*  62*/            catch(ReaderException _ex) { }
                }

/*  66*/        if(((List) (obj)).isEmpty())
/*  67*/            return EMPTY_DETECTOR_RESULTS;
/*  69*/        else
/*  69*/            return (DetectorResult[])((List) (obj)).toArray(new DetectorResult[((List) (obj)).size()]);
            }

            private static final DetectorResult EMPTY_DETECTOR_RESULTS[] = new DetectorResult[0];

}
