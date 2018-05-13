// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   IntConst.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;

// Referenced classes of package javassist.compiler.ast:
//            ASTree, DoubleConst, Visitor

public class IntConst extends ASTree
{

            public IntConst(long l, int i)
            {
/*  29*/        value = l;
/*  29*/        type = i;
            }

            public long get()
            {
/*  31*/        return value;
            }

            public void set(long l)
            {
/*  33*/        value = l;
            }

            public int getType()
            {
/*  37*/        return type;
            }

            public String toString()
            {
/*  39*/        return Long.toString(value);
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  42*/        visitor.atIntConst(this);
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

            private IntConst compute0(int i, IntConst intconst)
            {
/*  55*/        int j = type;
/*  56*/        int k = intconst.type;
/*  58*/        if(j == 403 || k == 403)
/*  59*/            k = 403;
/*  60*/        else
/*  60*/        if(j == 401 && k == 401)
/*  62*/            k = 401;
/*  64*/        else
/*  64*/            k = 402;
/*  66*/        long l = value;
/*  67*/        long l1 = intconst.value;
                long l2;
/*  69*/        switch(i)
                {
/*  71*/        case 43: // '+'
/*  71*/            l2 = l + l1;
                    break;

/*  74*/        case 45: // '-'
/*  74*/            l2 = l - l1;
                    break;

/*  77*/        case 42: // '*'
/*  77*/            l2 = l * l1;
                    break;

/*  80*/        case 47: // '/'
/*  80*/            l2 = l / l1;
                    break;

/*  83*/        case 37: // '%'
/*  83*/            l2 = l % l1;
                    break;

/*  86*/        case 124: // '|'
/*  86*/            l2 = l | l1;
                    break;

/*  89*/        case 94: // '^'
/*  89*/            l2 = l ^ l1;
                    break;

/*  92*/        case 38: // '&'
/*  92*/            l2 = l & l1;
                    break;

/*  95*/        case 364: 
/*  95*/            l2 = value << (int)l1;
/*  96*/            k = j;
                    break;

/*  99*/        case 366: 
/*  99*/            l2 = value >> (int)l1;
/* 100*/            k = j;
                    break;

/* 103*/        case 370: 
/* 103*/            l2 = value >>> (int)l1;
/* 104*/            k = j;
                    break;

/* 107*/        default:
/* 107*/            return null;
                }
/* 110*/        return new IntConst(l2, k);
            }

            private DoubleConst compute0(int i, DoubleConst doubleconst)
            {
/* 114*/        double d = value;
/* 115*/        double d1 = doubleconst.value;
                double d2;
/* 117*/        switch(i)
                {
/* 119*/        case 43: // '+'
/* 119*/            d2 = d + d1;
                    break;

/* 122*/        case 45: // '-'
/* 122*/            d2 = d - d1;
                    break;

/* 125*/        case 42: // '*'
/* 125*/            d2 = d * d1;
                    break;

/* 128*/        case 47: // '/'
/* 128*/            d2 = d / d1;
                    break;

/* 131*/        case 37: // '%'
/* 131*/            d2 = d % d1;
                    break;

/* 134*/        case 38: // '&'
/* 134*/        case 39: // '\''
/* 134*/        case 40: // '('
/* 134*/        case 41: // ')'
/* 134*/        case 44: // ','
/* 134*/        case 46: // '.'
/* 134*/        default:
/* 134*/            return null;
                }
/* 137*/        return new DoubleConst(d2, doubleconst.type);
            }

            protected long value;
            protected int type;
}
