// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ModulusPoly.java

package com.google.zxing.pdf417.decoder.ec;


// Referenced classes of package com.google.zxing.pdf417.decoder.ec:
//            ModulusGF

final class ModulusPoly
{

            ModulusPoly(ModulusGF modulusgf, int ai[])
            {
/*  29*/        if(ai.length == 0)
/*  30*/            throw new IllegalArgumentException();
/*  32*/        field = modulusgf;
                int i;
/*  33*/        if((i = ai.length) > 1 && ai[0] == 0)
                {
                    int j;
/*  36*/            for(j = 1; j < i && ai[j] == 0; j++);
/*  40*/            if(j == i)
                    {
/*  41*/                coefficients = modulusgf.getZero().coefficients;
                    } else
                    {
/*  43*/                coefficients = new int[i - j];
/*  44*/                System.arraycopy(ai, j, coefficients, 0, coefficients.length);
/*  50*/                return;
                    }
                } else
                {
/*  51*/            coefficients = ai;
                }
            }

            final int[] getCoefficients()
            {
/*  56*/        return coefficients;
            }

            final int getDegree()
            {
/*  63*/        return coefficients.length - 1;
            }

            final boolean isZero()
            {
/*  70*/        return coefficients[0] == 0;
            }

            final int getCoefficient(int i)
            {
/*  77*/        return coefficients[coefficients.length - 1 - i];
            }

            final int evaluateAt(int i)
            {
/*  84*/        if(i == 0)
/*  86*/            return getCoefficient(0);
/*  88*/        int j = coefficients.length;
/*  89*/        if(i == 1)
                {
/*  91*/            int k = 0;
                    int ai[];
/*  92*/            i = (ai = coefficients).length;
/*  92*/            for(j = 0; j < i; j++)
                    {
/*  92*/                int j1 = ai[j];
/*  93*/                k = field.add(k, j1);
                    }

/*  95*/            return k;
                }
/*  97*/        int l = coefficients[0];
/*  98*/        for(int i1 = 1; i1 < j; i1++)
/*  99*/            l = field.add(field.multiply(i, l), coefficients[i1]);

/* 101*/        return l;
            }

            final ModulusPoly add(ModulusPoly moduluspoly)
            {
/* 105*/        if(!field.equals(moduluspoly.field))
/* 106*/            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
/* 108*/        if(isZero())
/* 109*/            return moduluspoly;
/* 111*/        if(moduluspoly.isZero())
/* 112*/            return this;
/* 115*/        Object obj = coefficients;
/* 116*/        moduluspoly = moduluspoly.coefficients;
/* 117*/        if(obj.length > moduluspoly.length)
                {
/* 118*/            int ai[] = ((int []) (obj));
/* 119*/            obj = moduluspoly;
/* 120*/            moduluspoly = ai;
                }
/* 122*/        int ai1[] = new int[moduluspoly.length];
/* 123*/        int i = moduluspoly.length - obj.length;
/* 125*/        System.arraycopy(moduluspoly, 0, ai1, 0, i);
/* 127*/        for(int j = i; j < moduluspoly.length; j++)
/* 128*/            ai1[j] = field.add(obj[j - i], moduluspoly[j]);

/* 131*/        return new ModulusPoly(field, ai1);
            }

            final ModulusPoly subtract(ModulusPoly moduluspoly)
            {
/* 135*/        if(!field.equals(moduluspoly.field))
/* 136*/            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
/* 138*/        if(moduluspoly.isZero())
/* 139*/            return this;
/* 141*/        else
/* 141*/            return add(moduluspoly.negative());
            }

            final ModulusPoly multiply(ModulusPoly moduluspoly)
            {
/* 145*/        if(!field.equals(moduluspoly.field))
/* 146*/            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
/* 148*/        if(isZero() || moduluspoly.isZero())
/* 149*/            return field.getZero();
                int ai[];
/* 151*/        int i = (ai = coefficients).length;
/* 153*/        int j = (moduluspoly = moduluspoly.coefficients).length;
/* 155*/        int ai1[] = new int[(i + j) - 1];
/* 156*/        for(int k = 0; k < i; k++)
                {
/* 157*/            int l = ai[k];
/* 158*/            for(int i1 = 0; i1 < j; i1++)
/* 159*/                ai1[k + i1] = field.add(ai1[k + i1], field.multiply(l, moduluspoly[i1]));

                }

/* 162*/        return new ModulusPoly(field, ai1);
            }

            final ModulusPoly negative()
            {
                int i;
/* 166*/        int ai[] = new int[i = coefficients.length];
/* 168*/        for(int j = 0; j < i; j++)
/* 169*/            ai[j] = field.subtract(0, coefficients[j]);

/* 171*/        return new ModulusPoly(field, ai);
            }

            final ModulusPoly multiply(int i)
            {
/* 175*/        if(i == 0)
/* 176*/            return field.getZero();
/* 178*/        if(i == 1)
/* 179*/            return this;
                int j;
/* 181*/        int ai[] = new int[j = coefficients.length];
/* 183*/        for(int k = 0; k < j; k++)
/* 184*/            ai[k] = field.multiply(coefficients[k], i);

/* 186*/        return new ModulusPoly(field, ai);
            }

            final ModulusPoly multiplyByMonomial(int i, int j)
            {
/* 190*/        if(i < 0)
/* 191*/            throw new IllegalArgumentException();
/* 193*/        if(j == 0)
/* 194*/            return field.getZero();
                int k;
/* 196*/        i = new int[(k = coefficients.length) + i];
/* 198*/        for(int l = 0; l < k; l++)
/* 199*/            i[l] = field.multiply(coefficients[l], j);

/* 201*/        return new ModulusPoly(field, i);
            }

            final ModulusPoly[] divide(ModulusPoly moduluspoly)
            {
/* 205*/        if(!field.equals(moduluspoly.field))
/* 206*/            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
/* 208*/        if(moduluspoly.isZero())
/* 209*/            throw new IllegalArgumentException("Divide by 0");
/* 212*/        ModulusPoly moduluspoly1 = field.getZero();
/* 213*/        ModulusPoly moduluspoly2 = this;
/* 215*/        int i = moduluspoly.getCoefficient(moduluspoly.getDegree());
/* 216*/        i = field.inverse(i);
                ModulusPoly moduluspoly4;
/* 218*/        for(; moduluspoly2.getDegree() >= moduluspoly.getDegree() && !moduluspoly2.isZero(); moduluspoly2 = moduluspoly2.subtract(moduluspoly4))
                {
/* 219*/            int j = moduluspoly2.getDegree() - moduluspoly.getDegree();
/* 220*/            int k = field.multiply(moduluspoly2.getCoefficient(moduluspoly2.getDegree()), i);
/* 221*/            moduluspoly4 = moduluspoly.multiplyByMonomial(j, k);
/* 222*/            ModulusPoly moduluspoly3 = field.buildMonomial(j, k);
/* 223*/            moduluspoly1 = moduluspoly1.add(moduluspoly3);
                }

/* 227*/        return (new ModulusPoly[] {
/* 227*/            moduluspoly1, moduluspoly2
                });
            }

            public final String toString()
            {
/* 232*/        StringBuilder stringbuilder = new StringBuilder(8 * getDegree());
/* 233*/        for(int i = getDegree(); i >= 0; i--)
                {
                    int j;
/* 234*/            if((j = getCoefficient(i)) == 0)
/* 236*/                continue;
/* 236*/            if(j < 0)
                    {
/* 237*/                stringbuilder.append(" - ");
/* 238*/                j = -j;
                    } else
/* 240*/            if(stringbuilder.length() > 0)
/* 241*/                stringbuilder.append(" + ");
/* 244*/            if(i == 0 || j != 1)
/* 245*/                stringbuilder.append(j);
/* 247*/            if(i == 0)
/* 248*/                continue;
/* 248*/            if(i == 1)
                    {
/* 249*/                stringbuilder.append('x');
                    } else
                    {
/* 251*/                stringbuilder.append("x^");
/* 252*/                stringbuilder.append(i);
                    }
                }

/* 257*/        return stringbuilder.toString();
            }

            private final ModulusGF field;
            private final int coefficients[];
}
