// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import jersey.repackaged.com.google.common.base.Objects;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapDifference, Maps

static class right
    implements ence
{

            static ence create(Object obj, Object obj1)
            {
/* 537*/        return new <init>(obj, obj1);
            }

            public Object leftValue()
            {
/* 547*/        return left;
            }

            public Object rightValue()
            {
/* 552*/        return right;
            }

            public boolean equals(Object obj)
            {
/* 556*/        if(obj instanceof ence)
                {
/* 557*/            obj = (ence)obj;
/* 559*/            return Objects.equal(left, ((ence) (obj)).leftValue()) && Objects.equal(right, ((ence) (obj)).rightValue());
                } else
                {
/* 562*/            return false;
                }
            }

            public int hashCode()
            {
/* 566*/        return Objects.hashCode(new Object[] {
/* 566*/            left, right
                });
            }

            public String toString()
            {
/* 570*/        String s = String.valueOf(String.valueOf(left));
/* 570*/        String s1 = String.valueOf(String.valueOf(right));
/* 570*/        return (new StringBuilder(4 + s.length() + s1.length())).append("(").append(s).append(", ").append(s1).append(")").toString();
            }

            private final Object left;
            private final Object right;

            private ence(Object obj, Object obj1)
            {
/* 541*/        left = obj;
/* 542*/        right = obj1;
            }
}
