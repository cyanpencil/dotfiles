// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ASTList.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;

// Referenced classes of package javassist.compiler.ast:
//            ASTree, Visitor

public class ASTList extends ASTree
{

            public ASTList(ASTree astree, ASTList astlist)
            {
/*  30*/        left = astree;
/*  31*/        right = astlist;
            }

            public ASTList(ASTree astree)
            {
/*  35*/        left = astree;
/*  36*/        right = null;
            }

            public static ASTList make(ASTree astree, ASTree astree1, ASTree astree2)
            {
/*  40*/        return new ASTList(astree, new ASTList(astree1, new ASTList(astree2)));
            }

            public ASTree getLeft()
            {
/*  43*/        return left;
            }

            public ASTree getRight()
            {
/*  45*/        return right;
            }

            public void setLeft(ASTree astree)
            {
/*  47*/        left = astree;
            }

            public void setRight(ASTree astree)
            {
/*  50*/        right = (ASTList)astree;
            }

            public ASTree head()
            {
/*  56*/        return left;
            }

            public void setHead(ASTree astree)
            {
/*  59*/        left = astree;
            }

            public ASTList tail()
            {
/*  65*/        return right;
            }

            public void setTail(ASTList astlist)
            {
/*  68*/        right = astlist;
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  71*/        visitor.atASTList(this);
            }

            public String toString()
            {
                StringBuffer stringbuffer;
/*  74*/        (stringbuffer = new StringBuffer()).append("(<");
/*  76*/        stringbuffer.append(getTag());
/*  77*/        stringbuffer.append('>');
/*  78*/        for(ASTList astlist = this; astlist != null; astlist = astlist.right)
                {
/*  80*/            stringbuffer.append(' ');
/*  81*/            ASTree astree = astlist.left;
/*  82*/            stringbuffer.append(astree != null ? astree.toString() : "<null>");
                }

/*  86*/        stringbuffer.append(')');
/*  87*/        return stringbuffer.toString();
            }

            public int length()
            {
/*  94*/        return length(this);
            }

            public static int length(ASTList astlist)
            {
/*  98*/        if(astlist == null)
/*  99*/            return 0;
                int i;
/* 101*/        for(i = 0; astlist != null; i++)
/* 103*/            astlist = astlist.right;

/* 107*/        return i;
            }

            public ASTList sublist(int i)
            {
                ASTList astlist;
/* 117*/        for(astlist = this; i-- > 0; astlist = astlist.right);
/* 121*/        return astlist;
            }

            public boolean subst(ASTree astree, ASTree astree1)
            {
/* 129*/        for(ASTList astlist = this; astlist != null; astlist = astlist.right)
/* 130*/            if(astlist.left == astree1)
                    {
/* 131*/                astlist.left = astree;
/* 132*/                return true;
                    }

/* 135*/        return false;
            }

            public static ASTList append(ASTList astlist, ASTree astree)
            {
/* 142*/        return concat(astlist, new ASTList(astree));
            }

            public static ASTList concat(ASTList astlist, ASTList astlist1)
            {
/* 149*/        if(astlist == null)
/* 150*/            return astlist1;
                ASTList astlist2;
/* 152*/        for(astlist2 = astlist; astlist2.right != null; astlist2 = astlist2.right);
/* 156*/        astlist2.right = astlist1;
/* 157*/        return astlist;
            }

            private ASTree left;
            private ASTList right;
}
