// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonJsonNumber.java

package com.owlike.genson.ext.jsr353;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.json.JsonNumber;
import javax.json.JsonValue;

abstract class GensonJsonNumber
    implements JsonNumber
{
    static class DoubleJsonNumber extends GensonJsonNumber
    {

                public boolean isIntegral()
                {
/* 103*/            return false;
                }

                public int intValue()
                {
/* 108*/            return (int)value;
                }

                public int intValueExact()
                {
/* 113*/            return (int)value;
                }

                public long longValue()
                {
/* 118*/            return (long)value;
                }

                public long longValueExact()
                {
/* 123*/            return (long)value;
                }

                public BigInteger bigIntegerValue()
                {
/* 128*/            return bigDecimalValue().toBigInteger();
                }

                public BigInteger bigIntegerValueExact()
                {
/* 133*/            return bigDecimalValue().toBigIntegerExact();
                }

                public double doubleValue()
                {
/* 138*/            return value;
                }

                public BigDecimal bigDecimalValue()
                {
/* 143*/            if(exactValue == null)
/* 144*/                exactValue = BigDecimal.valueOf(value);
/* 146*/            return exactValue;
                }

                public boolean equals(Object obj)
                {
/* 151*/            if(this == obj)
/* 151*/                return true;
/* 152*/            if(obj == null || getClass() != obj.getClass())
                    {
/* 152*/                return false;
                    } else
                    {
/* 154*/                obj = (DoubleJsonNumber)obj;
/* 156*/                return bigDecimalValue().equals(((DoubleJsonNumber) (obj)).bigDecimalValue());
                    }
                }

                public int hashCode()
                {
/* 161*/            return bigDecimalValue().hashCode();
                }

                public String toString()
                {
/* 166*/            return Double.toString(value);
                }

                private final double value;
                private BigDecimal exactValue;

                protected DoubleJsonNumber(double d)
                {
/*  93*/            value = d;
                }

                protected DoubleJsonNumber(BigDecimal bigdecimal)
                {
/*  97*/            exactValue = bigdecimal;
/*  98*/            value = exactValue.doubleValue();
                }
    }

    static class IntJsonNumber extends GensonJsonNumber
    {

                public boolean isIntegral()
                {
/*  24*/            return true;
                }

                public int intValue()
                {
/*  29*/            return (int)value;
                }

                public int intValueExact()
                {
/*  34*/            return (int)value;
                }

                public long longValue()
                {
/*  39*/            return value;
                }

                public long longValueExact()
                {
/*  44*/            return value;
                }

                public BigInteger bigIntegerValue()
                {
/*  49*/            return BigInteger.valueOf(value);
                }

                public BigInteger bigIntegerValueExact()
                {
/*  54*/            return BigInteger.valueOf(value);
                }

                public double doubleValue()
                {
/*  59*/            return (double)value;
                }

                public BigDecimal bigDecimalValue()
                {
/*  64*/            return BigDecimal.valueOf(value);
                }

                public boolean equals(Object obj)
                {
/*  69*/            if(this == obj)
/*  69*/                return true;
/*  70*/            if(obj == null || getClass() != obj.getClass())
                    {
/*  70*/                return false;
                    } else
                    {
/*  72*/                obj = (IntJsonNumber)obj;
/*  74*/                return bigIntegerValue().equals(((IntJsonNumber) (obj)).bigIntegerValue());
                    }
                }

                public int hashCode()
                {
/*  79*/            return bigIntegerValue().hashCode();
                }

                public String toString()
                {
/*  84*/            return Long.toString(value);
                }

                private final long value;
                private BigInteger exactValue;

                protected IntJsonNumber(long l)
                {
/*  14*/            value = l;
                }

                IntJsonNumber(BigInteger biginteger)
                {
/*  18*/            exactValue = biginteger;
/*  19*/            value = biginteger.longValue();
                }
    }


            GensonJsonNumber()
            {
            }

            public javax.json.JsonValue.ValueType getValueType()
            {
/* 172*/        return javax.json.JsonValue.ValueType.NUMBER;
            }
}
