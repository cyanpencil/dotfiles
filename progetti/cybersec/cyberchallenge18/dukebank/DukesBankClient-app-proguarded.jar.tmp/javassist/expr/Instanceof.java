// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Instanceof.java

package javassist.expr;

import javassist.*;
import javassist.bytecode.*;
import javassist.compiler.*;
import javassist.compiler.ast.ASTList;

// Referenced classes of package javassist.expr:
//            Expr

public class Instanceof extends Expr
{
    static class ProceedForInstanceof
        implements ProceedHandler
    {

                public void doit(JvstCodeGen jvstcodegen, Bytecode bytecode, ASTList astlist)
                    throws CompileError
                {
/* 152*/            if(jvstcodegen.getMethodArgsLength(astlist) != 1)
                    {
/* 153*/                throw new CompileError("$proceed() cannot take more than one parameter for instanceof");
                    } else
                    {
/* 157*/                jvstcodegen.atMethodArgs(astlist, new int[1], new int[1], new String[1]);
/* 158*/                bytecode.addOpcode(193);
/* 159*/                bytecode.addIndex(index);
/* 160*/                jvstcodegen.setType(CtClass.booleanType);
/* 161*/                return;
                    }
                }

                public void setReturnType(JvstTypeChecker jvsttypechecker, ASTList astlist)
                    throws CompileError
                {
/* 166*/            jvsttypechecker.atMethodArgs(astlist, new int[1], new int[1], new String[1]);
/* 167*/            jvsttypechecker.setType(CtClass.booleanType);
                }

                int index;

                ProceedForInstanceof(int i)
                {
/* 146*/            index = i;
                }
    }


            protected Instanceof(int i, CodeIterator codeiterator, CtClass ctclass, MethodInfo methodinfo)
            {
/*  33*/        super(i, codeiterator, ctclass, methodinfo);
            }

            public CtBehavior where()
            {
/*  40*/        return super.where();
            }

            public int getLineNumber()
            {
/*  49*/        return super.getLineNumber();
            }

            public String getFileName()
            {
/*  59*/        return super.getFileName();
            }

            public CtClass getType()
                throws NotFoundException
            {
/*  68*/        Object obj = getConstPool();
/*  69*/        int i = currentPos;
/*  70*/        i = iterator.u16bitAt(i + 1);
/*  71*/        obj = ((ConstPool) (obj)).getClassInfo(i);
/*  72*/        return thisClass.getClassPool().getCtClass(((String) (obj)));
            }

            public CtClass[] mayThrow()
            {
/*  82*/        return super.mayThrow();
            }

            public void replace(String s)
                throws CannotCompileException
            {
/*  94*/        thisClass.getClassFile();
/*  95*/        getConstPool();
/*  96*/        int i = currentPos;
/*  97*/        int j = iterator.u16bitAt(i + 1);
/*  99*/        Javac javac = new Javac(thisClass);
/* 100*/        ClassPool classpool = thisClass.getClassPool();
/* 101*/        CodeAttribute codeattribute = iterator.get();
/* 104*/        try
                {
/* 104*/            CtClass actclass[] = {
/* 104*/                classpool.get("java.lang.Object")
                    };
/* 106*/            CtClass ctclass = CtClass.booleanType;
/* 108*/            int k = codeattribute.getMaxLocals();
/* 109*/            javac.recordParams("java.lang.Object", actclass, true, k, withinStatic());
/* 111*/            int l = javac.recordReturnType(ctclass, true);
/* 112*/            javac.recordProceed(new ProceedForInstanceof(j));
/* 115*/            javac.recordType(getType());
/* 119*/            checkResultValue(ctclass, s);
/* 121*/            Bytecode bytecode = javac.getBytecode();
/* 122*/            storeStack(actclass, true, k, bytecode);
/* 123*/            javac.recordLocalVariables(codeattribute, i);
/* 125*/            bytecode.addConstZero(ctclass);
/* 126*/            bytecode.addStore(l, ctclass);
/* 128*/            javac.compileStmnt(s);
/* 129*/            bytecode.addLoad(l, ctclass);
/* 131*/            replace0(i, bytecode, 3);
/* 137*/            return;
                }
/* 133*/        catch(CompileError compileerror)
                {
/* 133*/            throw new CannotCompileException(compileerror);
                }
/* 134*/        catch(NotFoundException notfoundexception)
                {
/* 134*/            throw new CannotCompileException(notfoundexception);
                }
/* 135*/        catch(BadBytecode _ex)
                {
/* 136*/            throw new CannotCompileException("broken method");
                }
            }
}
