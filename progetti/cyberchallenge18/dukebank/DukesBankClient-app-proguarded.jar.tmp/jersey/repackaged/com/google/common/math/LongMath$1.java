// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LongMath.java

package jersey.repackaged.com.google.common.math;

import java.math.RoundingMode;

// Referenced classes of package jersey.repackaged.com.google.common.math:
//            LongMath

static class _cls9
{

            static final int $SwitchMap$java$math$RoundingMode[];

            static 
            {
/*  87*/        $SwitchMap$java$math$RoundingMode = new int[RoundingMode.values().length];
/*  87*/        try
                {
/*  87*/            $SwitchMap$java$math$RoundingMode[RoundingMode.UNNECESSARY.ordinal()] = 1;
                }
/*  87*/        catch(NoSuchFieldError _ex) { }
/*  87*/        try
                {
/*  87*/            $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 2;
                }
/*  87*/        catch(NoSuchFieldError _ex) { }
/*  87*/        try
                {
/*  87*/            $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 3;
                }
/*  87*/        catch(NoSuchFieldError _ex) { }
/*  87*/        try
                {
/*  87*/            $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 4;
                }
/*  87*/        catch(NoSuchFieldError _ex) { }
/*  87*/        try
                {
/*  87*/            $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 5;
                }
/*  87*/        catch(NoSuchFieldError _ex) { }
/*  87*/        try
                {
/*  87*/            $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 6;
                }
/*  87*/        catch(NoSuchFieldError _ex) { }
/*  87*/        try
                {
/*  87*/            $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
                }
/*  87*/        catch(NoSuchFieldError _ex) { }
/*  87*/        try
                {
/*  87*/            $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 8;
                }
/*  87*/        catch(NoSuchFieldError _ex) { }
            }
}
