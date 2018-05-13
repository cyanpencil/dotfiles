// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LongMath.java

package jersey.repackaged.com.google.common.math;

import java.math.RoundingMode;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.math:
//            MathPreconditions

public final class LongMath
{

            public static boolean isPowerOfTwo(long l)
            {
/*  62*/        return (l > 0L) & ((l & l - 1L) == 0L);
            }

            static int lessThanBranchFree(long l, long l1)
            {
/*  73*/        return (int)(~~(l - l1) >>> 63);
            }

            public static int log2(long l, RoundingMode roundingmode)
            {
/*  86*/        MathPreconditions.checkPositive("x", l);
        static class _cls1
        {

                    static final int $SwitchMap$java$math$RoundingMode[];

                    static 
                    {
/*  87*/                $SwitchMap$java$math$RoundingMode = new int[RoundingMode.values().length];
/*  87*/                try
                        {
/*  87*/                    $SwitchMap$java$math$RoundingMode[RoundingMode.UNNECESSARY.ordinal()] = 1;
                        }
/*  87*/                catch(NoSuchFieldError _ex) { }
/*  87*/                try
                        {
/*  87*/                    $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 2;
                        }
/*  87*/                catch(NoSuchFieldError _ex) { }
/*  87*/                try
                        {
/*  87*/                    $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 3;
                        }
/*  87*/                catch(NoSuchFieldError _ex) { }
/*  87*/                try
                        {
/*  87*/                    $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 4;
                        }
/*  87*/                catch(NoSuchFieldError _ex) { }
/*  87*/                try
                        {
/*  87*/                    $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 5;
                        }
/*  87*/                catch(NoSuchFieldError _ex) { }
/*  87*/                try
                        {
/*  87*/                    $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 6;
                        }
/*  87*/                catch(NoSuchFieldError _ex) { }
/*  87*/                try
                        {
/*  87*/                    $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
                        }
/*  87*/                catch(NoSuchFieldError _ex) { }
/*  87*/                try
                        {
/*  87*/                    $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 8;
                        }
/*  87*/                catch(NoSuchFieldError _ex) { }
                    }
        }

/*  87*/        switch(_cls1..SwitchMap.java.math.RoundingMode[roundingmode.ordinal()])
                {
/*  89*/        case 1: // '\001'
/*  89*/            MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(l));
                    // fall through

/*  93*/        case 2: // '\002'
/*  93*/        case 3: // '\003'
/*  93*/            return 63 - Long.numberOfLeadingZeros(l);

/*  97*/        case 4: // '\004'
/*  97*/        case 5: // '\005'
/*  97*/            return 64 - Long.numberOfLeadingZeros(l - 1L);

/* 103*/        case 6: // '\006'
/* 103*/        case 7: // '\007'
/* 103*/        case 8: // '\b'
/* 103*/            roundingmode = Long.numberOfLeadingZeros(l);
/* 104*/            long l1 = 0xb504f333f9de6484L >>> roundingmode;
/* 106*/            return (roundingmode = 63 - roundingmode) + lessThanBranchFree(l1, l);

/* 110*/        default:
/* 110*/            throw new AssertionError("impossible");
                }
            }

            public static long gcd(long l, long l1)
            {
/* 463*/        MathPreconditions.checkNonNegative("a", l);
/* 464*/        MathPreconditions.checkNonNegative("b", l1);
/* 465*/        if(l == 0L)
/* 468*/            return l1;
/* 469*/        if(l1 == 0L)
/* 470*/            return l;
/* 476*/        int i = Long.numberOfTrailingZeros(l);
/* 477*/        l >>= i;
/* 478*/        int j = Long.numberOfTrailingZeros(l1);
/* 479*/        for(l1 >>= j; l != l1; l >>= Long.numberOfTrailingZeros(l))
                {
                    long l2;
/* 488*/            long l3 = (l2 = l - l1) & l2 >> 63;
/* 493*/            l = l2 - l3 - l3;
/* 496*/            l1 += l3;
                }

/* 499*/        return l << Math.min(i, j);
            }

            public static long binomial(int i, int j)
            {
/* 649*/        MathPreconditions.checkNonNegative("n", i);
/* 650*/        MathPreconditions.checkNonNegative("k", j);
/* 651*/        Preconditions.checkArgument(j <= i, "k (%s) > n (%s)", new Object[] {
/* 651*/            Integer.valueOf(j), Integer.valueOf(i)
                });
/* 652*/        if(j > i >> 1)
/* 653*/            j = i - j;
/* 655*/        switch(j)
                {
/* 657*/        case 0: // '\0'
/* 657*/            return 1L;

/* 659*/        case 1: // '\001'
/* 659*/            return (long)i;
                }
/* 661*/        if(i < factorials.length)
/* 662*/            return factorials[i] / (factorials[j] * factorials[i - j]);
/* 663*/        if(j >= biggestBinomials.length || i > biggestBinomials[j])
/* 664*/            return 0x7fffffffffffffffL;
/* 665*/        if(j < biggestSimpleBinomials.length && i <= biggestSimpleBinomials[j])
                {
/* 667*/            long l = i--;
/* 668*/            for(int j1 = 2; j1 <= j; j1++)
                    {
/* 669*/                l = (l *= i) / (long)j1;
/* 668*/                i--;
                    }

/* 672*/            return l;
                }
/* 674*/        int k = log2(i, RoundingMode.CEILING);
/* 676*/        long l1 = 1L;
/* 677*/        long l2 = i--;
/* 678*/        long l3 = 1L;
/* 680*/        int i1 = k;
/* 688*/        for(int k1 = 2; k1 <= j;)
                {
/* 689*/            if(i1 + k < 63)
                    {
/* 691*/                l2 *= i;
/* 692*/                l3 *= k1;
/* 693*/                i1 += k;
                    } else
                    {
/* 697*/                l1 = multiplyFraction(l1, l2, l3);
/* 698*/                l2 = i;
/* 699*/                l3 = k1;
/* 700*/                i1 = k;
                    }
/* 688*/            k1++;
/* 688*/            i--;
                }

/* 703*/        return multiplyFraction(l1, l2, l3);
            }

            static long multiplyFraction(long l, long l1, long l2)
            {
/* 712*/        if(l == 1L)
                {
/* 713*/            return l1 / l2;
                } else
                {
/* 715*/            long l3 = gcd(l, l2);
/* 716*/            l /= l3;
/* 717*/            l2 /= l3;
/* 720*/            return l * (l1 / l2);
                }
            }

            static final byte maxLog10ForLeadingZeros[] = {
/* 169*/        19, 18, 18, 18, 18, 17, 17, 17, 16, 16, 
/* 169*/        16, 15, 15, 15, 15, 14, 14, 14, 13, 13, 
/* 169*/        13, 12, 12, 12, 12, 11, 11, 11, 10, 10, 
/* 169*/        10, 9, 9, 9, 9, 8, 8, 8, 7, 7, 
/* 169*/        7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 
/* 169*/        4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 
/* 169*/        1, 0, 0, 0
            };
            static final long powersOf10[] = {
/* 176*/        1L, 10L, 100L, 1000L, 10000L, 0x186a0L, 0xf4240L, 0x989680L, 0x5f5e100L, 0x3b9aca00L, 
/* 176*/        0x2540be400L, 0x174876e800L, 0xe8d4a51000L, 0x9184e72a000L, 0x5af3107a4000L, 0x38d7ea4c68000L, 0x2386f26fc10000L, 0x16345785d8a0000L, 0xde0b6b3a7640000L
            };
            static final long halfPowersOf10[] = {
/* 201*/        3L, 31L, 316L, 3162L, 31622L, 0x4d343L, 0x3040a5L, 0x1e28678L, 0x12d940b6L, 0xbc7c871cL, 
/* 201*/        0x75cdd4719L, 0x49a0a4c700L, 0x2e0466fc608L, 0x1cc2c05dbc53L, 0x11f9b83a95b45L, 0xb3c13249d90bbL, 0x7058bf6e27a751L, 0x463777a4d8c892dL, 0x2be2aac7077d5bc3L
            };
            static final long factorials[] = {
/* 618*/        1L, 1L, 2L, 6L, 24L, 120L, 720L, 5040L, 40320L, 0x58980L, 
/* 618*/        0x375f00L, 0x2611500L, 0x1c8cfc00L, 0x17328cc00L, 0x144c3b2800L, 0x13077775800L, 0x130777758000L, 0x1437eeecd8000L, 0x16beecca730000L, 0x1b02b9306890000L, 
/* 618*/        0x21c3677c82b40000L
            };
            static final int biggestBinomials[] = {
/* 727*/        0x7fffffff, 0x7fffffff, 0x7fffffff, 0x3a25db, 0x1dc79, 16175, 4337, 1733, 887, 534, 
/* 727*/        361, 265, 206, 169, 143, 125, 111, 101, 94, 88, 
/* 727*/        83, 79, 76, 74, 72, 70, 69, 68, 67, 67, 
/* 727*/        66, 66, 66, 66
            };
            static final int biggestSimpleBinomials[] = {
/* 736*/        0x7fffffff, 0x7fffffff, 0x7fffffff, 0x285146, 0x150eb, 11724, 3218, 1313, 684, 419, 
/* 736*/        287, 214, 169, 139, 119, 105, 95, 87, 81, 76, 
/* 736*/        73, 70, 68, 66, 64, 63, 62, 62, 61, 61, 
/* 736*/        61
            };

}
