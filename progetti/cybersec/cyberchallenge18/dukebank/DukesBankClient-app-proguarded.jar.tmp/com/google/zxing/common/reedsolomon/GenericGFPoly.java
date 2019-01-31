// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GenericGFPoly.java

package com.google.zxing.common.reedsolomon;


// Referenced classes of package com.google.zxing.common.reedsolomon:
//            GenericGF

final class GenericGFPoly
{

            GenericGFPoly(GenericGF genericgf, int ai[])
            {
/*  43*/        if(ai.length == 0)
/*  44*/            throw new IllegalArgumentException();
/*  46*/        field = genericgf;
                int i;
/*  47*/        if((i = ai.length) > 1 && ai[0] == 0)
                {
                    int j;
/*  50*/            for(j = 1; j < i && ai[j] == 0; j++);
/*  54*/            if(j == i)
                    {
/*  55*/                coefficients = genericgf.getZero().coefficients;
                    } else
                    {
/*  57*/                coefficients = new int[i - j];
/*  58*/                System.arraycopy(ai, j, coefficients, 0, coefficients.length);
/*  64*/                return;
                    }
                } else
                {
/*  65*/            coefficients = ai;
                }
            }

            final int[] getCoefficients()
            {
/*  70*/        return coefficients;
            }

            final int getDegree()
            {
/*  77*/        return coefficients.length - 1;
            }

            final boolean isZero()
            {
/*  84*/        return coefficients[0] == 0;
            }

            final int getCoefficient(int i)
            {
/*  91*/        return coefficients[coefficients.length - 1 - i];
            }

            final int evaluateAt(int i)
            {
/*  98*/        if(i == 0)
/* 100*/            return getCoefficient(0);
/* 102*/        int j = coefficients.length;
/* 103*/        if(i == 1)
                {
/* 105*/            int k = 0;
                    int ai[];
/* 106*/            i = (ai = coefficients).length;
/* 106*/            for(j = 0; j < i; j++)
                    {
/* 106*/                int j1 = ai[j];
/* 107*/                k = GenericGF.addOrSubtract(k, j1);
                    }

/* 109*/            return k;
                }
/* 111*/        int l = coefficients[0];
/* 112*/        for(int i1 = 1; i1 < j; i1++)
/* 113*/            l = GenericGF.addOrSubtract(field.multiply(i, l), coefficients[i1]);

/* 115*/        return l;
            }

            final GenericGFPoly addOrSubtract(GenericGFPoly genericgfpoly)
            {
/* 119*/        if(!field.equals(genericgfpoly.field))
/* 120*/            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
/* 122*/        if(isZero())
/* 123*/            return genericgfpoly;
/* 125*/        if(genericgfpoly.isZero())
/* 126*/            return this;
/* 129*/        Object obj = coefficients;
/* 130*/        genericgfpoly = genericgfpoly.coefficients;
/* 131*/        if(obj.length > genericgfpoly.length)
                {
/* 132*/            int ai[] = ((int []) (obj));
/* 133*/            obj = genericgfpoly;
/* 134*/            genericgfpoly = ai;
                }
/* 136*/        int ai1[] = new int[genericgfpoly.length];
/* 137*/        int i = genericgfpoly.length - obj.length;
/* 139*/        System.arraycopy(genericgfpoly, 0, ai1, 0, i);
/* 141*/        for(int j = i; j < genericgfpoly.length; j++)
/* 142*/            ai1[j] = GenericGF.addOrSubtract(obj[j - i], genericgfpoly[j]);

/* 145*/        return new GenericGFPoly(field, ai1);
            }

            final GenericGFPoly multiply(GenericGFPoly genericgfpoly)
            {
/* 149*/        if(!field.equals(genericgfpoly.field))
/* 150*/            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
/* 152*/        if(isZero() || genericgfpoly.isZero())
/* 153*/            return field.getZero();
                int ai[];
/* 155*/        int i = (ai = coefficients).length;
/* 157*/        int j = (genericgfpoly = genericgfpoly.coefficients).length;
/* 159*/        int ai1[] = new int[(i + j) - 1];
/* 160*/        for(int k = 0; k < i; k++)
                {
/* 161*/            int l = ai[k];
/* 162*/            for(int i1 = 0; i1 < j; i1++)
/* 163*/                ai1[k + i1] = GenericGF.addOrSubtract(ai1[k + i1], field.multiply(l, genericgfpoly[i1]));

                }

/* 167*/        return new GenericGFPoly(field, ai1);
            }

            final GenericGFPoly multiply(int i)
            {
/* 171*/        if(i == 0)
/* 172*/            return field.getZero();
/* 174*/        if(i == 1)
/* 175*/            return this;
                int j;
/* 177*/        int ai[] = new int[j = coefficients.length];
/* 179*/        for(int k = 0; k < j; k++)
/* 180*/            ai[k] = field.multiply(coefficients[k], i);

/* 182*/        return new GenericGFPoly(field, ai);
            }

            final GenericGFPoly multiplyByMonomial(int i, int j)
            {
/* 186*/        if(i < 0)
/* 187*/            throw new IllegalArgumentException();
/* 189*/        if(j == 0)
/* 190*/            return field.getZero();
                int k;
/* 192*/        i = new int[(k = coefficients.length) + i];
/* 194*/        for(int l = 0; l < k; l++)
/* 195*/            i[l] = field.multiply(coefficients[l], j);

/* 197*/        return new GenericGFPoly(field, i);
            }

            final GenericGFPoly[] divide(GenericGFPoly genericgfpoly)
            {
/* 201*/        if(!field.equals(genericgfpoly.field))
/* 202*/            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
/* 204*/        if(genericgfpoly.isZero())
/* 205*/            throw new IllegalArgumentException("Divide by 0");
/* 208*/        GenericGFPoly genericgfpoly1 = field.getZero();
/* 209*/        GenericGFPoly genericgfpoly2 = this;
/* 211*/        int i = genericgfpoly.getCoefficient(genericgfpoly.getDegree());
/* 212*/        i = field.inverse(i);
                GenericGFPoly genericgfpoly4;
/* 214*/        for(; genericgfpoly2.getDegree() >= genericgfpoly.getDegree() && !genericgfpoly2.isZero(); genericgfpoly2 = genericgfpoly2.addOrSubtract(genericgfpoly4))
                {
/* 215*/            int j = genericgfpoly2.getDegree() - genericgfpoly.getDegree();
/* 216*/            int k = field.multiply(genericgfpoly2.getCoefficient(genericgfpoly2.getDegree()), i);
/* 217*/            genericgfpoly4 = genericgfpoly.multiplyByMonomial(j, k);
/* 218*/            GenericGFPoly genericgfpoly3 = field.buildMonomial(j, k);
/* 219*/            genericgfpoly1 = genericgfpoly1.addOrSubtract(genericgfpoly3);
                }

/* 223*/        return (new GenericGFPoly[] {
/* 223*/            genericgfpoly1, genericgfpoly2
                });
            }

            public final String toString()
            {
/* 228*/        StringBuilder stringbuilder = new StringBuilder(8 * getDegree());
/* 229*/        for(int i = getDegree(); i >= 0; i--)
                {
                    int j;
/* 230*/            if((j = getCoefficient(i)) == 0)
/* 232*/                continue;
/* 232*/            if(j < 0)
                    {
/* 233*/                stringbuilder.append(" - ");
/* 234*/                j = -j;
                    } else
/* 236*/            if(stringbuilder.length() > 0)
/* 237*/                stringbuilder.append(" + ");
/* 240*/            if(i == 0 || j != 1)
/* 241*/                if((j = field.log(j)) == 0)
/* 243*/                    stringbuilder.append('1');
/* 244*/                else
/* 244*/                if(j == 1)
                        {
/* 245*/                    stringbuilder.append('a');
                        } else
                        {
/* 247*/                    stringbuilder.append("a^");
/* 248*/                    stringbuilder.append(j);
                        }
/* 251*/            if(i == 0)
/* 252*/                continue;
/* 252*/            if(i == 1)
                    {
/* 253*/                stringbuilder.append('x');
                    } else
                    {
/* 255*/                stringbuilder.append("x^");
/* 256*/                stringbuilder.append(i);
                    }
                }

/* 261*/        return stringbuilder.toString();
            }

            private final GenericGF field;
            private final int coefficients[];
}
