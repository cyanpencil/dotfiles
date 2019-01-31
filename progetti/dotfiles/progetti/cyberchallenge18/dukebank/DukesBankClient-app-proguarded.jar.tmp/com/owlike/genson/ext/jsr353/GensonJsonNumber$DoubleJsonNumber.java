// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonJsonNumber.java

package com.owlike.genson.ext.jsr353;

import java.math.BigDecimal;
import java.math.BigInteger;

// Referenced classes of package com.owlike.genson.ext.jsr353:
//            GensonJsonNumber

static class exactValue extends GensonJsonNumber
{

            public boolean isIntegral()
            {
/* 103*/        return false;
            }

            public int intValue()
            {
/* 108*/        return (int)value;
            }

            public int intValueExact()
            {
/* 113*/        return (int)value;
            }

            public long longValue()
            {
/* 118*/        return (long)value;
            }

            public long longValueExact()
            {
/* 123*/        return (long)value;
            }

            public BigInteger bigIntegerValue()
            {
/* 128*/        return bigDecimalValue().toBigInteger();
            }

            public BigInteger bigIntegerValueExact()
            {
/* 133*/        return bigDecimalValue().toBigIntegerExact();
            }

            public double doubleValue()
            {
/* 138*/        return value;
            }

            public BigDecimal bigDecimalValue()
            {
/* 143*/        if(exactValue == null)
/* 144*/            exactValue = BigDecimal.valueOf(value);
/* 146*/        return exactValue;
            }

            public boolean equals(Object obj)
            {
/* 151*/        if(this == obj)
/* 151*/            return true;
/* 152*/        if(obj == null || getClass() != obj.getClass())
                {
/* 152*/            return false;
                } else
                {
/* 154*/            obj = (exactValue)obj;
/* 156*/            return bigDecimalValue().equals(((bigDecimalValue) (obj)).bigDecimalValue());
                }
            }

            public int hashCode()
            {
/* 161*/        return bigDecimalValue().hashCode();
            }

            public String toString()
            {
/* 166*/        return Double.toString(value);
            }

            private final double value;
            private BigDecimal exactValue;

            protected (double d)
            {
/*  93*/        value = d;
            }

            protected value(BigDecimal bigdecimal)
            {
/*  97*/        exactValue = bigdecimal;
/*  98*/        value = exactValue.doubleValue();
            }
}
