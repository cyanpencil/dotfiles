// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Cast.java

package javassist.expr;

import javassist.*;
import javassist.bytecode.*;
import javassist.compiler.*;
import javassist.compiler.ast.ASTList;

// Referenced classes of package javassist.expr:
//            Expr

public class Cast extends Expr
{
    static class ProceedForCast
        implements ProceedHandler
    {

                public void doit(JvstCodeGen jvstcodegen, Bytecode bytecode, ASTList astlist)
                    throws CompileError
                {
/* 148*/            if(jvstcodegen.getMethodArgsLength(astlist) != 1)
                    {
/* 149*/                throw new CompileError("$proceed() cannot take more than one parameter for cast");
                    } else
                    {
/* 153*/                jvstcodegen.atMethodArgs(astlist, new int[1], new int[1], new String[1]);
/* 154*/                bytecode.addOpcode(192);
/* 155*/                bytecode.addIndex(index);
/* 156*/                jvstcodegen.setType(retType);
/* 157*/                return;
                    }
                }

                public void setReturnType(JvstTypeChecker jvsttypechecker, ASTList astlist)
                    throws CompileError
                {
/* 162*/            jvsttypechecker.atMethodArgs(astlist, new int[1], new int[1], new String[1]);
/* 163*/            jvsttypechecker.setType(retType);
                }

                int index;
                CtClass retType;

                ProceedForCast(int i, CtClass ctclass)
                {
/* 141*/            index = i;
/* 142*/            retType = ctclass;
                }
    }


            protected Cast(int i, CodeIterator codeiterator, CtClass ctclass, MethodInfo methodinfo)
            {
/*  32*/        super(i, codeiterator, ctclass, methodinfo);
            }

            public CtBehavior where()
            {
/*  39*/        return super.where();
            }

            public int getLineNumber()
            {
/*  48*/        return super.getLineNumber();
            }

            public String getFileName()
            {
/*  57*/        return super.getFileName();
            }

            public CtClass getType()
                throws NotFoundException
            {
/*  65*/        Object obj = getConstPool();
/*  66*/        int i = currentPos;
/*  67*/        i = iterator.u16bitAt(i + 1);
/*  68*/        obj = ((ConstPool) (obj)).getClassInfo(i);
/*  69*/        return thisClass.getClassPool().getCtClass(((String) (obj)));
            }

            public CtClass[] mayThrow()
            {
/*  79*/        return super.mayThrow();
            }

            public void replace(String s)
                throws CannotCompileException
            {
/*  91*/        thisClass.getClassFile();
/*  92*/        getConstPool();
/*  93*/        int i = currentPos;
/*  94*/        int j = iterator.u16bitAt(i + 1);
/*  96*/        Javac javac = new Javac(thisClass);
/*  97*/        ClassPool classpool = thisClass.getClassPool();
/*  98*/        CodeAttribute codeattribute = iterator.get();
/* 101*/        try
                {
/* 101*/            CtClass actclass[] = {
/* 101*/                classpool.get("java.lang.Object")
                    };
/* 103*/            CtClass ctclass = getType();
/* 105*/            int k = codeattribute.getMaxLocals();
/* 106*/            javac.recordParams("java.lang.Object", actclass, true, k, withinStatic());
/* 108*/            int l = javac.recordReturnType(ctclass, true);
/* 109*/            javac.recordProceed(new ProceedForCast(j, ctclass));
/* 113*/            checkResultValue(ctclass, s);
/* 115*/            Bytecode bytecode = javac.getBytecode();
/* 116*/            storeStack(actclass, true, k, bytecode);
/* 117*/            javac.recordLocalVariables(codeattribute, i);
/* 119*/            bytecode.addConstZero(ctclass);
/* 120*/            bytecode.addStore(l, ctclass);
/* 122*/            javac.compileStmnt(s);
/* 123*/            bytecode.addLoad(l, ctclass);
/* 125*/            replace0(i, bytecode, 3);
/* 131*/            return;
                }
/* 127*/        catch(CompileError compileerror)
                {
/* 127*/            throw new CannotCompileException(compileerror);
                }
/* 128*/        catch(NotFoundException notfoundexception)
                {
/* 128*/            throw new CannotCompileException(notfoundexception);
                }
/* 129*/        catch(BadBytecode _ex)
                {
/* 130*/            throw new CannotCompileException("broken method");
                }
            }
}
