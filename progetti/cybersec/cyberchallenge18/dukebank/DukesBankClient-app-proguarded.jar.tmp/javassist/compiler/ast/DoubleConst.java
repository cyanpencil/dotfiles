// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DoubleConst.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;

// Referenced classes of package javassist.compiler.ast:
//            ASTree, IntConst, Visitor

public class DoubleConst extends ASTree
{

            public DoubleConst(double d, int i)
            {
/*  29*/        value = d;
/*  29*/        type = i;
            }

            public double get()
            {
/*  31*/        return value;
            }

            public void set(double d)
            {
/*  33*/        value = d;
            }

            public int getType()
            {
/*  37*/        return type;
            }

            public String toString()
            {
/*  39*/        return Double.toString(value);
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  42*/        visitor.atDoubleConst(this);
            }

            public ASTree compute(int i, ASTree astree)
            {
/*  46*/        if(astree instanceof IntConst)
/*  47*/            return compute0(i, (IntConst)astree);
/*  48*/        if(astree instanceof DoubleConst)
/*  49*/            return compute0(i, (DoubleConst)astree);
/*  51*/        else
/*  51*/            return null;
            }

            private DoubleConst compute0(int i, DoubleConst doubleconst)
            {
                char c;
/*  56*/        if(type == 405 || doubleconst.type == 405)
/*  58*/            c = '\u0195';
/*  60*/        else
/*  60*/            c = '\u0194';
/*  62*/        return compute(i, value, doubleconst.value, c);
            }

            private DoubleConst compute0(int i, IntConst intconst)
            {
/*  66*/        return compute(i, value, intconst.value, type);
            }

            private static DoubleConst compute(int i, double d, double d1, int j)
            {
                double d2;
/*  73*/        switch(i)
                {
/*  75*/        case 43: // '+'
/*  75*/            d2 = d + d1;
                    break;

/*  78*/        case 45: // '-'
/*  78*/            d2 = d - d1;
                    break;

/*  81*/        case 42: // '*'
/*  81*/            d2 = d * d1;
                    break;

/*  84*/        case 47: // '/'
/*  84*/            d2 = d / d1;
                    break;

/*  87*/        case 37: // '%'
/*  87*/            d2 = d % d1;
                    break;

/*  90*/        case 38: // '&'
/*  90*/        case 39: // '\''
/*  90*/        case 40: // '('
/*  90*/        case 41: // ')'
/*  90*/        case 44: // ','
/*  90*/        case 46: // '.'
/*  90*/        default:
/*  90*/            return null;
                }
/*  93*/        return new DoubleConst(d2, j);
            }

            protected double value;
            protected int type;
}
