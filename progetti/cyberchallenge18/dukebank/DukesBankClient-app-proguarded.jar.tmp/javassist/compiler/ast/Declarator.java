// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Declarator.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;
import javassist.compiler.TokenId;

// Referenced classes of package javassist.compiler.ast:
//            ASTList, ASTree, Symbol, Visitor

public class Declarator extends ASTList
    implements TokenId
{

            public Declarator(int i, int j)
            {
/*  32*/        super(null);
/*  33*/        varType = i;
/*  34*/        arrayDim = j;
/*  35*/        localVar = -1;
/*  36*/        qualifiedClass = null;
            }

            public Declarator(ASTList astlist, int i)
            {
/*  40*/        super(null);
/*  41*/        varType = 307;
/*  42*/        arrayDim = i;
/*  43*/        localVar = -1;
/*  44*/        qualifiedClass = astToClassName(astlist, '/');
            }

            public Declarator(int i, String s, int j, int k, Symbol symbol)
            {
/*  51*/        super(null);
/*  52*/        varType = i;
/*  53*/        arrayDim = j;
/*  54*/        localVar = k;
/*  55*/        qualifiedClass = s;
/*  56*/        setLeft(symbol);
/*  57*/        append(this, null);
            }

            public Declarator make(Symbol symbol, int i, ASTree astree)
            {
/*  61*/        (i = new Declarator(varType, arrayDim + i)).qualifiedClass = qualifiedClass;
/*  63*/        i.setLeft(symbol);
/*  64*/        append(i, astree);
/*  65*/        return i;
            }

            public int getType()
            {
/*  71*/        return varType;
            }

            public int getArrayDim()
            {
/*  73*/        return arrayDim;
            }

            public void addArrayDim(int i)
            {
/*  75*/        arrayDim += i;
            }

            public String getClassName()
            {
/*  77*/        return qualifiedClass;
            }

            public void setClassName(String s)
            {
/*  79*/        qualifiedClass = s;
            }

            public Symbol getVariable()
            {
/*  81*/        return (Symbol)getLeft();
            }

            public void setVariable(Symbol symbol)
            {
/*  83*/        setLeft(symbol);
            }

            public ASTree getInitializer()
            {
                ASTList astlist;
/*  86*/        if((astlist = tail()) != null)
/*  88*/            return astlist.head();
/*  90*/        else
/*  90*/            return null;
            }

            public void setLocalVar(int i)
            {
/*  93*/        localVar = i;
            }

            public int getLocalVar()
            {
/*  95*/        return localVar;
            }

            public String getTag()
            {
/*  97*/        return "decl";
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/* 100*/        visitor.atDeclarator(this);
            }

            public static String astToClassName(ASTList astlist, char c)
            {
/* 104*/        if(astlist == null)
                {
/* 105*/            return null;
                } else
                {
                    StringBuffer stringbuffer;
/* 107*/            astToClassName(stringbuffer = new StringBuffer(), astlist, c);
/* 109*/            return stringbuffer.toString();
                }
            }

            private static void astToClassName(StringBuffer stringbuffer, ASTList astlist, char c)
            {
/* 115*/        do
                {
                    ASTree astree;
/* 115*/            if((astree = astlist.head()) instanceof Symbol)
/* 117*/                stringbuffer.append(((Symbol)astree).get());
/* 118*/            else
/* 118*/            if(astree instanceof ASTList)
/* 119*/                astToClassName(stringbuffer, (ASTList)astree, c);
/* 121*/            if((astlist = astlist.tail()) != null)
/* 125*/                stringbuffer.append(c);
/* 127*/            else
/* 127*/                return;
                } while(true);
            }

            protected int varType;
            protected int arrayDim;
            protected int localVar;
            protected String qualifiedClass;
}
