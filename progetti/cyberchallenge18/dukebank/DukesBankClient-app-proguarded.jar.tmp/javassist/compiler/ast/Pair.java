// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Pair.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;

// Referenced classes of package javassist.compiler.ast:
//            ASTree, Visitor

public class Pair extends ASTree
{

            public Pair(ASTree astree, ASTree astree1)
            {
/*  29*/        left = astree;
/*  30*/        right = astree1;
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  33*/        visitor.atPair(this);
            }

            public String toString()
            {
                StringBuffer stringbuffer;
/*  36*/        (stringbuffer = new StringBuffer()).append("(<Pair> ");
/*  38*/        stringbuffer.append(left != null ? left.toString() : "<null>");
/*  39*/        stringbuffer.append(" . ");
/*  40*/        stringbuffer.append(right != null ? right.toString() : "<null>");
/*  41*/        stringbuffer.append(')');
/*  42*/        return stringbuffer.toString();
            }

            public ASTree getLeft()
            {
/*  45*/        return left;
            }

            public ASTree getRight()
            {
/*  47*/        return right;
            }

            public void setLeft(ASTree astree)
            {
/*  49*/        left = astree;
            }

            public void setRight(ASTree astree)
            {
/*  51*/        right = astree;
            }

            protected ASTree left;
            protected ASTree right;
}
