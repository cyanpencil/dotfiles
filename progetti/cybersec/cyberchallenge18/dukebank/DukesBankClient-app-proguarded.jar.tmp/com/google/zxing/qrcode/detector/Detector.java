// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Detector.java

package com.google.zxing.qrcode.detector;

import com.google.zxing.*;
import com.google.zxing.common.*;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.qrcode.decoder.Version;
import java.util.Map;

// Referenced classes of package com.google.zxing.qrcode.detector:
//            AlignmentPattern, AlignmentPatternFinder, FinderPattern, FinderPatternFinder, 
//            FinderPatternInfo

public class Detector
{

            public Detector(BitMatrix bitmatrix)
            {
/*  45*/        image = bitmatrix;
            }

            protected final BitMatrix getImage()
            {
/*  49*/        return image;
            }

            protected final ResultPointCallback getResultPointCallback()
            {
/*  53*/        return resultPointCallback;
            }

            public DetectorResult detect()
                throws NotFoundException, FormatException
            {
/*  63*/        return detect(null);
            }

            public final DetectorResult detect(Map map)
                throws NotFoundException, FormatException
            {
/*  76*/        resultPointCallback = map != null ? (ResultPointCallback)map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK) : null;
                FinderPatternFinder finderpatternfinder;
/*  79*/        map = (finderpatternfinder = new FinderPatternFinder(image, resultPointCallback)).find(map);
/*  82*/        return processFinderPatternInfo(map);
            }

            protected final DetectorResult processFinderPatternInfo(FinderPatternInfo finderpatterninfo)
                throws NotFoundException, FormatException
            {
/*  88*/        FinderPattern finderpattern = finderpatterninfo.getTopLeft();
/*  89*/        FinderPattern finderpattern1 = finderpatterninfo.getTopRight();
/*  90*/        finderpatterninfo = finderpatterninfo.getBottomLeft();
                float f;
/*  92*/        if((f = calculateModuleSize(finderpattern, finderpattern1, finderpatterninfo)) < 1.0F)
/*  94*/            throw NotFoundException.getNotFoundInstance();
                int i;
                Object obj;
/*  96*/        float f2 = ((Version) (obj = Version.getProvisionalVersionForDimension(i = computeDimension(finderpattern, finderpattern1, finderpatterninfo, f)))).getDimensionForVersion() - 7;
/* 100*/        AlignmentPattern alignmentpattern = null;
/* 102*/        if(((Version) (obj)).getAlignmentPatternCenters().length > 0)
                {
/* 105*/            float f1 = (finderpattern1.getX() - finderpattern.getX()) + finderpatterninfo.getX();
/* 106*/            float f3 = (finderpattern1.getY() - finderpattern.getY()) + finderpatterninfo.getY();
/* 110*/            f2 = 1.0F - 3F / (float)f2;
/* 111*/            f1 = (int)(finderpattern.getX() + f2 * (f1 - finderpattern.getX()));
/* 112*/            f2 = (int)(finderpattern.getY() + f2 * (f3 - finderpattern.getY()));
/* 115*/            f3 = 4;
/* 115*/            do
                    {
/* 115*/                if(f3 > 16)
/* 117*/                    break;
/* 117*/                try
                        {
/* 117*/                    alignmentpattern = findAlignmentInRegion(f, f1, f2, f3);
/* 121*/                    break;
                        }
/* 122*/                catch(NotFoundException _ex)
                        {
/* 115*/                    f3 <<= 1;
                        }
                    } while(true);
                }
/* 129*/        f1 = createTransform(finderpattern, finderpattern1, finderpatterninfo, alignmentpattern, i);
/* 132*/        BitMatrix bitmatrix = sampleGrid(image, f1, i);
                ResultPoint aresultpoint[];
/* 135*/        if(alignmentpattern == null)
/* 136*/            aresultpoint = (new ResultPoint[] {
/* 136*/                finderpatterninfo, finderpattern, finderpattern1
                    });
/* 138*/        else
/* 138*/            aresultpoint = (new ResultPoint[] {
/* 138*/                finderpatterninfo, finderpattern, finderpattern1, alignmentpattern
                    });
/* 140*/        return new DetectorResult(bitmatrix, aresultpoint);
            }

            private static PerspectiveTransform createTransform(ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3, int i)
            {
/* 148*/        i = (float)i - 3.5F;
                float f;
                float f1;
                float f2;
/* 153*/        if(resultpoint3 != null)
                {
/* 154*/            f = resultpoint3.getX();
/* 155*/            resultpoint3 = resultpoint3.getY();
/* 156*/            f2 = f1 = i - 3F;
                } else
                {
/* 160*/            f = (resultpoint1.getX() - resultpoint.getX()) + resultpoint2.getX();
/* 161*/            resultpoint3 = (resultpoint1.getY() - resultpoint.getY()) + resultpoint2.getY();
/* 162*/            f1 = i;
/* 163*/            f2 = i;
                }
/* 166*/        return PerspectiveTransform.quadrilateralToQuadrilateral(3.5F, 3.5F, i, 3.5F, f1, f2, 3.5F, i, resultpoint.getX(), resultpoint.getY(), resultpoint1.getX(), resultpoint1.getY(), f, resultpoint3, resultpoint2.getX(), resultpoint2.getY());
            }

            private static BitMatrix sampleGrid(BitMatrix bitmatrix, PerspectiveTransform perspectivetransform, int i)
                throws NotFoundException
            {
                GridSampler gridsampler;
/* 189*/        return (gridsampler = GridSampler.getInstance()).sampleGrid(bitmatrix, i, i, perspectivetransform);
            }

            private static int computeDimension(ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, float f)
                throws NotFoundException
            {
/* 201*/        resultpoint1 = MathUtils.round(ResultPoint.distance(resultpoint, resultpoint1) / f);
/* 202*/        resultpoint = MathUtils.round(ResultPoint.distance(resultpoint, resultpoint2) / f);
/* 203*/        switch((resultpoint = (resultpoint1 + resultpoint >> 1) + 7) & 3)
                {
/* 206*/        case 0: // '\0'
/* 206*/            resultpoint++;
                    break;

/* 210*/        case 2: // '\002'
/* 210*/            resultpoint--;
                    break;

/* 213*/        case 3: // '\003'
/* 213*/            throw NotFoundException.getNotFoundInstance();
                }
/* 215*/        return resultpoint;
            }

            protected final float calculateModuleSize(ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2)
            {
/* 226*/        return (calculateModuleSizeOneWay(resultpoint, resultpoint1) + calculateModuleSizeOneWay(resultpoint, resultpoint2)) / 2.0F;
            }

            private float calculateModuleSizeOneWay(ResultPoint resultpoint, ResultPoint resultpoint1)
            {
/* 236*/        float f = sizeOfBlackWhiteBlackRunBothWays((int)resultpoint.getX(), (int)resultpoint.getY(), (int)resultpoint1.getX(), (int)resultpoint1.getY());
/* 240*/        resultpoint = sizeOfBlackWhiteBlackRunBothWays((int)resultpoint1.getX(), (int)resultpoint1.getY(), (int)resultpoint.getX(), (int)resultpoint.getY());
/* 244*/        if(Float.isNaN(f))
/* 245*/            return resultpoint / 7F;
/* 247*/        if(Float.isNaN(resultpoint))
/* 248*/            return f / 7F;
/* 252*/        else
/* 252*/            return (f + resultpoint) / 14F;
            }

            private float sizeOfBlackWhiteBlackRunBothWays(int i, int j, int k, int l)
            {
/* 262*/        float f = sizeOfBlackWhiteBlackRun(i, j, k, l);
/* 265*/        float f1 = 1.0F;
/* 266*/        if((k = i - (k - i)) < 0)
                {
/* 268*/            f1 = (float)i / (float)(i - k);
/* 269*/            k = 0;
                } else
/* 270*/        if(k >= image.getWidth())
                {
/* 271*/            f1 = (float)(image.getWidth() - 1 - i) / (float)(k - i);
/* 272*/            k = image.getWidth() - 1;
                }
/* 274*/        l = (int)((float)j - (float)(l - j) * f1);
/* 276*/        f1 = 1.0F;
/* 277*/        if(l < 0)
                {
/* 278*/            f1 = (float)j / (float)(j - l);
/* 279*/            l = 0;
                } else
/* 280*/        if(l >= image.getHeight())
                {
/* 281*/            f1 = (float)(image.getHeight() - 1 - j) / (float)(l - j);
/* 282*/            l = image.getHeight() - 1;
                }
/* 284*/        k = (int)((float)i + (float)(k - i) * f1);
/* 286*/        return (f += sizeOfBlackWhiteBlackRun(i, j, k, l)) - 1.0F;
            }

            private float sizeOfBlackWhiteBlackRun(int i, int j, int k, int l)
            {
                boolean flag;
/* 303*/        if(flag = Math.abs(l - j) > Math.abs(k - i))
                {
/* 305*/            int i1 = i;
/* 306*/            i = j;
/* 307*/            j = i1;
/* 308*/            i1 = k;
/* 309*/            k = l;
/* 310*/            l = i1;
                }
/* 313*/        int j1 = Math.abs(k - i);
/* 314*/        int k1 = Math.abs(l - j);
/* 315*/        int l1 = -j1 >> 1;
/* 316*/        byte byte0 = ((byte)(i >= k ? -1 : 1));
/* 317*/        byte byte1 = ((byte)(j >= l ? -1 : 1));
/* 320*/        int i2 = 0;
/* 322*/        int j2 = k + byte0;
/* 323*/        int k2 = i;
/* 323*/        int l2 = j;
/* 323*/        for(; k2 != j2; k2 += byte0)
                {
/* 324*/            int i3 = flag ? l2 : k2;
/* 325*/            int j3 = flag ? k2 : l2;
/* 330*/            if((i2 == 1) == image.get(i3, j3))
                    {
/* 331*/                if(i2 == 2)
/* 332*/                    return MathUtils.distance(k2, l2, i, j);
/* 334*/                i2++;
                    }
/* 337*/            if((l1 += k1) <= 0)
/* 339*/                continue;
/* 339*/            if(l2 == l)
/* 342*/                break;
/* 342*/            l2 += byte1;
/* 343*/            l1 -= j1;
                }

/* 349*/        if(i2 == 2)
/* 350*/            return MathUtils.distance(k + byte0, l, i, j);
/* 353*/        else
/* 353*/            return (0.0F / 0.0F);
            }

            protected final AlignmentPattern findAlignmentInRegion(float f, int i, int j, float f1)
                throws NotFoundException
            {
/* 374*/        f1 = (int)(f1 * f);
/* 375*/        int k = Math.max(0, i - f1);
/* 376*/        if((float)((i = Math.min(image.getWidth() - 1, i + f1)) - k) < f * 3F)
/* 378*/            throw NotFoundException.getNotFoundInstance();
/* 381*/        int l = Math.max(0, j - f1);
/* 382*/        if((float)((j = Math.min(image.getHeight() - 1, j + f1)) - l) < f * 3F)
/* 384*/            throw NotFoundException.getNotFoundInstance();
/* 387*/        else
/* 387*/            return (f = new AlignmentPatternFinder(image, k, l, i - k, j - l, f, resultPointCallback)).find();
            }

            private final BitMatrix image;
            private ResultPointCallback resultPointCallback;
}
