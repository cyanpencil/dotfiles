// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JvstTypeChecker.java

package javassist.compiler;

import javassist.*;
import javassist.compiler.ast.ASTList;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.CallExpr;
import javassist.compiler.ast.CastExpr;
import javassist.compiler.ast.Expr;
import javassist.compiler.ast.Member;
import javassist.compiler.ast.Symbol;

// Referenced classes of package javassist.compiler:
//            TypeChecker, CodeGen, CompileError, JvstCodeGen, 
//            MemberResolver, ProceedHandler

public class JvstTypeChecker extends TypeChecker
{

            public JvstTypeChecker(CtClass ctclass, ClassPool classpool, JvstCodeGen jvstcodegen)
            {
/*  29*/        super(ctclass, classpool);
/*  30*/        codeGen = jvstcodegen;
            }

            public void addNullIfVoid()
            {
/*  37*/        if(exprType == 344)
                {
/*  38*/            exprType = 307;
/*  39*/            arrayDim = 0;
/*  40*/            className = "java/lang/Object";
                }
            }

            public void atMember(Member member)
                throws CompileError
            {
                String s;
/*  48*/        if((s = member.get()).equals(codeGen.paramArrayName))
                {
/*  50*/            exprType = 307;
/*  51*/            arrayDim = 1;
/*  52*/            className = "java/lang/Object";
/*  52*/            return;
                }
/*  54*/        if(s.equals("$sig"))
                {
/*  55*/            exprType = 307;
/*  56*/            arrayDim = 1;
/*  57*/            className = "java/lang/Class";
/*  57*/            return;
                }
/*  59*/        if(s.equals("$type") || s.equals("$class"))
                {
/*  61*/            exprType = 307;
/*  62*/            arrayDim = 0;
/*  63*/            className = "java/lang/Class";
/*  63*/            return;
                } else
                {
/*  66*/            super.atMember(member);
/*  67*/            return;
                }
            }

            protected void atFieldAssign(Expr expr, int i, ASTree astree, ASTree astree1)
                throws CompileError
            {
/*  72*/        if((astree instanceof Member) && ((Member)astree).get().equals(codeGen.paramArrayName))
                {
/*  74*/            astree1.accept(this);
/*  75*/            if((expr = codeGen.paramTypeList) == null)
/*  77*/                return;
/*  79*/            i = expr.length;
/*  80*/            for(astree = 0; astree < i; astree++)
/*  81*/                compileUnwrapValue(expr[astree]);

/*  82*/            return;
                } else
                {
/*  84*/            super.atFieldAssign(expr, i, astree, astree1);
/*  85*/            return;
                }
            }

            public void atCastExpr(CastExpr castexpr)
                throws CompileError
            {
                ASTList astlist;
                ASTree astree;
/*  88*/        if((astlist = castexpr.getClassName()) != null && castexpr.getArrayDim() == 0 && ((astree = astlist.head()) instanceof Symbol) && astlist.tail() == null)
                {
                    String s;
/*  92*/            if((s = ((Symbol)astree).get()).equals(codeGen.returnCastName))
                    {
/*  94*/                atCastToRtype(castexpr);
/*  95*/                return;
                    }
/*  97*/            if(s.equals("$w"))
                    {
/*  98*/                atCastToWrapper(castexpr);
/*  99*/                return;
                    }
                }
/* 104*/        super.atCastExpr(castexpr);
            }

            protected void atCastToRtype(CastExpr castexpr)
                throws CompileError
            {
/* 112*/        CtClass ctclass = codeGen.returnType;
/* 113*/        castexpr.getOprand().accept(this);
/* 114*/        if(exprType == 344 || CodeGen.isRefType(exprType) || arrayDim > 0)
                {
/* 115*/            compileUnwrapValue(ctclass);
/* 115*/            return;
                }
/* 116*/        if(ctclass instanceof CtPrimitiveType)
                {
/* 117*/            castexpr = MemberResolver.descToType((castexpr = (CtPrimitiveType)ctclass).getDescriptor());
/* 119*/            exprType = castexpr;
/* 120*/            arrayDim = 0;
/* 121*/            className = null;
                }
            }

            protected void atCastToWrapper(CastExpr castexpr)
                throws CompileError
            {
/* 126*/        castexpr.getOprand().accept(this);
/* 127*/        if(CodeGen.isRefType(exprType) || arrayDim > 0)
/* 128*/            return;
/* 130*/        if((castexpr = resolver.lookupClass(exprType, arrayDim, className)) instanceof CtPrimitiveType)
                {
/* 132*/            exprType = 307;
/* 133*/            arrayDim = 0;
/* 134*/            className = "java/lang/Object";
                }
            }

            public void atCallExpr(CallExpr callexpr)
                throws CompileError
            {
                Object obj;
/* 142*/        if((obj = callexpr.oprand1()) instanceof Member)
                {
/* 144*/            obj = ((Member)obj).get();
/* 145*/            if(codeGen.procHandler != null && ((String) (obj)).equals(codeGen.proceedName))
                    {
/* 147*/                codeGen.procHandler.setReturnType(this, (ASTList)callexpr.oprand2());
/* 149*/                return;
                    }
/* 151*/            if(((String) (obj)).equals("$cflow"))
                    {
/* 152*/                atCflow((ASTList)callexpr.oprand2());
/* 153*/                return;
                    }
                }
/* 157*/        super.atCallExpr(callexpr);
            }

            protected void atCflow(ASTList astlist)
                throws CompileError
            {
/* 163*/        exprType = 324;
/* 164*/        arrayDim = 0;
/* 165*/        className = null;
            }

            public boolean isParamListName(ASTList astlist)
            {
/* 172*/        if(codeGen.paramTypeList != null && astlist != null && astlist.tail() == null)
/* 174*/            return ((astlist = astlist.head()) instanceof Member) && ((Member)astlist).get().equals(codeGen.paramListName);
/* 179*/        else
/* 179*/            return false;
            }

            public int getMethodArgsLength(ASTList astlist)
            {
/* 183*/        String s = codeGen.paramListName;
/* 184*/        int i = 0;
/* 185*/        for(; astlist != null; astlist = astlist.tail())
                {
                    ASTree astree;
/* 186*/            if(((astree = astlist.head()) instanceof Member) && ((Member)astree).get().equals(s))
                    {
/* 188*/                if(codeGen.paramTypeList != null)
/* 189*/                    i += codeGen.paramTypeList.length;
                    } else
                    {
/* 192*/                i++;
                    }
                }

/* 197*/        return i;
            }

            public void atMethodArgs(ASTList astlist, int ai[], int ai1[], String as[])
                throws CompileError
            {
/* 202*/        CtClass actclass[] = codeGen.paramTypeList;
/* 203*/        String s = codeGen.paramListName;
/* 204*/        int i = 0;
/* 205*/        for(; astlist != null; astlist = astlist.tail())
                {
                    int j;
/* 206*/            if(((j = astlist.head()) instanceof Member) && ((Member)j).get().equals(s))
                    {
/* 208*/                if(actclass == null)
/* 209*/                    continue;
/* 209*/                j = actclass.length;
/* 210*/                for(int k = 0; k < j; k++)
                        {
/* 211*/                    CtClass ctclass = actclass[k];
/* 212*/                    setType(ctclass);
/* 213*/                    ai[i] = exprType;
/* 214*/                    ai1[i] = arrayDim;
/* 215*/                    as[i] = className;
/* 216*/                    i++;
                        }

                    } else
                    {
/* 221*/                j.accept(this);
/* 222*/                ai[i] = exprType;
/* 223*/                ai1[i] = arrayDim;
/* 224*/                as[i] = className;
/* 225*/                i++;
                    }
                }

            }

            void compileInvokeSpecial(ASTree astree, String s, String s1, String s2, ASTList astlist)
                throws CompileError
            {
/* 239*/        astree.accept(this);
/* 240*/        astree = getMethodArgsLength(astlist);
/* 241*/        atMethodArgs(astlist, new int[astree], new int[astree], new String[astree]);
/* 243*/        setReturnType(s2);
/* 244*/        addNullIfVoid();
            }

            protected void compileUnwrapValue(CtClass ctclass)
                throws CompileError
            {
/* 249*/        if(ctclass == CtClass.voidType)
                {
/* 250*/            addNullIfVoid();
/* 250*/            return;
                } else
                {
/* 252*/            setType(ctclass);
/* 253*/            return;
                }
            }

            public void setType(CtClass ctclass)
                throws CompileError
            {
/* 259*/        setType(ctclass, 0);
            }

            private void setType(CtClass ctclass, int i)
                throws CompileError
            {
/* 263*/        if(ctclass.isPrimitive())
                {
/* 264*/            ctclass = (CtPrimitiveType)ctclass;
/* 265*/            exprType = MemberResolver.descToType(ctclass.getDescriptor());
/* 266*/            arrayDim = i;
/* 267*/            className = null;
/* 268*/            return;
                }
/* 269*/        if(ctclass.isArray())
                {
/* 271*/            try
                    {
/* 271*/                setType(ctclass.getComponentType(), i + 1);
/* 275*/                return;
                    }
/* 273*/            catch(NotFoundException _ex)
                    {
/* 274*/                throw new CompileError((new StringBuilder("undefined type: ")).append(ctclass.getName()).toString());
                    }
                } else
                {
/* 277*/            exprType = 307;
/* 278*/            arrayDim = i;
/* 279*/            className = MemberResolver.javaToJvmName(ctclass.getName());
/* 281*/            return;
                }
            }

            private JvstCodeGen codeGen;
}
