// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MathPreconditions.java

package jersey.repackaged.com.google.common.math;


final class MathPreconditions
{

            static long checkPositive(String s, long l)
            {
/*  38*/        if(l <= 0L)
                {
/*  39*/            s = String.valueOf(String.valueOf(s));
/*  39*/            throw new IllegalArgumentException((new StringBuilder(35 + s.length())).append(s).append(" (").append(l).append(") must be > 0").toString());
                } else
                {
/*  41*/            return l;
                }
            }

            static int checkNonNegative(String s, int i)
            {
/*  52*/        if(i < 0)
                {
/*  53*/            s = String.valueOf(String.valueOf(s));
/*  53*/            throw new IllegalArgumentException((new StringBuilder(27 + s.length())).append(s).append(" (").append(i).append(") must be >= 0").toString());
                } else
                {
/*  55*/            return i;
                }
            }

            static long checkNonNegative(String s, long l)
            {
/*  59*/        if(l < 0L)
                {
/*  60*/            s = String.valueOf(String.valueOf(s));
/*  60*/            throw new IllegalArgumentException((new StringBuilder(36 + s.length())).append(s).append(" (").append(l).append(") must be >= 0").toString());
                } else
                {
/*  62*/            return l;
                }
            }

            static void checkRoundingUnnecessary(boolean flag)
            {
/*  80*/        if(!flag)
/*  81*/            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
/*  83*/        else
/*  83*/            return;
            }

            static void checkNoOverflow(boolean flag)
            {
/*  92*/        if(!flag)
/*  93*/            throw new ArithmeticException("overflow");
/*  95*/        else
/*  95*/            return;
            }
}
