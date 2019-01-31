// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   IntMath.java

package jersey.repackaged.com.google.common.math;

import java.math.RoundingMode;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.math:
//            MathPreconditions

public final class IntMath
{

            public static int divide(int i, int j, RoundingMode roundingmode)
            {
/* 279*/        Preconditions.checkNotNull(roundingmode);
/* 280*/        if(j == 0)
/* 281*/            throw new ArithmeticException("/ by zero");
/* 283*/        int k = i / j;
                int l;
/* 284*/        if((l = i - j * k) == 0)
/* 287*/            return k;
/* 297*/        i = 1 | (i ^ j) >> 31;
        static class _cls1
        {

                    static final int $SwitchMap$java$math$RoundingMode[];

                    static 
                    {
/*  89*/                $SwitchMap$java$math$RoundingMode = new int[RoundingMode.values().length];
/*  89*/                try
                        {
/*  89*/                    $SwitchMap$java$math$RoundingMode[RoundingMode.UNNECESSARY.ordinal()] = 1;
                        }
/*  89*/                catch(NoSuchFieldError _ex) { }
/*  89*/                try
                        {
/*  89*/                    $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 2;
                        }
/*  89*/                catch(NoSuchFieldError _ex) { }
/*  89*/                try
                        {
/*  89*/                    $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 3;
                        }
/*  89*/                catch(NoSuchFieldError _ex) { }
/*  89*/                try
                        {
/*  89*/                    $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 4;
                        }
/*  89*/                catch(NoSuchFieldError _ex) { }
/*  89*/                try
                        {
/*  89*/                    $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 5;
                        }
/*  89*/                catch(NoSuchFieldError _ex) { }
/*  89*/                try
                        {
/*  89*/                    $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 6;
                        }
/*  89*/                catch(NoSuchFieldError _ex) { }
/*  89*/                try
                        {
/*  89*/                    $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
                        }
/*  89*/                catch(NoSuchFieldError _ex) { }
/*  89*/                try
                        {
/*  89*/                    $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 8;
                        }
/*  89*/                catch(NoSuchFieldError _ex) { }
                    }
        }

/* 299*/        switch(_cls1..SwitchMap.java.math.RoundingMode[roundingmode.ordinal()])
                {
/* 301*/        case 1: // '\001'
/* 301*/            MathPreconditions.checkRoundingUnnecessary(l == 0);
                    // fall through

/* 304*/        case 2: // '\002'
/* 304*/            j = 0;
                    break;

/* 307*/        case 4: // '\004'
/* 307*/            j = 1;
                    break;

/* 310*/        case 5: // '\005'
/* 310*/            j = i <= 0 ? 0 : 1;
                    break;

/* 313*/        case 3: // '\003'
/* 313*/            j = i >= 0 ? 0 : 1;
                    break;

/* 318*/        case 6: // '\006'
/* 318*/        case 7: // '\007'
/* 318*/        case 8: // '\b'
/* 318*/            if((j = (l = Math.abs(l)) - (Math.abs(j) - l)) == 0)
/* 323*/                j = roundingmode != RoundingMode.HALF_UP && !((roundingmode == RoundingMode.HALF_EVEN) & ((k & 1) != 0)) ? 0 : 1;
/* 325*/            else
/* 325*/                j = j <= 0 ? 0 : 1;
                    break;

/* 329*/        default:
/* 329*/            throw new AssertionError();
                }
/* 331*/        if(j != 0)
/* 331*/            return k + i;
/* 331*/        else
/* 331*/            return k;
            }

            public static int checkedMultiply(int i, int j)
            {
                long l;
/* 437*/        MathPreconditions.checkNoOverflow((l = (long)i * (long)j) == (long)(int)l);
/* 439*/        return (int)l;
            }

            public static int factorial(int i)
            {
/* 498*/        MathPreconditions.checkNonNegative("n", i);
/* 499*/        if(i < factorials.length)
/* 499*/            return factorials[i];
/* 499*/        else
/* 499*/            return 0x7fffffff;
            }

            static final byte maxLog10ForLeadingZeros[] = {
/* 169*/        9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 
/* 169*/        6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 
/* 169*/        3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 
/* 169*/        0, 0, 0
            };
            static final int powersOf10[] = {
/* 172*/        1, 10, 100, 1000, 10000, 0x186a0, 0xf4240, 0x989680, 0x5f5e100, 0x3b9aca00
            };
            static final int halfPowersOf10[] = {
/* 176*/        3, 31, 316, 3162, 31622, 0x4d343, 0x3040a5, 0x1e28678, 0x12d940b6, 0x7fffffff
            };
            private static final int factorials[] = {
/* 502*/        1, 1, 2, 6, 24, 120, 720, 5040, 40320, 0x58980, 
/* 502*/        0x375f00, 0x2611500, 0x1c8cfc00
            };
            static int biggestBinomials[] = {
/* 550*/        0x7fffffff, 0x7fffffff, 0x10000, 2345, 477, 193, 110, 75, 58, 49, 
/* 550*/        43, 39, 37, 35, 34, 34, 33
            };

}
