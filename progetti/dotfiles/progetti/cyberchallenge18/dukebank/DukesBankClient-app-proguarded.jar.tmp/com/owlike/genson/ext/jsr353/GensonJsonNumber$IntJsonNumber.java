// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonJsonNumber.java

package com.owlike.genson.ext.jsr353;

import java.math.BigDecimal;
import java.math.BigInteger;

// Referenced classes of package com.owlike.genson.ext.jsr353:
//            GensonJsonNumber

static class value extends GensonJsonNumber
{

            public boolean isIntegral()
            {
/*  24*/        return true;
            }

            public int intValue()
            {
/*  29*/        return (int)value;
            }

            public int intValueExact()
            {
/*  34*/        return (int)value;
            }

            public long longValue()
            {
/*  39*/        return value;
            }

            public long longValueExact()
            {
/*  44*/        return value;
            }

            public BigInteger bigIntegerValue()
            {
/*  49*/        return BigInteger.valueOf(value);
            }

            public BigInteger bigIntegerValueExact()
            {
/*  54*/        return BigInteger.valueOf(value);
            }

            public double doubleValue()
            {
/*  59*/        return (double)value;
            }

            public BigDecimal bigDecimalValue()
            {
/*  64*/        return BigDecimal.valueOf(value);
            }

            public boolean equals(Object obj)
            {
/*  69*/        if(this == obj)
/*  69*/            return true;
/*  70*/        if(obj == null || getClass() != obj.getClass())
                {
/*  70*/            return false;
                } else
                {
/*  72*/            obj = (value)obj;
/*  74*/            return bigIntegerValue().equals(((bigIntegerValue) (obj)).bigIntegerValue());
                }
            }

            public int hashCode()
            {
/*  79*/        return bigIntegerValue().hashCode();
            }

            public String toString()
            {
/*  84*/        return Long.toString(value);
            }

            private final long value;
            private BigInteger exactValue;

            protected A(long l)
            {
/*  14*/        value = l;
            }

            value(BigInteger biginteger)
            {
/*  18*/        exactValue = biginteger;
/*  19*/        value = biginteger.longValue();
            }
}
